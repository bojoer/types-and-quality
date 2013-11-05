package carlosgsouza.vinylshop.controller

import carlosgsouza.vinylshop.database.DB

class GenreController {
	
	DB db = DB.connect()
	
	List<String> list() {
		db.genres
	}
	
	List<String> search(String Genre) {
		db.searchVinylByGenre(Genre)
	}
	
}