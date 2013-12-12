package carlosgsouza.vinylshop.model;

import java.util.*;

public class Vinyl {
	public Integer id;
	public String artist;
	public String title;
	public List<String> songs;
	public String year;
	public String genre;
	
	public Vinyl() {
		
	}
	
	public Vinyl(String artist, String title, List<String> songs, String year, String genre) {
		this.artist = artist;
		this.title = title;
		this.songs = songs;
		this.year = year;
		this.genre = gener;
	}

	@Override
	public String toString() {
		return 
			"id:"+id+
			", artist:"+artist+
			", title:"+title+
			", songs:"+songs+
			", year:"+year+
			", genre:"+genre;
	}

	public boolean isValid() {
		return notEmpty(artist) && notEmpty(title) && notEmpty(year) && notEmpty(genre) && notEmpty(songs);
	}
	
	public boolean notEmpty(String str) {
		return str != null && !str.isEmpty();
	}

	public boolean notEmpty(List<String> list) {
		if(list == null || list.size() == 0) {
			return false;
		} else {
			for(String item : list) {
				if(notEmpty(item)) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean equals(Object obi) {
		if (this == obi )
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vinyl other = (Vinyl) obj;
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