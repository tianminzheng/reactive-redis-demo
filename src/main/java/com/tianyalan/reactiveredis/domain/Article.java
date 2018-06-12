package com.tianyalan.reactiveredis.domain;

import org.springframework.data.annotation.Id;

public class Article {
    @Id
    private String id;
    private String title;
    private String content;
    private String author;

    public Article(String id, String title, String content, String author) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
	}

	public Article() {
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
