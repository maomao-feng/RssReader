package articleprinter;

import java.util.List;

import interfaces.ArticlePrinter;
import model.Article;

public class ScreenPrinter implements ArticlePrinter {

	@Override
	public void print(List<Article> articles, String outputFileName) {
		articles.stream().forEach(e -> {
			System.out.println("title: " + e.title());
			System.out.println("body: " + e.body());
		});
	}
}
