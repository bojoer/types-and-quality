package carlosgsouza.vinylshop.controller;

import java.util.List;

import carlosgsouza.vinylshop.database.DB;

public class SongController {
	
	def db = DB.connect();
	
	public list() {
		return db.getSongs();
	}
	
}