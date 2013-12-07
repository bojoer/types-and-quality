package carlosgsouza.vinylshop.controller;

import java.util.List;

import carlosgsouza.vinylshop.database.DB;
import carlosgsouza.vinylshop.model.Vinyl;

public class YearController {
	
	DB db = DB.connect();
	
	List<Vinyl> search(String Year) {
		return db.searchVinylByYear(Year);
	}
	
}