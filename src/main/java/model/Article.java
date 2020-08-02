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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Article)) {
			return false;
		}
		Article other = (Article) obj;
		if (body == null) {
			if (other.body != null) {
				return false;
			}
		} else if (!body.equals(other.body)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}


}
