package carlosgsouza.vinylshop.controller

import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.model.Genre
import carlosgsouza.vinylshop.model.Vinyl

class GenreController {
	
	DB db = DB.connect()
	
	List<Genre> list() {
		db.genres
	}
	
	List<Vinyl> search(String Genre) {
		db.searchVinylByGenre(Genre)
	}
	
}