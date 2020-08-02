package articleprinter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.io.Files;

import interfaces.ArticlePrinter;
import model.Article;

public class FilePrinter implements ArticlePrinter {

	@Override
	public void print(List<Article> articles, String outputFileName) {
		String outputString = articles.stream().map(e -> Joiner.on("\n").//
				join("title: " + e.title(), "body: " + e.body())).//
				reduce("", (partialString, s) -> Joiner.on("\n").//
						join(partialString, s, ""));
		File file = new File(outputFileName);
		try {
			Files.asCharSink(file, Charsets.UTF_8).write(outputString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
