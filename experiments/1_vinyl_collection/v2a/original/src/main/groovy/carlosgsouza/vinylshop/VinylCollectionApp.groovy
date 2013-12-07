 package carlosgsouza.vinylshop

import carlosgsouza.derails.App
import carlosgsouza.derails.Form
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.controller.*
import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.form.VinylForm
import carlosgsouza.vinylshop.model.Vinyl
import carlosgsouza.vinylshop.view.*

class VinylCollectionApp extends App {
	
	static VinylCollectionApp app = new VinylCollectionApp()
	
	VinylController vinylController = new VinylController()
	ArtistController artistController = new ArtistController()
	YearController yearController = new YearController()
	GenreController genreController = new GenreController()
	SongController songController = new SongController()
	SummaryController summaryController = new SummaryController()
	ReportController reportController = new ReportController()
	
	DB db = DB.connect()
	
	List<Vinyl> preloadedVinyls = [
			new Vinyl(artist:"Lana Del Rey", title:"Born to Die", songs:["Off to Races", "Radio", "Carmen"], year:"2012", genre:"Pop"),
			new Vinyl(artist:"Bruno Mars", title:"Unorthodox Jukebox", songs:["Gorilla", "Treasure", "Young Girls"], year:"2012", genre:"Pop"),
			new Vinyl(artist:"Pearl Jam", title:"Lightning Bolt", songs:["Getaway", "Mind Your Manners", "Young Sirens"], year:"2013", genre:"Rock"),
			new Vinyl(artist:"Angra", title:"Temple of Shadows", songs:["Deus Le Volt!", "Waiting Silence"], year:"2004", genre:"Metal"),
			new Vinyl(artist:"Luan Santana", title:"Quando Chega a Noite", songs:["Te vivo", "Quimica do Amor"], year:"2010", genre:"Rock"),
			new Vinyl(artist:"Coldplay", title:"Parachutes", songs:["Don't Panic", "Shiver", "Spies"], year:"2000", genre:"Alternative"),
			new Vinyl(artist:"Pearl Jam", title:"Backspacer", songs:["Just Breathe", "Supersonic"], year:"2009", genre:"Rock")]
	
	VinylCollectionApp() {
		super("DJ PopCorn - Amazing Vinyl Collection")
	}
	
	void routeRequest(String controller, String action, String parameter) {
		if(controller == "vinyl") {
			switch(action) {
				case "list":
					def vinyls = vinylController.list()
					console.render(new ListVinylsView(vinyls))
					return
				case "create":
					Form form = new VinylForm()
					console.apply form
					def id = vinylController.create(form.fields)
					def createdVinyl = vinylController.get(id)
					
					console.render new ShowVinylView(createdVinyl)
					return
				case "show":
					def id = Integer.valueOf parameter
					def vinyl = vinylController.get(id)
					
					console.render new ShowVinylView(vinyl)
					return
				case "delete":
					def id = Integer.valueOf parameter
					vinylController.delete(id)
					
					console.render new DeleteVinylView()
					return
				case "search":
					def result = vinylController.search(parameter)
					console.render new SearchByTitleView(parameter, result)
					return
			}
		} else if(controller == "artist") {
			switch(action) {
				case "list":
					def artists = artistController.list()
					console.render new ListArtistsView(artists)
					return
				case "search":
					def artists = artistController.search(parameter)
					console.render new SearchByArtistView(parameter, artists)
					return
			}
		} else if(controller == "song") {
			switch(action) {
				case "list":
					def artists = songController.list()
					console.render new ListSongsView(artists)
					return
			}
		} else if(controller == "year") {
			switch(action) {
				case "search":
					def years = yearController.search(parameter)
					console.render new SearchByYearView(parameter, years)
					return
			}
		} else if(controller == "genre") {
			switch(action) {
				case "list":
					def genres = genreController.list()
					console.render new ListGenresView(genres)
					return
				case "search":
					def genres = genreController.search(parameter)
					console.render new SearchByGenreView(parameter, genres)
					return
			}
		} else if(controller == "summary") {
			switch(action) {
				case "show":
					def summary = summaryController.show()
					console.render new ShowSummaryView(summary)
					return
			}
		} else if(controller == "report") {
			switch(action) {
				case "artist":
					def report = reportController.artist()
					console.render(new ShowReportView("Artist", report))
					return
				case "genre":
					def report = reportController.genre()
					console.render new ShowReportView("Genre", report)
					return
			}
		}
		
		console.render(new View("command not found"))
	}
	
	void bootstrap() {
		db.reset()
		
		for(vinyl in preloadedVinyls) {
			vinylController.create(vinyl)
		}
	}
	
	public static void main(String[] args) {
		app.run()
	}
}