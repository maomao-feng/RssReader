package articlefetcher;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class RssFetcherTest {

	@Test
	public void articles_fetched_from_valid_rss_feed_url() throws MalformedURLException {
		RssFetcher rssFetcher = new RssFetcher();
		URL okSource = new URL("https://tech.uzabase.com/feed");
		assertThat(rssFetcher.fetchArticles(okSource).isEmpty(), is(false));
	}

	@Test
	public void no_article_fetched_from_invalid_rss_feed_url() throws MalformedURLException {
		RssFetcher rssFetcher = new RssFetcher();
		URL ngSource = new URL("http://google.com");
		assertThat(rssFetcher.fetchArticles(ngSource).isEmpty(), is(true));
	}

}
