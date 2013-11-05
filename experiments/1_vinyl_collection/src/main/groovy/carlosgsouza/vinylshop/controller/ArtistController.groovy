package carlosgsouza.vinylshop.controller

import carlosgsouza.vinylshop.database.DB

class ArtistController {
	
	DB db = DB.connect()
	
	List<String> list() {
		db.artists
	}
	
	List<String> search(String artist) {
		db.searchVinylByArtist(artist)
	}
	
}