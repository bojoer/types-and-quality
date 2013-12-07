package carlosgsouza.vinylshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import carlosgsouza.vinylshop.database.DB;
import carlosgsouza.vinylshop.model.Vinyl;

public class VinylController {
	
	DB db = DB.connect();
	
	List<Vinyl> list() {
		return db.getVinyls();
	}
	
	Vinyl get(Integer id) {
		if(id == null) {
			throw new IllegalArgumentException("Can't show vinyl with null id");
		}
		
		if(!db.containsVinyl(id)) {
			throw new IllegalArgumentException("Vinyl doesn't exist");
		}
		
		return db.getVinyl(id);
	}
	
	Integer create(Map<String, Object> fields) {
		if(fields == null) {
			throw new IllegalArgumentException("Can't create invalid vinyl");
		}
		
		Vinyl vinyl = new Vinyl();
		vinyl.artist = fields.get("Artist");
		vinyl.title = (String)fields.get("Title");
		vinyl.songs = splitSongs((String)fields.get("Songs"));
		vinyl.year = (String)fields.get("Year");
		vinyl.genre = (String)fields.get("Genre");
		
		return create(vinyl);
	}
	
	public List<String> splitSongs(String songs) {
		List<String> result = new ArrayList<String>();
		
		for(String song : songs.split(",")) {
			result.add(song);
		}
		
		return result ;
	}
	
	Integer create(Vinyl vinyl) {
		if(vinyl == null || !vinyl.isValid()) {
			throw new IllegalArgumentException("Can't create invalid vinyl");
		}
		
		return db.addVinyl(vinyl);
	}
	
	void delete(Integer id) {
		if(id == null) {
			throw new IllegalArgumentException("Can't delete null vinyl");
		}
		if(!db.containsVinyl(id)) {
			throw new IllegalArgumentException("Vinyl doesn't exist");
		}
		
		db.removeVinyl(id);
	}
	
	List<Vinyl> search(String title) {
		if(title == null || title.isEmpty()) {
			throw new IllegalArgumentException("Must provide a title for the vinyl search");
		}
		
		return db.searchVinylByTitle(title);
	}
	
}