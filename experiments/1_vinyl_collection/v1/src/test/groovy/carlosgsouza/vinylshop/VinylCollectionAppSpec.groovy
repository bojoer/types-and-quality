package carlosgsouza.vinylshop

import spock.lang.Specification
import carlosgsouza.derails.Console
import carlosgsouza.derails.Form
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.controller.ArtistController
import carlosgsouza.vinylshop.controller.GenreController
import carlosgsouza.vinylshop.controller.ReportController
import carlosgsouza.vinylshop.controller.SongController
import carlosgsouza.vinylshop.controller.SummaryController
import carlosgsouza.vinylshop.controller.VinylController
import carlosgsouza.vinylshop.controller.YearController
import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.model.Report
import carlosgsouza.vinylshop.model.Summary
import carlosgsouza.vinylshop.model.Vinyl
import carlosgsouza.vinylshop.view.UiFactory

class VinylCollectionAppSpec extends Specification {
	
	VinylController vinylController
	ArtistController artistController
	YearController yearController
	GenreController genreController
	SongController songController 
	SummaryController summaryController
	ReportController reportController
	
	UiFactory uiFactory
	Console console
	
	Vinyl vinylA
	Vinyl vinylB
	Vinyl vinylC
	
	Summary summary
	Report report
	
	View view
	Form vinylForm
	
	VinylCollectionApp app
	
	DB db
	
	def setup() {
		vinylA = new Vinyl(id:1, artist:"A", title:"A", songs:["A1", "A2", "A3"], year:"A", genre:"A")
		vinylB = new Vinyl(id:2, artist:"B", title:"B", songs:["B1", "B2", "B3"], year:"B", genre:"B")
		vinylC = new Vinyl(id:3, artist:"C", title:"C", songs:["C1", "C2", "C3"], year:"C", genre:"C")
		
		summary = new Summary(vinylCount:10, artistCount:5, songCount:23, genreCount:6)
		report = new Report(data:["data description":"value"])
		
		view = new View()
		
		vinylForm = new UiFactory().vinylForm()
		
		vinylController = Mock(VinylController)
		artistController = Mock(ArtistController)
		yearController = Mock(YearController)
		genreController = Mock(GenreController)
		songController = Mock(SongController)
		summaryController = Mock(SummaryController)
		reportController = Mock(ReportController)
		
		uiFactory = Mock(UiFactory)
		console = Mock(Console)
		
		db = Mock(DB)
		
		app = new VinylCollectionApp(
						reportController:reportController,
						summaryController:summaryController,
						yearController:yearController, 
						genreController:genreController, 
						songController:songController, 
						artistController: artistController, 
						vinylController:vinylController, 
						uiFactory:uiFactory, 
						console:console,
						db:db)
	}
	
	def "should reset the db and create a bunch of vinyl during bootstrap"() {
		when:
		app.bootstrap()
		
		then:
		(1.._) * vinylController.create(_)
	}
	
	def "should list vinyls"() {
		when:
		app.routeRequest("vinyl", "list", null)
		
		then:
		1 * vinylController.list() >> [vinylA, vinylB]
		1 * uiFactory.listVinyls([vinylA, vinylB]) >> view
		1 * console.render(view)
	}
	
	def "should create a vinyl"() {
		given:
		vinylForm.fields = ["artist":"", "title":"", "songs":"", "year":"", "genre":""]
		
		when:
		app.routeRequest("vinyl", "create", null)
		
		then:
		1 * uiFactory.vinylForm() >> vinylForm
		1 * console.apply(vinylForm)
		1 * vinylController.create(vinylForm.fields) >> 1
		1 * vinylController.get(1) >> vinylA
		1 * uiFactory.showVinyl(vinylA) >> view
		1 * console.render(view)
	}
	
	def "should delete a vinyl"() {
		when:
		app.routeRequest("vinyl", "delete", "1")
		
		then:
		1 * vinylController.delete(1)
		1 * uiFactory.deleteVinyl()
	}
	
	def "should show a vinyl"() {
		when:
		app.routeRequest("vinyl", "show", "1")
		
		then:
		1 * vinylController.get(1) >> vinylA
		1 * uiFactory.showVinyl(vinylA) >> view
		1 * console.render(view)
	}
	
	def "should search for a vinyl"() {
		when:
		app.routeRequest("vinyl", "search", "Album Title")
		
		then:
		1 * vinylController.search("Album Title") >> [vinylA, vinylB]
		1 * uiFactory.searchByTitle("Album Title", [vinylA, vinylB]) >> view
		1 * console.render(view)
	}
	
	def "should search for an artist"() {
		when:
		app.routeRequest("artist", "search", "Art")
		
		then:
		1 * artistController.search("Art") >> [vinylA, vinylB]
		1 * uiFactory.searchByArtist("Art", [vinylA, vinylB]) >> view
		1 * console.render(view)
	}
	
	def "should list all artists"() {
		when:
		app.routeRequest("artist", "list", null)
		
		then:
		1 * artistController.list() >> ["Artist 1", "Artist 2"]
		1 * uiFactory.listArtists(["Artist 1", "Artist 2"]) >> view
		1 * console.render(view)
	}
	
	def "should search for an genre"() {
		when:
		app.routeRequest("genre", "search", "Art")
		
		then:
		1 * genreController.search("Art") >> [vinylA, vinylB]
		1 * uiFactory.searchByGenre("Art", [vinylA, vinylB]) >> view
		1 * console.render(view)
	}
	
	def "should list all genres"() {
		when:
		app.routeRequest("genre", "list", null)
		
		then:
		1 * genreController.list() >> ["Genre 1", "Genre 2"]
		1 * uiFactory.listGenres(["Genre 1", "Genre 2"]) >> view
		1 * console.render(view)
	}
	
	def "should search for an year"() {
		when:
		app.routeRequest("year", "search", "Art")
		
		then:
		1 * yearController.search("Art") >> [vinylA, vinylB]
		1 * uiFactory.searchByYear("Art", [vinylA, vinylB]) >> view
		1 * console.render(view)
	}
	
	def "should list all years"() {
		when:
		app.routeRequest("year", "list", null)
		
		then:
		1 * yearController.list() >> ["Year 1", "Year 2"]
		1 * uiFactory.listYears(["Year 1", "Year 2"]) >> view
		1 * console.render(view)
	}
	
	def "should search for an song"() {
		when:
		app.routeRequest("song", "search", "Art")
		
		then:
		1 * songController.search("Art") >> [vinylA, vinylB]
		1 * uiFactory.searchBySong("Art", [vinylA, vinylB]) >> view
		1 * console.render(view)
	}
	
	def "should list all songs"() {
		when:
		app.routeRequest("song", "list", null)
		
		then:
		1 * songController.list() >> ["Song 1", "Song 2"]
		1 * uiFactory.listSongs(["Song 1", "Song 2"]) >> view
		1 * console.render(view)
	}
	
	def "should show a summary of the data"() {
		when:
		app.routeRequest("summary", "show", null)
		
		then:
		1 * summaryController.show() >> summary
		1 * uiFactory.showSummary(summary) >> view
		1 * console.render(view)
	}
	
	def "should show an artist report"() {
		when:
		app.routeRequest("report", "artist", null)
		
		then:
		1 * reportController.artist() >> report
		1 * uiFactory.showReport("Artist", report) >> view
		1 * console.render(view)
	}
	
	def "should show a genre report"() {
		when:
		app.routeRequest("report", "genre", null)
		
		then:
		1 * reportController.genre() >> report
		1 * uiFactory.showReport("Genre", report) >> view
		1 * console.render(view)
	}
	
	def "should print an error message when the user types an invalid command"() {
		when:
		app.routeRequest("a", "invalid", "command")
		
		then:
		1 * console.render({it.items == ["command not found"]})
	}
	
	def "should start the application from the main method"() {
		given:
		VinylCollectionApp app = Mock(VinylCollectionApp)
		VinylCollectionApp.app = app
		
		when:
		VinylCollectionApp.main(null) 
		
		then:
		1 * app.run()
	}
	
}