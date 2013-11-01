package carlosgsouza.vinylshop.controller

import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.model.Summary

class SummaryController {
	
	DB db = DB.connect()
	
	Summary show() {
		Summary result = new Summary()
		
		def vinyls = db.all
		
		result.vinylCount = vinyls.size()
		result.artistCount = vinyls*.artist.unique().size()
		result.genreCount = vinyls*.genre.unique().size()
		result.songCount = vinyls*.songs.flatten().size()
		
		return result
	}
	
}