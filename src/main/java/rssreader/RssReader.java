package rssreader;

import articleconverter.ArticleCutter;
import articleconverter.WordConverter;
import articlefetcher.FileFetcher;
import articlefetcher.RssFetcher;
import articleprinter.FilePrinter;
import articleprinter.ScreenPrinter;
import picocli.CommandLine;

public class RssReader{

    public static void main(String[] args) throws Exception {
        CommandLine commandLine = new CommandLine(new CommandProcessor());

        int exitCode = commandLine.execute(args);
        if (exitCode != 0)
        	System.exit(exitCode);

        ReaderConfig config = commandLine.getExecutionResult();
        new ContentProcessor(new RssFetcher(), new FileFetcher(),//
        		new WordConverter(), new ArticleCutter(),//
        		new ScreenPrinter(), new FilePrinter()).process(config);
    }

}
