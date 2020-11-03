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
		datePublishing = LocalDate.now();
		timePublishing = LocalTime.now();
	}

	public Crick(String pMessage, String pArtist, LocalDate pDate, LocalTime pTime) {

		datePublishing = pDate;
		timePublishing = pTime;
		message = pMessage;
		artistName = pArtist;
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
	
	@Override
	public String toString() {
		return "Crick [artist=" + artist + ", datePublishing=" + datePublishing + ", timePublishing=" + timePublishing
				+ ", message=" + message + ", artistName=" + artistName + "]";
	}

}
