package carlosgsouza.vinylshop.database

import carlosgsouza.vinylshop.model.Artist
import carlosgsouza.vinylshop.model.Vinyl

class DB {
	List<Vinyl> vinyls = []
	List<Artist> artists = []
	
	private static DB instance = new DB()
	
	private DB() {}
	
	Vinyl getVinyl(id) {
		for(vinyl in vinyls) {
			if(vinyl && vinyl.id == id) {
				return vinyl
			}
		}
		
	}
	
	private Artist findArtist(String name) {
		for(artist in artists) {
			if(artist && artist.name == name) {
				return artist
			}
		}
	}
	
	Integer addVinyl(Vinyl vinyl) {
		vinyl.id = vinyl.id ?: maxId + 1
		vinyls << vinyl
		
		def existingArtist = findArtist(vinyl.artist) 
		if(existingArtist) {
			existingArtist.vinyls << vinyl
		} else {
			artists << new Artist(name:vinyl.artist, vinyls:[vinyl])
		}
		
		return vinyl.id
	}
	
	
	
	List<String> getYears() {
		Set<String> uniqueEntries = new TreeSet<String>()
		
		for(vinyl in vinyls) {
			uniqueEntries.add(vinyl.year)	
		}
		
		return new ArrayList<String>(uniqueEntries)
	}
	
	List<String> getGenres() {
		Set<String> uniqueEntries = new TreeSet<String>()
		
		for(vinyl in vinyls) {
			uniqueEntries.add(vinyl.genre)	
		}
		
		return new ArrayList<String>(uniqueEntries)
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
		Vinyl vinyl = vinyls.find{it.id == id}
		
		vinyls.remove(vinyl)
		
		Artist artist = artists.find{it.name == vinyl.artist}
		if(artist.vinyls.size() == 1) {
			artists.remove(artist)
		} else {
			artist.vinyls.remove(vinyl)
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
	
	public List<Vinyl> searchVinylByGenre(String genre) {
		List<Vinyl> result = new ArrayList<Vinyl>()
		
		
		if(genre) {
			for(vinyl in vinyls) {
				if(vinyl.genre && vinyl.genre.toLowerCase().contains(genre.toLowerCase())) {
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
	
	public List<Vinyl> searchVinylBySong(String songName) {
		List<Vinyl> result = new ArrayList<Vinyl>()
		
		if(songName) {
			for(vinyl in vinyls) {
				for(song in vinyl.songs) {
					if(song && song.toLowerCase().contains(songName.toLowerCase())) {
						result.add(vinyl)
						break
					}
				}
			}
		}
		
		return result
	}
	
	public static DB connect() {
		instance
	}
}