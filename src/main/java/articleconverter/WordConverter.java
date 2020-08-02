package articleconverter;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Strings;

import interfaces.ArticleConverter;
import model.Article;

public class WordConverter implements ArticleConverter {

	private static final String REPLACEMENT = "UZABASE";
	private static final String TARGET = "ユーザベース";

	@Override
	public List<Article> convert(List<Article> rawArticles) {
		return rawArticles.stream().map(a -> convert(a)).collect(Collectors.toList());
	}

	private Article convert(Article article) {
		return new Article(convert(article.title()), convert(article.body()));
	}

	private String convert(String content) {
		if (Strings.isNullOrEmpty(content))
			return "";
		return content.replaceAll(TARGET, REPLACEMENT);
	}

}
