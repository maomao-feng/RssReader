package rssreader;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import articleconverter.ArticleCutterMock;
import articleconverter.WordConverterMock;
import articlefetcher.FileFetcherMock;
import articlefetcher.RssFetcherMock;
import model.Article;

@RunWith(Enclosed.class)
public class ContentProcessorTest {

	private static final String URL = "http://test.com";
	private static final String FILE = "test.txt";
	private static ContentProcessor contentProcessor;
	private static RssFetcherMock rssFetcher;
	private static FileFetcherMock fileFetcher;
	private static WordConverterMock wordConverter;
	private static ArticleCutterMock articleCutter;

	public static class FetchArticlesTest{
		@Before
		public void setUp() {
			rssFetcher = new RssFetcherMock();
			fileFetcher = new FileFetcherMock();
			wordConverter = new WordConverterMock();
			articleCutter = new ArticleCutterMock();
			contentProcessor = new ContentProcessor(rssFetcher, fileFetcher, wordConverter, articleCutter);
		}

		@Test
		public void rssFetcher_is_called_when_source_path_is_an_valid_url() throws Exception {
			ReaderConfig config = ReaderConfig.of(URL, Arrays.asList(ConversionType.cut), null);
			contentProcessor.process(config);
			assertThat(rssFetcher.callTimes(),is(1));
			assertThat(fileFetcher.callTimes(), is(0));
		}

		@Test
		public void fileFetcher_is_called_when_source_path_is_not_url() throws Exception {
			ReaderConfig config = ReaderConfig.of(FILE, Arrays.asList(ConversionType.cut), null);
			contentProcessor.process(config);
			assertThat(rssFetcher.callTimes(),is(0));
			assertThat(fileFetcher.callTimes(), is(1));
		}
	}

	public static class ConvertArticlesTest{
		@Before
		public void setUp() {
			fileFetcher = new FileFetcherMock();
			wordConverter = new WordConverterMock();
			articleCutter = new ArticleCutterMock();
		}


		@Test
		public void not_convert_when_no_articles_fetched() throws Exception {
			//setup: rss fetcher return an empty list
			List<Article> emptyRawArticles = new ArrayList<>();
			rssFetcher = new RssFetcherMock(emptyRawArticles);
			contentProcessor = new ContentProcessor(rssFetcher, fileFetcher, wordConverter, articleCutter);
			ReaderConfig config = ReaderConfig.of(URL, Arrays.asList(ConversionType.cut), null);

			//execute
			contentProcessor.process(config);

			//verify
			assertThat(wordConverter.calledTimes(),is(0));
			assertThat(articleCutter.calledTimes(),is(0));
		}

		@Test
		public void word_converter_called_when_conversion_type_of_convert_in_config() throws Exception {
			//setup: rss fetcher return a non-empty list
			List<Article> rawArticles = createArticles();
			rssFetcher = new RssFetcherMock(rawArticles);
			contentProcessor = new ContentProcessor(rssFetcher, fileFetcher, wordConverter, articleCutter);
			ReaderConfig config = ReaderConfig.of(URL, Arrays.asList(ConversionType.convert), null);

			//execute
			contentProcessor.process(config);

			//verify
			assertThat(wordConverter.calledTimes(),is(1));
			assertThat(articleCutter.calledTimes(),is(0));
		}

		@Test
		public void article_cutter_called_when_conversion_type_of_cut_in_config() throws Exception {
			//setup: rss fetcher return a non-empty list
			List<Article> rawArticles = createArticles();
			rssFetcher = new RssFetcherMock(rawArticles);
			contentProcessor = new ContentProcessor(rssFetcher, fileFetcher, wordConverter, articleCutter);
			ReaderConfig config = ReaderConfig.of(URL, Arrays.asList(ConversionType.cut), null);

			//execute
			contentProcessor.process(config);

			//verify
			assertThat(wordConverter.calledTimes(),is(0));
			assertThat(articleCutter.calledTimes(),is(1));
		}

		@Test
		public void two_converter_both_called_when_both_conversion_type_in_config() throws Exception {
			//setup: rss fetcher return a non-empty list
			List<Article> rawArticles = createArticles();
			rssFetcher = new RssFetcherMock(rawArticles);
			contentProcessor = new ContentProcessor(rssFetcher, fileFetcher, wordConverter, articleCutter);
			ReaderConfig config = ReaderConfig.of(URL, Arrays.asList(ConversionType.convert, ConversionType.cut), null);

			//execute
			contentProcessor.process(config);

			//verify
			assertThat(wordConverter.calledTimes(),is(1));
			assertThat(articleCutter.calledTimes(),is(1));
		}

		private List<Article> createArticles() {
			return Arrays.asList(new Article("test title", "test body"));
		}
	}
}
