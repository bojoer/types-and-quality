package carlosgsouza.vinylshop.controller

import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.model.Artist
import carlosgsouza.vinylshop.model.Vinyl

class ArtistController {
	
	DB db = DB.connect()
	
	List<Artist> list() {
		db.artists
	}
	
	List<Vinyl> search(String artist) {
		db.searchVinylByArtist(artist)
	}
	
}