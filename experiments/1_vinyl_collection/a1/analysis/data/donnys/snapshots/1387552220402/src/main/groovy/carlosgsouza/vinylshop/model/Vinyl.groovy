package carlosgsouza.vinylshop.model;

import java.util.*;

public class Vinyl {
	public id;
	public artist;
	public title;
	public songs;
	public year;
	public genre;
	
	public Vinyl() {
		
	}
	
	public Vinyl(artist, title, songs, year, genre) {
		this.artist = artist;
		this.title = title;
		this.songs = songs;
		this.year = year;
		this.genre = genre;
	}

	@Override
	public String toString() {
			"id:"+id+
			", artist:"+artist+
			", title:"+title+
			", songs:"+songs+
			", year:"+year+
			", genre:"+genre;
	}

	public isValid() {
		return notEmpty(artist) && notEmpty(title) && notEmpty(year) && notEmpty(genre) && notEmpty(songs);
	}
	
	public notEmpty(element) {
		if(element instanceof String) {
			return element != null && !element.isEmpty();
		} else {
			if(element == null || element.size() == 0) {
				return false;
			} else {
				for(def i : element) {
					if(notEmpty(i)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.is(obj) )
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		def other = obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (songs == null) {
			if (other.songs != null)
				return false;
		} else if (!songs.equals(other.songs)) {
			return false;
		}
			
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
}