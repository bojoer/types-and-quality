package carlosgsouza.vinylshop.controller

import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.model.Vinyl

class VinylController {
	
	DB db = new DB()
	
	List<Vinyl> list() {
		db.all
	}
	
	Vinyl show(Integer id) {
		if(!id) {
			throw new IllegalArgumentException("Can't show vinyl with null id")
		}
		
		if(!db.contains(id)) {
			throw new IllegalArgumentException("Vinyl doesn't exist")
		}
		
		db.get(id)
	}
	
	Integer create(Vinyl vinyl) {
		if(!vinyl?.valid) {
			throw new IllegalArgumentException("Can't create invalid vinyl")
		}
		
		db.add(vinyl)
	}
	
	void delete(Integer id) {
		if(!id) {
			throw new IllegalArgumentException("Can't delete null vinyl")
		}
		if(!db.contains(id)) {
			throw new IllegalArgumentException("Vinyl doesn't exist")
		}
		
		db.remove(id)
	}
	
}