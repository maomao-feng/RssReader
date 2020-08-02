package rssreader;

import java.util.List;

import interfaces.ArticleFetcher;
import model.Article;

public class FileFetcherMock implements ArticleFetcher {

	private int callTimes = 0;

	@Override
	public List<Article> fetchArticles(Object source) {
		this.callTimes  ++;
		return null;
	}

	public int callTimes() {
		return this.callTimes;
	}
}
