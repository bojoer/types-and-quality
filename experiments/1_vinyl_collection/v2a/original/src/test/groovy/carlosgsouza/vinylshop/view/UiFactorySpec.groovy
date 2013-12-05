package carlosgsouza.vinylshop.view

import spock.lang.Specification
import carlosgsouza.vinylshop.model.Report
import carlosgsouza.vinylshop.model.Summary
import carlosgsouza.vinylshop.model.Vinyl



public class UiFactorySpec extends Specification {
	
	def "should create a view and add items to it"() {
		given:
		Vinyl vinylA = new Vinyl(id:1, artist:"A", title:"A", songs:["A1", "A2", "A3"], year:"A", genre:"A")
		Vinyl vinylB = new Vinyl(id:2, artist:"B", title:"B", songs:["B1", "B2", "B3"], year:"B", genre:"B")
		Vinyl vinylC = new Vinyl(id:3, artist:"C", title:"C", songs:["C1", "C2", "C3"], year:"C", genre:"C")
		Summary summary = new Summary()
		Report report = new Report()
		
		when:
		UiFactory uiFactory = new UiFactory()
		
		then:
		uiFactory.listVinyls([]).items.size() ==  1
		uiFactory.listVinyls([vinylA, vinylB]).items.size() ==  1 + 2
		uiFactory.searchByTitle("", []).items.size() ==  1
		uiFactory.searchByTitle("", [vinylA]).items.size() ==  1 + 1
		uiFactory.searchByGenre("", []).items.size() ==  1
		uiFactory.searchByGenre("", [vinylA]).items.size() ==  1 + 1
		uiFactory.searchByYear("", []).items.size() ==  1
		uiFactory.searchByYear("", [vinylA]).items.size() ==  1 + 1
		uiFactory.searchByArtist("", []).items.size() ==  1
		uiFactory.searchByArtist("", [vinylA]).items.size() ==  1 + 1
		uiFactory.showVinyl(vinylA).items.size() ==  1
		uiFactory.deleteVinyl().items.size() ==  1
		uiFactory.createVinyl().items.size() ==  1
		uiFactory.listArtists([]).items.size() ==  1
		uiFactory.listArtists(["1", "2"]).items.size() ==  1 + 2
		uiFactory.listSongs([]).items.size() ==  1    
		uiFactory.listSongs(["1", "2"]).items.size() ==  1 + 2
		uiFactory.listGenres([]).items.size() ==  1    
		uiFactory.listGenres(["1", "2"]).items.size() ==  1 + 2
		uiFactory.showSummary(summary).items.size() ==  2
		uiFactory.showReport("", report).items.size() ==  2
		
	}	
	
	def "should create a form"() {
		given:
		UiFactory uiFactory = new UiFactory()
		
		when:
		def form = uiFactory.vinylForm()
		
		then:
		form != null
	}
	
}
