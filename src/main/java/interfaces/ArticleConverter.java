package interfaces;

import java.util.List;

import model.Article;

public interface ArticleConverter {
	public List<Article> convert(List<Article> rawArticles);
}
