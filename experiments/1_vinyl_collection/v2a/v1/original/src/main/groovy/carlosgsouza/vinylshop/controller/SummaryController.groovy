package carlosgsouza.vinylshop.controller

import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.model.Summary
import carlosgsouza.vinylshop.model.Vinyl

public class SummaryController {
	
	DB db = DB.connect()
	
	Summary show() {
		Summary result = new Summary()
		
		result.vinylCount = db.vinyls.size()
		result.artistCount = db.artists.size()
		result.genreCount = db.genres.size()
		result.songCount = songCount(db.vinyls)
		
		return result
	}
	
	private int songCount(List<Vinyl> vinyls) {
		int result = 0
		
		for(Vinyl vinyl in vinyls) {
			result += vinyl.songs.size()
		}
		
		return result
	}
	
}