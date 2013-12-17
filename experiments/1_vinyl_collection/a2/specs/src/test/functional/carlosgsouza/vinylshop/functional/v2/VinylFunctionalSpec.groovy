package carlosgsouza.vinylshop.functional.v2

import spock.lang.Specification
import carlosgsouza.derails.Console;
import carlosgsouza.derails.Form
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.VinylCollectionApp
import carlosgsouza.vinylshop.model.Vinyl

class VinylFunctionalSpec extends Specification {
	
	VinylCollectionApp app
	def vinylsSortedByYear
	
	def setup() {
		app = new VinylCollectionApp()
		app.console = Mock(Console)
		
		app.bootstrap()
		
		vinylsSortedByYear = app.preloadedVinyls.sort{ it.year}
	}
	
	def "should show a vinyl"() {
		when:
		app.execute "show vinyl 1"
		
		then:
		1 * app.console.render{ View view ->
			view.items == [app.preloadedVinyls.find{it.id == 1}]
		}
	}
	
	def "should delete a vinyl"() {
		when:
		app.execute "delete vinyl 7"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Vinyl deleted"]
		}
		
		and:
		!app.db.vinyls.contains(app.preloadedVinyls.find{ it.id == 7})
	}
	
	def "should create a vinyl"() {
		given:
		def newVinyl = new Vinyl(artist:["Artist"], title:"Title", songs:["Song 1", "Song 2"], year:"2013", genre:"Genre")
		
		when:
		app.execute "create vinyl"
		
		then:
		1 * app.console.apply { Form form ->
				form.title == "Please enter the vinyl details below" &&
				form.fieldName == ["Artist", "Title", "Songs", "Year", "Genre"]
			} >> { Form form ->
				form.fields = [
							"Artist":newVinyl.artist, 
							"Title":newVinyl.title,
							"Songs":"Song 1,   Song 2", 
							"Year":newVinyl.year, 
							"Genre":newVinyl.genre]
			} 
		
		and:
		1 * app.console.render { it.items == [newVinyl] }
		
		and:
		app.db.vinyls.contains newVinyl

	}
}