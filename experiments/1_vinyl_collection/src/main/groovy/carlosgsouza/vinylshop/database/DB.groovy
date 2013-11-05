package carlosgsouza.vinylshop.database

import carlosgsouza.vinylshop.model.Artist
import carlosgsouza.vinylshop.model.Vinyl

class DB {
	List<Vinyl> vinyls = []
	List<Artist> artists = []
	
	private static DB instance = new DB()
	
	private DB() {}
	
	Vinyl getVinyl(id) {
		vinyls.find{ it?.id == id }
	}
	
	Integer addVinyl(Vinyl vinyl) {
		vinyl.id = vinyl.id ?: maxId + 1
		vinyls << vinyl
		
		def existingArtist = artists.find{ it.name == vinyl.artist }
		if(existingArtist) {
			existingArtist.vinyls << vinyl
		} else {
			artists << new Artist(name:vinyl.artist, vinyls:[vinyl])
		}
		
		return vinyl.id
	}
	
	List<String> getYears() {
		vinyls*.year.unique()
	}
	
	List<String> getGenres() {
		vinyls*.genre.unique()
	}
	
	List<String> getSongs() {
		vinyls*.songs.flatten().unique()
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
		vinyls.find{it.id == id} != null
	}
	
	private getMaxId() {
		vinyls*.id.max() ?: 0
	}
	
	public List<Vinyl> searchVinylByTitle(title) {
		vinyls.findAll{ it.title.toLowerCase().contains(title.toLowerCase()) }
	}
	
	public List<Vinyl> searchVinylByGenre(genre) {
		vinyls.findAll{ it.genre.toLowerCase().contains(genre.toLowerCase()) }
	}
	
	public List<Vinyl> searchVinylByArtist(artist) {
		vinyls.findAll{ it.artist.toLowerCase().contains(artist.toLowerCase()) }
	}
	
	public List<Vinyl> searchVinylByYear(year) {
		vinyls.findAll{ it.year.toLowerCase().contains(year.toLowerCase()) }
	}
	
	public List<Vinyl> searchVinylBySong(song) {
		vinyls.findAll{ vinyl -> vinyl.songs*.toLowerCase().find { songName -> songName.contains(song.toLowerCase()) }
		}
	}
	
	public static DB connect() {
		instance
	}
}