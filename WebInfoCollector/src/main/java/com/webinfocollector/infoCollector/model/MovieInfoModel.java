package com.webinfocollector.infoCollector.model;

import java.io.Serializable;

public class MovieInfoModel implements Serializable {

	private static final long serialVersionUID = -7186168134506023976L;

	private String movieName;
	private String rate;
	private String voteCount;
	private String descripton;
	private String[] genres;
	private String[] director;
	private String[] casts;
	private String releaseYear;
	private byte[] movieCover;
	private String filePath;
	private String fileName;

	public byte[] getMovieCover() {
		return movieCover;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String path) {
		this.filePath = path;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setMovieCover(byte[] movieCover) {
		this.movieCover = movieCover;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(String voteCount) {
		this.voteCount = voteCount;
	}

	public String getDescripton() {
		return descripton;
	}

	public void setDescripton(String descripton) {
		this.descripton = descripton;
	}

	public String[] getGenres() {
		return genres;
	}

	public void setGenres(String[] genres) {
		this.genres = genres;
	}

	public String[] getDirector() {
		return director;
	}

	public void setDirector(String[] director) {
		this.director = director;
	}

	public String[] getCasts() {
		return casts;
	}

	public void setCasts(String[] casts) {
		this.casts = casts;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

}
