package com.ssafy.movie.model.dto;

import java.io.Serializable;

public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static int n = 1;
	private int id;
	private String title, director;
	private int runningTime;
	private String genre, desc, openDate, posterUrl;
	
	public Movie() {}

	public Movie(String title, String director, int runningTime, String genre, String desc, String openDate, String posterUrl) {
		this.id = n++;
		this.title = title;
		this.director = director;
		this.runningTime = runningTime;
		this.genre = genre;
		this.desc = desc;
		this.openDate = openDate;
		this.posterUrl = posterUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", runningTime=" + runningTime
				+ ", genre=" + genre + ", desc=" + desc + ", posterUrl=" + posterUrl + "]";
	}

}
