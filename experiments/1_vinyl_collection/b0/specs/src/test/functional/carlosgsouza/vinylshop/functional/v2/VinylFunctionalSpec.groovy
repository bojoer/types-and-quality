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
	
	def "should delete a vinyl which artist has only one vinyl in the collection"() {
		when:
		app.execute "delete vinyl 1"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Vinyl deleted"]
		}
		
		and:
		!app.db.vinyls.contains(app.preloadedVinyls.find{ it.id == 1})
	}
	
	def "should show an error message when trying to delete an inexistent vinyl"() {
		when:
		app.execute "delete vinyl 657"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["(error) Vinyl doesn't exist"]
		}
	}
	
}