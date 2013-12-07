package carlosgsouza.vinylshop.controller;

import java.util.List;

import carlosgsouza.vinylshop.database.DB;
import carlosgsouza.vinylshop.model.Artist;
import carlosgsouza.vinylshop.model.Vinyl;

public class ArtistController {
	
	DB db = DB.connect();
	
	List<Artist> list() {
		return db.getArtists();
	}
	
	List<Vinyl> search(String artist) {
		return db.searchVinylByArtist(artist);
	}
	
}