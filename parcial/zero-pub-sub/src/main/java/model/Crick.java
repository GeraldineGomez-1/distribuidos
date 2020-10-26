package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Crick {

	private Artist artist;
	private LocalDate datePublishing;
	private LocalTime timePublishing;
	private String message;
	private String artistName;

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public Crick() {

	}

	public Crick(String pMessage, Artist pArtist) {

		datePublishing = LocalDate.now();
		timePublishing = LocalTime.now();
		artist = pArtist;
		message = pMessage;
		artistName = pArtist.getName();
	}


	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public LocalDate getDatePublishing() {
		return datePublishing;
	}

	public void setDatePublishing(LocalDate datePublishing) {
		this.datePublishing = datePublishing;
	}

	public LocalTime getTimePublishing() {
		return timePublishing;
	}

	public void setTimePublishing(LocalTime timePublishing) {
		this.timePublishing = timePublishing;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
