package articlefetcher;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import interfaces.ArticleFetcher;
import model.Article;

public class RssFetcher implements ArticleFetcher {

	@Override
	public List<Article> fetchArticles(Object source){
		try {
			SyndFeed feed = new SyndFeedInput().build(new XmlReader((URL) source));
			return convertToArticles(feed);
		} catch (Exception e) {
			System.out.println("Exception happenned when fetching rss feed");
			return new ArrayList<>();
		}

	}

	private List<Article> convertToArticles(SyndFeed feed) {
		return feed.getEntries().stream().//
				map(e -> new Article(e.getTitle().trim(), e.getDescription().getValue().trim())).//
				collect(Collectors.toList());
	}

}
