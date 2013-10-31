package carlosgsouza.vinylshop.view

import carlosgsouza.derails.Form
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.model.Vinyl



public class UiFactory {
	
	public View listVinyls(List<Vinyl> vinylList) {
		def items = []
		
		items.add "Listing $vinylList.size items"
		items.addAll vinylList
		
		new View(items)
	}
	
	public View searchVinyls(String query, List<Vinyl> vinylList) {
		def items = []
		
		items.add "Listing $vinylList.size items matching '$query'"
		items.addAll vinylList
		
		new View(items)
	}
	
	public View showVinyl(Vinyl vinyl) {
		new View(vinyl)
	}
	
	public View deleteVinyls() {
		new View("Vinyl deleted")
	}
	
	public View createVinyl() {
		new View("Item created")
	}
	
	public Form vinylForm() {
		new Form("Please enter the vinyl details below", "Artist", "Title", "Songs", "Year", "Genre")
	}
	
	public View listArtists(List<String> artists) {
		def items = []
		
		items.add "Listing $artists.size artists"
		items.addAll artists
		
		new View(items)
	}
}
