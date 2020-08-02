package articleconverter;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Strings;

import interfaces.ArticleConverter;
import model.Article;

public class ArticleCutter implements ArticleConverter {

	private static final int BODY_MAX_LENGTH = 30;
	private static final int TITLE_MAX_LENGTH = 10;

	@Override
	public List<Article> convert(List<Article> rawArticles) {
		return rawArticles.stream().map(a -> cut(a)).collect(Collectors.toList());

	}

	private Article cut(Article article) {
		return new Article(cutToLength(article.title(), TITLE_MAX_LENGTH),//
				cutToLength(article.body(), BODY_MAX_LENGTH));
	}

	private String cutToLength(String content, int maxLength) {
		if (Strings.isNullOrEmpty(content))
			return "";
		if(content.length()<=maxLength)
			return content;
		return content.substring(0,maxLength);
	}

}
