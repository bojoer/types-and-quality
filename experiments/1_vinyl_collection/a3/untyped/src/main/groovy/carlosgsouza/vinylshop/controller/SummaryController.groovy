package carlosgsouza.vinylshop.controller;

import java.util.List;

import carlosgsouza.vinylshop.database.DB;
import carlosgsouza.vinylshop.model.Summary;
import carlosgsouza.vinylshop.model.Vinyl;

public class SummaryController {
	
	def db = DB.connect();
	
	public show() {
		def result = new Summary();
		
		result.vinylCount = db.vinyls.size();
		result.artistCount = db.artists.size();
		result.genreCount = db.genres.size();
		result.songCount = SongUtils.instance.songCount(db.vinyls);
		
		return result;
	}
	
	
}