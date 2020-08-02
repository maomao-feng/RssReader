package rssreader;

import java.util.List;

import com.google.common.base.Strings;

public class ReaderConfig {

	private String sourcePath;
	private List<ConvertWay> convertWays;
	private String outputFileName;

	private ReaderConfig(String sourcePath, List<ConvertWay> convertWays, String outputFileName) {
		this.sourcePath = sourcePath;
		this.convertWays = convertWays;
		this.outputFileName = outputFileName;

	}

	public static ReaderConfig of(String sourcePath, List<ConvertWay> convertWays, String outputFileName) {
		if (Strings.isNullOrEmpty(sourcePath)) {
			throw new IllegalArgumentException("Source path not specified!");
		}
		if (convertWays.isEmpty()) {
			throw new IllegalArgumentException("At least one way to convert should be specified");
		}

		return new ReaderConfig(sourcePath, convertWays, outputFileName);
	}

	public String sourcePath() {
		return this.sourcePath;
	}

	public List<ConvertWay> convertWays() {
		return this.convertWays;
	}

	public String outputFileName() {
		return this.outputFileName;
	}

	public boolean shouldOutputToScreen() {
		return Strings.isNullOrEmpty(outputFileName);
	}

}
