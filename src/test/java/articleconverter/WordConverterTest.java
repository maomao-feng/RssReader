package articleconverter;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import model.Article;

public class WordConverterTest {

	private static final String CONTENT_SHOULD_NOT_CONVERT = "ーザベース12345";
	private static final String CONVERTED_CONTENT = "UZABASE12345";
	private static final String CONTENT_SHOULD_CONVERT = "ユーザベース12345";

	@Test
	public void convert_uzabase_katakana_to_uppercase_romaji() {
		WordConverter wordConverter = new WordConverter();
		List<Article> rawArticles = Arrays.asList(new Article(CONTENT_SHOULD_CONVERT, CONTENT_SHOULD_CONVERT));
		List<Article> expectedArticles = Arrays.asList(new Article(CONVERTED_CONTENT, CONVERTED_CONTENT));
		assertThat(wordConverter.convert(rawArticles), is(expectedArticles));
	}

	@Test
	public void not_convert_when_no_uzabase_katakana() {
		WordConverter wordConverter = new WordConverter();
		List<Article> rawArticles = Arrays.asList(new Article(CONTENT_SHOULD_NOT_CONVERT, CONTENT_SHOULD_NOT_CONVERT));
		assertThat(wordConverter.convert(rawArticles), is(rawArticles));
	}
}
