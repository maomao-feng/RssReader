package rssreader;

import picocli.CommandLine;

public class RssReader{

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new CommandProcessor());
    	int exitCode = commandLine.execute(args);
        if (exitCode != 0)
        	System.exit(exitCode);
        ReaderConfig config = commandLine.getExecutionResult();
		System.out.println("source: " + config.sourcePath());
		System.out.println("convert: ");
		config.convertWays().forEach(e -> System.out.println(e));
		System.out.println("output file: " + config.outputFileName());
    }

}
