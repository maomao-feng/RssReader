package rssreader;

import articlefetcher.FileFetcher;
import articlefetcher.RssFetcher;
import picocli.CommandLine;

public class RssReader{

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new CommandProcessor());

        int exitCode = commandLine.execute(args);
        if (exitCode != 0)
        	System.exit(exitCode);

        ReaderConfig config = commandLine.getExecutionResult();
        new ContentProcessor(new RssFetcher(), new FileFetcher()).process(config);
    }

}
