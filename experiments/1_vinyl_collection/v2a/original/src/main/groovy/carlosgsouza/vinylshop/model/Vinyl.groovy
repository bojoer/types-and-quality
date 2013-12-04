package carlosgsouza.vinylshop.model

class Vinyl {
	Integer id
	String artist
	String title
	List<String> songs
	String year
	String genre
	
	@Override
	public String toString() {
		"id:$id, artist:$artist, title:$title, songs:$songs, year:$year, genre:$genre"
	}

	boolean isValid() {
		def anyNonListEmpty = [artist, title, year, genre].findAll{ !it }
		def atLeastOneSongIsValid = songs.find { it }
		
		return !anyNonListEmpty && atLeastOneSongIsValid
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this.is(obj))
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
		} else if (!songs.equals(other.songs))
			return false;
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