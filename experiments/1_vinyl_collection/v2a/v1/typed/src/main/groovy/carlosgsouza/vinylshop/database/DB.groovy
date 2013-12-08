package carlosgsouza.vinylshop.database

import carlosgsouza.vinylshop.model.Artist
import carlosgsouza.vinylshop.model.Genre
import carlosgsouza.vinylshop.model.Vinyl

class DB {
	public List<Vinyl> vinyls = []
	public List<Artist> artists = []
	public List<Genre> genres = []

	private static DB instance = new DB()

	private DB() {

	}
	
	List<Vinyl> getVinyls() {
		return this.vinyls;
	}

	Vinyl getVinyl(id) {
		for(vinyl in vinyls) {
			if(vinyl && vinyl.id == id) {
				return vinyl
			}
		}
	}

	private Artist findArtist(name) {
		for(artist in artists) {
			if(artist && artist.name == name) {
				return artist
			}
		}
	}

	private Genre findGenre(String name) {
		for(genre in genres) {
			if(genre && genre.name == name) {
				return genre
			}
		}
	}

	Integer addVinyl(Vinyl vinyl) {
		vinyl.id = vinyl.id ?: maxId + 1
		vinyls << vinyl

		addOrUpdateArtist(vinyl)
		addOrUpdateGenre(vinyl)

		return vinyl.id
	}

	private void addOrUpdateArtist(Vinyl vinyl) {
		def existingArtist = findArtist(vinyl.artist)
		if(existingArtist) {
			existingArtist.vinyls << vinyl
		} else {
			artists << new Artist(name:vinyl.artist, vinyls:[vinyl])
		}
	}

	private void addOrUpdateGenre(Vinyl vinyl) {
		def existingGenre = findGenre(vinyl.genre)
		if(existingGenre) {
			existingGenre.vinyls << vinyl
		} else {
			genres << new Genre(name:vinyl.genre, vinyls:[vinyl])
		}
	}

	List<String> getSongs() {
		Set<String> uniqueEntries = new TreeSet<String>()

		for(vinyl in vinyls) {
			for(song in vinyl.songs) {
				uniqueEntries.add(song)
			}
		}

		return new ArrayList<String>(uniqueEntries)
	}

	void removeVinyl(Integer id) {
		Vinyl vinyl = getVinyl(id)

		vinyls.remove(vinyl)

		removeOrUpdateArtist(vinyl)
		removeOrUpdateGenre(vinyl)
	}

	private removeOrUpdateArtist(Vinyl vinyl) {
		Artist artist = findArtist(vinyl.artist)
		if(artist.vinyls.size() == 1) {
			artists.remove(artist)
		} else {
			artist.vinyls.remove(vinyl)
		}
	}

	private removeOrUpdateGenre(Vinyl vinyl) {
		Genre genre = findGenre(vinyl.genre)
		if(genre.vinyls.size() == 1) {
			genres.remove(genre)
		} else {
			genre.vinyls.remove(vinyl)
		}
	}

	boolean containsVinyl(id) {
		getVinyl(id) != null
	}

	private getMaxId() {
		int max = 0

		for(vinyl in vinyls) {
			if(vinyl.id && vinyl.id > max) {
				max = vinyl.id
			}
		}

		return max
	}

	public List<Vinyl> searchVinylByTitle(String title) {
		List<Vinyl> result = new ArrayList<Vinyl>()


		if(title) {
			for(vinyl in vinyls) {
				if(vinyl.title && vinyl.title.toLowerCase().contains(title.toLowerCase())) {
					result.add(vinyl)
				}
			}
		}

		return result
	}

	public List<Vinyl> searchVinylByGenre(String name) {
		List<Vinyl> result = new ArrayList<Vinyl>()


		if(name) {
			for(vinyl in vinyls) {
				if(vinyl.genre && vinyl.genre.toLowerCase().contains(name.toLowerCase())) {
					result.add(vinyl)
				}
			}
		}

		return result
	}

	public List<Vinyl> searchVinylByArtist(String name) {
		List<Vinyl> result = new ArrayList<Vinyl>()

		if(name) {
			for(vinyl in vinyls) {
				if(vinyl.artist && vinyl.artist.toLowerCase().contains(name.toLowerCase())) {
					result.add(vinyl)
				}
			}
		}

		return result
	}

	public List<Vinyl> searchVinylByYear(year) {
		List<Vinyl> result = new ArrayList<Vinyl>()


		if(year) {
			for(vinyl in vinyls) {
				if(vinyl.year && vinyl.year.toLowerCase().contains(year.toLowerCase())) {
					result.add(vinyl)
				}
			}
		}

		return result
	}


	public List getArtists() {
		List result = artists
		return artists*.name
	}


	public List getGenres() {
		genres*.name
	}

	public static DB connect() {
		instance
	}

	public void reset() {
		artists = []
		vinyls = []
		genres = []
	}
}