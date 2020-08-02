package rssreader;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class ContentProcessorTest {

	private static final String URL = "http://test.com";
	private static final String FILE = "test.txt";
	private ContentProcessor contentProcessor;
	private RssFetcherMock rssFetcher;
	private FileFetcherMock fileFetcher;

	@Before
	public void setUp() {
		rssFetcher = new RssFetcherMock();
		fileFetcher = new FileFetcherMock();
		contentProcessor = new ContentProcessor(rssFetcher, fileFetcher);
	}

	@Test
	public void rssFetcher_is_called_when_source_path_is_an_valid_url() {
		ReaderConfig config = ReaderConfig.of(URL, Arrays.asList(ConvertWay.cut), null);
		contentProcessor.process(config);
		assertThat(rssFetcher.callTimes(),is(1));
		assertThat(fileFetcher.callTimes(), is(0));
	}

	@Test
	public void fileFetcher_is_called_when_source_path_is_not_url() {
		ReaderConfig config = ReaderConfig.of(FILE, Arrays.asList(ConvertWay.cut), null);
		contentProcessor.process(config);
		assertThat(rssFetcher.callTimes(),is(0));
		assertThat(fileFetcher.callTimes(), is(1));
	}


}
