package articleprinter;

import java.util.List;

import interfaces.ArticlePrinter;
import model.Article;

public class FilePrinterMock implements ArticlePrinter {

	private int callTimes = 0;

	@Override
	public void print(List<Article> articles, String outputFileName) {
		this.callTimes ++;
	}

	public int callTimes() {
		return this.callTimes;
	}
}
