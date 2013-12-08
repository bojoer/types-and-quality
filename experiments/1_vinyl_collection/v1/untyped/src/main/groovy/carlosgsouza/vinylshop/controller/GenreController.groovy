package carlosgsouza.vinylshop.controller;

import java.util.List;

import carlosgsouza.vinylshop.database.DB;
import carlosgsouza.vinylshop.model.Vinyl;

public class GenreController {
	
	def db = DB.connect();
	
	public list() {
		return db.getGenres();
	}
	
	public search(String genreName) {
		return db.searchVinylByGenre(genreName);
	}
	
}