package carlosgsouza.vinylshop.view

import carlosgsouza.derails.Form
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.model.Vinyl



public class UiFactory {
	
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
	
	public Form vinylForm() {
		new Form("Please enter the vinyl details below", "Artist", "Title", "Songs", "Year", "Genre")
	}
}
