package articlefetcher;

import java.util.ArrayList;
import java.util.List;

import interfaces.ArticleFetcher;
import model.Article;

public class RssFetcherMock implements ArticleFetcher{

	private int callTimes = 0;
	private List<Article> rawArticles = new ArrayList<>();

	public RssFetcherMock(List<Article> rawArticles) {
		this.rawArticles  = rawArticles;
	}

	public RssFetcherMock() {
	}

	@Override
	public List<Article> fetchArticles(Object source) {
		this.callTimes  ++;
		return this.rawArticles;
	}

	public int callTimes() {
		return this.callTimes;
	}

}
