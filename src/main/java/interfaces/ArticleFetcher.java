package interfaces;

import java.util.List;

import model.Article;

public interface ArticleFetcher {
	public List<Article> fetchArticles(Object source);
}
