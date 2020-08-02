package articlefetcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.collect.Iterables;
import com.google.common.io.Files;

import interfaces.ArticleFetcher;
import model.Article;

public class FileFetcher implements ArticleFetcher {

	@Override
	public List<Article> fetchArticles(Object source){
		File sourceFile = new File((String) source);
		List<String> rawText;
		try {
			rawText = Files.readLines(sourceFile, Charsets.UTF_8);
			return parseText(rawText);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return Collections.emptyList();
		}

	}

	private List<Article> parseText(List<String> rawText) {
		List<Article> articles = new ArrayList<>();
		List<String> titles = new ArrayList<>();
		List<String> bodies = new ArrayList<>();
		for(String line: rawText) {
			if(line.startsWith("title:")) {
				if(titles.size() > bodies.size()) {
					bodies.add("");
					articles.add(new Article(Iterables.getLast(titles, ""), Iterables.getLast(bodies, "")));
				}
				titles.add(line.split(":")[1].trim());
				continue;
			}
			if(line.startsWith("body:")) {
				if(titles.size() == bodies.size()) {
					titles.add("");
				}
				bodies.add(line.split(":")[1].trim());
				articles.add(new Article(Iterables.getLast(titles, ""), Iterables.getLast(bodies, "")));
				continue;
			}
		}
		return articles;
	}

}
