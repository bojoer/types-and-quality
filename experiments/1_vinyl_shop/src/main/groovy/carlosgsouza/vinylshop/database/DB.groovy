package carlosgsouza.vinylshop.database

import carlosgsouza.vinylshop.model.Vinyl

class DB {
	private List<Vinyl> vinyls = []
	
	private static DB instance = new DB()
	
	private DB() {}
	
	List<Vinyl> getAll() {
		vinyls
	}
	
	Vinyl get(id) {
		vinyls.find{ it?.id == id }
	}
	
	Integer add(Vinyl vinyl) {
		vinyl.id = vinyl.id ?: maxId + 1
		vinyls << vinyl
		
		return vinyl.id
	}
	
	List<String> getAllArtists() {
		vinyls*.artist.unique()
	}
	
	void remove(Integer id) {
		vinyls.remove(vinyls.find{it.id == id})
	}
	
	boolean contains(id) {
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