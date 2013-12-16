package carlosgsouza.vinylshop.controller;

import java.util.List;

import carlosgsouza.vinylshop.database.DB;
import carlosgsouza.vinylshop.model.Vinyl;

public class YearController {
	
	DB db = DB.connect();
	
	public List<Vinyl> search(String year) {
		Integer iyear;
		try {
			iyear = Integer.parseInt(iyear.trim());
		} catch(NumberFormatException e) {
			String msg = "Year must be a number, but it is " + iyear;
			throw new IllegalArgumentException(msg);
		}
		return db.searchVinylByYear(String.valueOf(year));
	}
	
}