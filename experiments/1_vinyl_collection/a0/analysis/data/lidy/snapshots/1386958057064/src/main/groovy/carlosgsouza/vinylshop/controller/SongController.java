package carlosgsouza.vinylshop.controller;

import java.util.List;

import carlosgsouza.vinylshop.database.DB;

public class SongController {
	
	DB db = DB.connect();
	
	public List<String> list() {
		return db.getSongs();
	}
	
}