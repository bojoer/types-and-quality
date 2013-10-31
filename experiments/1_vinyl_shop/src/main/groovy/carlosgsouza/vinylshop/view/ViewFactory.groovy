package carlosgsouza.vinylshop.view

import carlosgsouza.derails.View
import carlosgsouza.vinylshop.model.Vinyl



public class ViewFactory {
	
	public View list(List<Vinyl> vinylList) {
		def items = []
		
		items.add "Listing $vinylList.size items"
		items.addAll vinylList
		
		new View(items)
	}
	
	public View show(Vinyl vinyl) {
		new View(vinyl)
	}
	
	public View delete() {
		new View("Vinyl deleted")
	}
	
	public View create() {
		new View("Item created")
	}
	
	
}
