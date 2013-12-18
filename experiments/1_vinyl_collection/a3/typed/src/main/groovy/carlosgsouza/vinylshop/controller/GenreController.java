package carlosgsouza.vinylshop.controller;

import java.util.ArrayList;
import java.util.List;

import carlosgsouza.vinylshop.database.DB;
import carlosgsouza.vinylshop.model.Vinyl;

public class GenreController {
	
	DB db = DB.connect();
	
	public List<String> list() {
		return db.getGenres();
	}
	
	public List<String> search(String genreName) {
		List<Vinyl> result = db.searchVinylByGenre(genreName);
		
		// TODO return vinyls
		List<String> temp = new ArrayList<String>();
		for(Vinyl v : result) {
			temp.add(v.title);
		}
		return temp;
	}
	
}