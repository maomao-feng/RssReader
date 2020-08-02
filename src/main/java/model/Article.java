package model;

public class Article {

	private String title;
	private String body;

	public Article(String title, String body) {
		this.title = title;
		this.body = body;
	}

	public String title() {
		return this.title;
	}

	public String body() {
		return this.body;
	}
}
