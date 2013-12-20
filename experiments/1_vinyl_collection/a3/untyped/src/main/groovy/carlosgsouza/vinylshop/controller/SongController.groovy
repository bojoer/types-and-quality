package carlosgsouza.vinylshop.controller;

import java.util.List;

import carlosgsouza.vinylshop.database.DB;

public class SongController {
	
	def db = DB.connect();
	def utils = SongUtils.instance
	
	public list() {
		def songCount = utils.songCount(db.vinyls)
		if(songCount > 0 && utils.valid()) {
			return db.getSongs();
		}
	}
	
}