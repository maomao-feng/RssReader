package articlefetcher;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import model.Article;

public class FileFetcherTest {

	@Test
	public void test() throws IOException {
		FileFetcher fileFetcher = new FileFetcher();
		List<Article> articles = fileFetcher.fetchArticles("src/test/resources/articles.txt");
		assertThat(articles.size(),is(2));
	}

}
