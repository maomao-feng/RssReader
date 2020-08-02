package articleconverter;

import java.util.ArrayList;
import java.util.List;

import interfaces.ArticleConverter;
import model.Article;

public class WordConverterMock implements ArticleConverter {

	private int calledTimes = 0;
	private List<Article> convertedArticles = new ArrayList<>();

	public WordConverterMock(List<Article> convertedArticles) {
		this.convertedArticles = convertedArticles;
	}

	public WordConverterMock() {
	}

	public int calledTimes() {
		return this.calledTimes;
	}

	@Override
	public List<Article> convert(List<Article> rawArticles) {
		this.calledTimes++;
		return this.convertedArticles;
	}
}
