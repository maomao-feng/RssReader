package rssreader;

import java.util.List;

import com.google.common.base.Strings;

class ReaderConfig {

	private String sourcePath;
	private List<ConversionType> conversionTypes;
	private String outputFileName;

	private ReaderConfig(String sourcePath, List<ConversionType> conversionTypes, String outputFileName) {
		this.sourcePath = sourcePath;
		this.conversionTypes = conversionTypes;
		this.outputFileName = outputFileName;

	}

	static ReaderConfig of(String sourcePath, List<ConversionType> convertWays, String outputFileName) {
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

	List<ConversionType> conversionTypes() {
		return this.conversionTypes;
	}

	String outputFileName() {
		return this.outputFileName;
	}

	boolean shouldOutputToScreen() {
		return Strings.isNullOrEmpty(outputFileName);
	}

}
