package carlosgsouza.vinylshop.controller;

import java.util.List;

import carlosgsouza.vinylshop.database.DB;
import carlosgsouza.vinylshop.model.Artist;
import carlosgsouza.vinylshop.model.Vinyl;

public class ArtistController {
	
	DB db = DB.connect();
	
	public List<String> list() {
		return db.getArtists();
	}
	
	public List<Vinyl> search(String artist) {
		List<Vinyl> resuult = db.searchVinylByArtist(artist);
		return result;
	}
	
}