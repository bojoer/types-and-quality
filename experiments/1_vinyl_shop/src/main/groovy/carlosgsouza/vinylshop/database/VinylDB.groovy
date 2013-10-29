package carlosgsouza.vinylshop.database

import carlosgsouza.vinylshop.model.Vinyl

class VinylDB {
	private List<VinylDB> vinyls = []
	
	List<Vinyl> getAll() {
		vinyls
	}
	
	Vinyl get(id) {
		vinyls.find{ it?.id == id }
	}
	
	Integer add(Vinyl vinyl) {
		if(!vinyl.valid) {
			throw new IllegalArgumentException("Trying to add an invalid vinyl")
		}
		
		vinyl.id = vinyl.id ?: maxId + 1
		vinyls << vinyl
		
		return vinyl.id
	}
	
	void remove(Integer id) {
		vinyls.remove(vinyls.find{it.id == id})
	}
	
	boolean contains(id) {
		vinyls.find{it.id == id} != null
	}
	
	private getMaxId() {
		vinyls*.id.max() ?: 0
	}
}