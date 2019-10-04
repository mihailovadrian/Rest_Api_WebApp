package com.rest.app.messenger.models;

import java.util.Date;

public class Comment {
	private Long id;
	private String author;
	private Date date;
	private String message;

	public Comment() {
		super();
	}

	public Comment(Long id, String author, Date date, String message) {
		super();
		this.id = id;
		this.author = author;
		this.date = date;
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
