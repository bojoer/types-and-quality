package carlosgsouza.vinylshop

import spock.lang.Specification
import carlosgsouza.derails.Console
import carlosgsouza.derails.Form
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.controller.ArtistController
import carlosgsouza.vinylshop.controller.VinylController
import carlosgsouza.vinylshop.model.Vinyl
import carlosgsouza.vinylshop.view.UiFactory

class VinylCollectionAppSpec extends Specification {
	
	VinylController vinylController
	ArtistController artistController
	UiFactory uiFactory
	Console console
	
	Vinyl vinylA
	Vinyl vinylB
	Vinyl vinylC
	
	View view
	Form vinylForm
	
	VinylCollectionApp app
	
	def setup() {
		vinylA = new Vinyl(id:1, artist:"A", title:"A", songs:["A1", "A2", "A3"], year:"A", genre:"A")
		vinylB = new Vinyl(id:2, artist:"B", title:"B", songs:["B1", "B2", "B3"], year:"B", genre:"B")
		vinylC = new Vinyl(id:3, artist:"C", title:"C", songs:["C1", "C2", "C3"], year:"C", genre:"C")
		
		view = new View()
		
		vinylForm = new UiFactory().vinylForm()
		
		vinylController = Mock(VinylController)
		artistController = Mock(ArtistController)
		uiFactory = Mock(UiFactory)
		console = Mock(Console)
		
		app = new VinylCollectionApp(artistController: artistController, vinylController:vinylController, uiFactory:uiFactory, console:console)
	}
	
	def "should create a bunch of vinyl during bootstrap"() {
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
		1 * uiFactory.deleteVinyls()
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
		1 * uiFactory.searchVinyls("Album Title", [vinylA, vinylB]) >> view
		1 * console.render(view)
	}
	
//	def "should search for an artist"() {
//		when:
//		app.routeRequest("artist", "search", "Artist Name")
//		
//		then:
//		1 * artistController.search("Artist Name") >> ["Art", vinylB]
//		1 * uiFactory.searchArtist("Artist Name", [vinylA, vinylB]) >> view
//		1 * console.render(view)
//	}
	
	def "should list all artists"() {
		when:
		app.routeRequest("artist", "list", null)
		
		then:
		1 * artistController.list() >> ["Artist 1", "Artist 2"]
		1 * uiFactory.listArtists(["Artist 1", "Artist 2"]) >> view
		1 * console.render(view)
	}
	
	
	
}