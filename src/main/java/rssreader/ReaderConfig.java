package rssreader;

import java.util.List;

import com.google.common.base.Strings;

class ReaderConfig {

	private String sourcePath;
	private List<ConvertWay> convertWays;
	private String outputFileName;

	private ReaderConfig(String sourcePath, List<ConvertWay> convertWays, String outputFileName) {
		this.sourcePath = sourcePath;
		this.convertWays = convertWays;
		this.outputFileName = outputFileName;

	}

	static ReaderConfig of(String sourcePath, List<ConvertWay> convertWays, String outputFileName) {
		if (Strings.isNullOrEmpty(sourcePath)) {
			throw new IllegalArgumentException("Source path not specified!");
		}
		if (convertWays.isEmpty()) {
			throw new IllegalArgumentException("At least one way to convert should be specified");
		}

		return new ReaderConfig(sourcePath, convertWays, outputFileName);
	}

	String sourcePath() {
		return this.sourcePath;
	}

	List<ConvertWay> convertWays() {
		return this.convertWays;
	}

	String outputFileName() {
		return this.outputFileName;
	}

	boolean shouldOutputToScreen() {
		return Strings.isNullOrEmpty(outputFileName);
	}

}
