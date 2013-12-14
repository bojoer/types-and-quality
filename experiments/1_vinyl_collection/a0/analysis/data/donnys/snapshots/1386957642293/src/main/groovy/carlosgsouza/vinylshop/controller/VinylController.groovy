package carlosgsouza.vinylshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import carlosgsouza.vinylshop.database.DB;
import carlosgsouza.vinylshop.model.Vinyl;

public class VinylController {
	
	def db = DB.connect();
	
	public list() {
		return db.getVinyls();
	}
	
	public get(id) {
		if(id == null) {
			throw new IllegalArgumentException("Can't show vinyl with null id");
		}
		
		if(!db.containsVinyl(id)) {
			throw new IllegalArgumentException("Vinyl doesn't exist");
		}
		
		return db.getVinyl(id);
	}
	
	public create(item) {
		if(item == null) {
			throw new IllegalArgumentException("Can't create invalid vinyl");
		}
		
		if(item instanceof Vinyl) {
			def vinyl = item
			if(!vinyl.isValid()) {
				throw new IllegalArgumentException("Can't create invalid vinyl");
			}
			
			return db.addVinyl(vinyl);
		} else {
			def fields = item
			
			def vinyl = new Vinyl();
			vinyl.artist = fields.get("Artist");
			vinyl.title = fields.get("Title");
			vinyl.songs = splitSongs(fields.get("Songs"));
			vinyl.year = fields.get("Year");
			vinyl.genre = fields.get("Genre");
			
			return create(vinyl);
		}
	}
	
	public splitSongs(songs) {
		def result = new ArrayList<String>();
		
		for(def song : songs.split(",")) {
			result.add(song.trim());
		}
		
		return result ;
	}
	
	public delete(id) {
		if(id == null) {
			throw new IllegalArgumentException("Can't delete null vinyl");
		}
		if(!db.containsVinyl(id)) {
			throw new IllegalArgumentException("Vinyl doesn't exist");
		}
		
		db.removeVinyl(id);
	}
	
	public search(title) {
		if(title == null || title.isEmpty()) {
			throw new IllegalArgumentException("Must provide a title for the vinyl search");
		}
		
		return db.searchVinylByTitle(title);
	}
	
}