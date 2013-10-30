package carlosgsouza.vinylshop.database

import carlosgsouza.vinylshop.model.Vinyl

class DB {
	private List<DB> vinyls = []
	
	List<Vinyl> getAll() {
		vinyls
	}
	
	Vinyl get(id) {
		vinyls.find{ it?.id == id }
	}
	
	Integer add(Vinyl vinyl) {
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
	
	public List<Vinyl> searchVinyl(title) {
		vinyls.findAll{ it.title.toLowerCase().contains(title.toLowerCase()) }
	}
}