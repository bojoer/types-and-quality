package carlosgsouza.vinylshop.controller

import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.model.Vinyl

class YearController {
	
	DB db = DB.connect()
	
	List<Vinyl> search(String Year) {
		db.searchVinylByYear(Year)
	}
	
}