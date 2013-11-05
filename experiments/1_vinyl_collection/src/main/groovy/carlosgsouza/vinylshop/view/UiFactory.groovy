package carlosgsouza.vinylshop.view

import carlosgsouza.derails.Form
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.model.Report
import carlosgsouza.vinylshop.model.Summary
import carlosgsouza.vinylshop.model.Vinyl



public class UiFactory {
	
	public View listVinyls(List<Vinyl> vinylList) {
		def items = []
		
		items.add "Listing $vinylList.size items"
		items.addAll vinylList
		
		new View(items)
	}
	
	public View searchByTitle(String query, List<Vinyl> vinylList) {
		def items = []
		
		items.add "Listing $vinylList.size items matching '$query'"
		items.addAll vinylList
		
		new View(items)
	}
	
	public View searchByGenre(String query, List<Vinyl> vinylList) {
		def items = []
		
		items.add "Listing $vinylList.size with genre matching '$query'"
		items.addAll vinylList
		
		new View(items)
	}
	
	
	public View searchByYear(String query, List<Vinyl> vinylList) {
		def items = []
		
		items.add "Listing $vinylList.size with year matching '$query'"
		items.addAll vinylList
		
		new View(items)
	}
	
	
	public View searchBySong(String query, List<Vinyl> vinylList) {
		def items = []
		
		items.add "Listing $vinylList.size with song matching '$query'"
		items.addAll vinylList
		
		new View(items)
	}
	
	
	public View searchByArtist(String query, List<Vinyl> vinylList) {
		def items = []
		
		items.add "Listing $vinylList.size with artist matching '$query'"
		items.addAll vinylList
		
		new View(items)
	}
	
	public View showVinyl(Vinyl vinyl) {
		new View(vinyl)
	}
	
	public View deleteVinyl() {
		new View("Vinyl deleted")
	}
	
	public View createVinyl() {
		new View("Item created")
	}
	
	public Form vinylForm() {
		new Form("Please enter the vinyl details below", "Artist", "Title", "Songs", "Year", "Genre")
	}
	
	public View listArtists(List<String> list) {
		def items = []
		
		items.add "Listing $list.size artists"
		items.addAll list
		
		new View(items)
	}
	
	public View listSongs(List<String> list) {
		def items = []
		
		items.add "Listing $list.size songs"
		items.addAll list
		
		new View(items)
	}
	
	public View listGenres(List<String> list) {
		def items = []
		
		items.add "Listing $list.size genres"
		items.addAll list
		
		new View(items)
	}
	
	public View listYears(List<String> list) {
		def items = []
		
		items.add "Listing $list.size years"
		items.addAll list
		
		new View(items)
	}
	
	public View showSummary(Summary summary) {
		def items = []
		
		items << "Collection Summary"
		items << summary
		
		new View(items)
	}
	
	public View showReport(String name, Report report) {
		def items = []
		
		items << "$name Report"
		items << report
		
		new View(items)
	}
}
