package carlosgsouza.vinylshop.controller

import carlosgsouza.vinylshop.database.DB

class SongController {
	
	DB db = DB.connect()
	
	List<String> list() {
		db.allSongs
	}
	
	List<String> search(String Song) {
		db.searchVinylBySong(Song)
	}
	
}