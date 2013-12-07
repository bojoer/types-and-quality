package carlosgsouza.vinylshop.controller

import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.model.Genre
import carlosgsouza.vinylshop.model.Vinyl

class GenreController {
	
	DB db = DB.connect()
	
	List<String> list() {
		db.genres
	}
	
	List<Genre> search(String genreName) {
		db.searchVinylByGenre(genreName)
	}
	
}