package rssreader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import interfaces.ArticleConverter;
import interfaces.ArticleFetcher;
import model.Article;

class ContentProcessor {

	private ArticleFetcher rssFetcher;
	private ArticleFetcher fileFetcher;
	private ArticleConverter wordConverter;
	private ArticleConverter articleCutter;

	public ContentProcessor(ArticleFetcher rssFetcher, ArticleFetcher fileFetcher,//
			ArticleConverter wordConverter, ArticleConverter articleCutter) {
		this.rssFetcher = rssFetcher;
		this.fileFetcher = fileFetcher;
		this.wordConverter = wordConverter;
		this.articleCutter = articleCutter;
	}

	void process(ReaderConfig config) throws Exception {
		List<Article> rawArticles = fetchArticles(config.sourcePath());
		if(null == rawArticles || rawArticles.isEmpty()) {
			return;
		}
		List<Article> convertedArticles = convertArticles(rawArticles, config.conversionTypes());
		if(null == convertedArticles || convertedArticles.isEmpty()) {
			return;
		}
		convertedArticles.stream().forEach(e -> {
			System.out.println("title: " + e.title());
			System.out.println("body: " + e.body());
		});
	}

	private List<Article> fetchArticles(String sourcePath) throws Exception {
		try {
			URL feedUrl = new URL(sourcePath);
			return rssFetcher.fetchArticles(feedUrl);
		}catch(MalformedURLException e) {
			return fileFetcher.fetchArticles(sourcePath);
		}catch(IOException e) {
			System.out.println("Exception happenned. Check source path");
			return Collections.emptyList();
		}
	}

	private List<Article> convertArticles(List<Article> articles, List<ConversionType> conversionTypes) {
		for(ConversionType conversionType : conversionTypes) {
			articles = conversionType == ConversionType.convert ?
						wordConverter.convert(articles)
						: articleCutter.convert(articles);
		}
		return articles;
	}
}
