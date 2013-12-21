package carlosgsouza.vinylshop.controller;

import java.util.List;

import carlosgsouza.vinylshop.database.DB;
import carlosgsouza.vinylshop.model.Artist;
import carlosgsouza.vinylshop.model.Vinyl;

public class ArtistController {
	
	def db = DB.connect();
	
	public list() {
		return db.getArtists();
	}
	
	public search(artist) {
		return db.searchVinylByArtist(artist);
	}
	
}