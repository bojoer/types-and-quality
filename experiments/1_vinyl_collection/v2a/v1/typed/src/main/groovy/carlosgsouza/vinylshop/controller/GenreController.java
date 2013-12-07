package carlosgsouza.vinylshop.controller;

import java.util.List;

import carlosgsouza.vinylshop.database.DB;
import carlosgsouza.vinylshop.model.Vinyl;

public class GenreController {
	
	DB db = DB.connect();
	
	List<String> list() {
		return db.getGenres();
	}
	
	List<Vinyl> search(String genreName) {
		return db.searchVinylByGenre(genreName);
	}
	
}