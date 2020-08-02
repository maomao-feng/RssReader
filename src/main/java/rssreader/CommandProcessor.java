package rssreader;

import java.util.List;
import java.util.concurrent.Callable;

import picocli.CommandLine.Option;

class CommandProcessor implements Callable<ReaderConfig>{
    @Option(names = "-i", required = true, description = "rss feed url, or file path")
    private String sourcePath;

    @Option(names = "-c", split = ",", required = true,//
    		description = "cut or convert, or both with comma to split./n"
    				+ "cut : cut title to 10 and body to 30 words/n"
    				+ "convert : convert 'ユーザベース' to 'UZABASE'")
    private List<ConvertWay> convertWays;

    @Option(names = "-o", description = "output file name")
    private String outputFileName;


	@Override
	public	ReaderConfig call() {
		return ReaderConfig.of(sourcePath, convertWays, outputFileName);
	}
}
