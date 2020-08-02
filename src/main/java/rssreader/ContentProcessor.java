package rssreader;

import java.net.MalformedURLException;
import java.net.URL;
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
//		rawArticles.stream().forEach(e -> System.out.println(e.title()));
		if(null == rawArticles || rawArticles.isEmpty()) {
			return;
		}
		List<Article> convertedArticles = convertArticles(rawArticles, config.conversionTypes());
//		convertedArticles.stream().forEach(e -> System.out.println(e.title()));
	}

	private List<Article> fetchArticles(String sourcePath) throws Exception {
		try {
			URL feedUrl = new URL(sourcePath);
			return rssFetcher.fetchArticles(feedUrl);
		}catch(MalformedURLException e) {
			return fileFetcher.fetchArticles(sourcePath);
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
