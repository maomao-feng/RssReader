package rssreader;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ReaderConfigTest {

	private static final String CONVERT_WAY_NOT_SPECIFIED_MESSAGE = "At least one way to convert should be specified";
	private static final String SOURCE_PATH = "test.com";
	private static final List<ConversionType> CONVERSION_TYPE = Arrays.asList(ConversionType.cut);
	private static final String OUTPUT_FILE_NAME = "test.txt";
	private static final String SOURCE_PATH_NOT_SPECIFIED_MESSAGE = "Source path not specified!";

	@Test
	public void get_source_path_and_convert_ways_and_output_file_name_when_specified() {
		ReaderConfig config = ReaderConfig.of(SOURCE_PATH, CONVERSION_TYPE, OUTPUT_FILE_NAME);
		assertThat(config.sourcePath(), is(SOURCE_PATH));
		assertThat(config.conversionTypes(),is(CONVERSION_TYPE));
		assertThat(config.outputFileName(), is(OUTPUT_FILE_NAME));
		assertThat(config.shouldOutputToScreen(), is(false));

	}

	@Test
	public void shouldOutputToScreen_is_true_when_output_file_name_is_null() {
		ReaderConfig config = ReaderConfig.of(SOURCE_PATH, CONVERSION_TYPE, null);
		assertThat(config.shouldOutputToScreen(), is(true));
	}

	@Test
	public void throws_exception_when_source_path_is_null() {
		try {
			ReaderConfig.of(null, CONVERSION_TYPE, OUTPUT_FILE_NAME);
			fail("Should throw exception");
		}catch(IllegalArgumentException e){
			assertThat(e.getMessage(), is(SOURCE_PATH_NOT_SPECIFIED_MESSAGE));
		}
	}

	@Test
	public void throws_exception_when_convert_ways_list_is_empty() {
		try {
			ReaderConfig.of(SOURCE_PATH, new ArrayList<>(), OUTPUT_FILE_NAME);
			fail("Should throw exception");
		}catch(IllegalArgumentException e){
			assertThat(e.getMessage(), is(CONVERT_WAY_NOT_SPECIFIED_MESSAGE));
		}
	}


}
