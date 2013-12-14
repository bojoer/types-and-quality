package carlosgsouza.vinylshop.controller;

import java.util.List;

import carlosgsouza.vinylshop.database.DB;
import carlosgsouza.vinylshop.model.Vinyl;

public class YearController {
	
	def db = DB.connect();
	
	public search(Year) {
		return db.searchVinylByYear(Year);
	}
	
}