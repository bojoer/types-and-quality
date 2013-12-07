package carlosgsouza.vinylshop.controller

import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.model.Vinyl

class VinylController {
	
	DB db = DB.connect()
	
	List<Vinyl> list() {
		db.vinyls
	}
	
	Vinyl get(Integer id) {
		if(!id) {
			throw new IllegalArgumentException("Can't show vinyl with null id")
		}
		
		if(!db.containsVinyl(id)) {
			throw new IllegalArgumentException("Vinyl doesn't exist")
		}
		
		db.getVinyl(id)
	}
	
	Integer create(Map fields) {
		if(!fields) {
			throw new IllegalArgumentException("Can't create invalid vinyl")
		}
		
		def vinyl = new Vinyl(
			artist:fields["Artist"],
			title:fields["Title"],
			songs:fields["Songs"].split(",")*.trim(),
			year:fields["Year"],
			genre:fields["Genre"])
		
		return create(vinyl)
	}
	
	Integer create(Vinyl vinyl) {
		if(!vinyl?.valid) {
			throw new IllegalArgumentException("Can't create invalid vinyl")
		}
		
		db.addVinyl(vinyl)
	}
	
	void delete(Integer id) {
		if(!id) {
			throw new IllegalArgumentException("Can't delete null vinyl")
		}
		if(!db.containsVinyl(id)) {
			throw new IllegalArgumentException("Vinyl doesn't exist")
		}
		
		db.removeVinyl(id)
	}
	
	List<Vinyl> search(String title) {
		if(!title) {
			throw new IllegalArgumentException("Must provide a title for the vinyl search")
		}
		
		db.searchVinylByTitle(title)
	}
	
}