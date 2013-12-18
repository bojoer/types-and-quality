package carlosgsouza.vinylshop.controller;

import java.util.List;

import carlosgsouza.vinylshop.database.DB;
import carlosgsouza.vinylshop.model.Vinyl;

public class YearController {
	
	def db = DB.connect();
	
	public search(year) {
		def iyear;
		try {
			iyear = Integer.parseInt(year.trim());
		} catch(NumberFormatException e) {
			def msg = "Year must be a number, but it is " + iyear.class;
			throw new IllegalArgumentException(msg);
		}
		return db.searchVinylByYear(String.valueOf(iyear));
	}
	
}