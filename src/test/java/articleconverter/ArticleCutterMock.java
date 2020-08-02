package articleconverter;

import java.util.List;

import interfaces.ArticleConverter;
import model.Article;

public class ArticleCutterMock implements ArticleConverter {

	private int calledTimes = 0;

	public int calledTimes() {
		return this.calledTimes;
	}

	@Override
	public List<Article> convert(List<Article> rawArticles) {
		this.calledTimes++;
		return null;
	}
}
