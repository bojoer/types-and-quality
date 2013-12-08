package carlosgsouza.vinylshop.controller

import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.model.Vinyl

public class SongController {
	
	DB db = DB.connect()
	
	List<String> list() {
		return db.songs
	}
	
}