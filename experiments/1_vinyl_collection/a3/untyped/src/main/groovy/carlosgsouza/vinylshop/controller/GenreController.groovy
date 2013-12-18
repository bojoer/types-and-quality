package carlosgsouza.vinylshop.controller;

import java.util.ArrayList;
import java.util.List;

import carlosgsouza.vinylshop.database.DB;
import carlosgsouza.vinylshop.model.Vinyl;

public class GenreController {
	
	def db = DB.connect();
	
	public list() {
		return db.getGenres();
	}
	
	public search(String genreName) {
		def result = db.searchVinylByGenre(genreName);
		
		// TODO return vinyls
		def temp = new ArrayList<String>();
		for(def v : result) {
			temp.add(v.title);
		}
		return temp;
	}
}