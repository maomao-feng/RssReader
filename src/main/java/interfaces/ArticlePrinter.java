package interfaces;

import java.util.List;

import model.Article;

public interface ArticlePrinter {
	public void print(List<Article> articles, String outputFileName);
}
