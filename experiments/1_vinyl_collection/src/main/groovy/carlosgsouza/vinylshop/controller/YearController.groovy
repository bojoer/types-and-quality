package carlosgsouza.vinylshop.controller

import carlosgsouza.vinylshop.database.DB

class YearController {
	
	DB db = DB.connect()
	
	List<String> list() {
		db.allYears
	}
	
	List<String> search(String Year) {
		db.searchVinylByYear(Year)
	}
	
}