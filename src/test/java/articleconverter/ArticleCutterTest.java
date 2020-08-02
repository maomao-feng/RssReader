package articleconverter;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import model.Article;

public class ArticleCutterTest {

	@Test
	public void cut_title_to_length_10_and_body_to_30_when_both_longer() {
		ArticleCutter articleCutter = new ArticleCutter();
		String longerThan30 = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDE";
		List<Article> rawArticles = Arrays.asList(new Article(longerThan30, longerThan30));
		List<Article> expectedArticles = Arrays.asList(new Article(longerThan30.substring(0, 10), longerThan30.substring(0, 30)));
		assertThat(articleCutter.convert(rawArticles), is(expectedArticles));
	}

	@Test
	public void not_cut_when_shorter_than_10_and_30() {
		ArticleCutter articleCutter = new ArticleCutter();
		String shorterThan10 = "123456789";
		List<Article> rawArticles = Arrays.asList(new Article(shorterThan10, shorterThan10));
		assertThat(articleCutter.convert(rawArticles), is(rawArticles));
	}

	@Test
	public void not_cut_when_equal_to_10_and_30() {
		ArticleCutter articleCutter = new ArticleCutter();
		String lengthOf10 = "0123456789";
		String lengthOf30 = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCD";
		List<Article> rawArticles = Arrays.asList(new Article(lengthOf10, lengthOf30));
		assertThat(articleCutter.convert(rawArticles), is(rawArticles));
	}
}
