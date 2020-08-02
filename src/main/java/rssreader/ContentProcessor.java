package rssreader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import interfaces.ArticleFetcher;
import model.Article;

class ContentProcessor {

	private ArticleFetcher rssFetcher;
	private ArticleFetcher fileFetcher;

	public ContentProcessor(ArticleFetcher rssFetcher, ArticleFetcher fileFetcher) {
		this.rssFetcher = rssFetcher;
		this.fileFetcher = fileFetcher;
	}

	void process(ReaderConfig config) {
		List<Article> articles = fetchArticles(config.sourcePath());
	}

	private List<Article> fetchArticles(String sourcePath) {
		try {
			URL feedUrl = new URL(sourcePath);
			return rssFetcher.fetchArticles(feedUrl);
		}catch(MalformedURLException e) {
			return fileFetcher.fetchArticles(sourcePath);
		}
	}

}
