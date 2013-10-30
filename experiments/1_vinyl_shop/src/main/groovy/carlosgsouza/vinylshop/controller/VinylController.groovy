package carlosgsouza.vinylshop.controller

import carlosgsouza.vinylshop.database.VinylDB
import carlosgsouza.vinylshop.model.Vinyl

class VinylController {
	
	VinylDB db = new VinylDB()
	
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
	
	void update(Vinyl vinyl) {
		delete(vinyl)
		create(vinyl)
	}
	
	Integer create(Vinyl vinyl) {
		if(!vinyl?.valid) {
			throw new IllegalArgumentException("Can't create invalid vinyl")
		}
		
		db.add(vinyl)
	}
	
	void delete(Vinyl vinyl) {
		if(!vinyl?.id) {
			throw new IllegalArgumentException("Can't delete null vinyl")
		}
		if(!db.contains(vinyl.id)) {
			throw new IllegalArgumentException("Vinyl doesn't exist")
		}
		
		db.remove(vinyl.id)
	}
	
}