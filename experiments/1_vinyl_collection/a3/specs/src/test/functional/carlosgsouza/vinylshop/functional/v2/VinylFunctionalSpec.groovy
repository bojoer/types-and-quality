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
	
	
	def "should search for a vinyl given its name, ignoring the case and matching the query anywhere in the name"() {
		given:
		def parachutes = app.preloadedVinyls.find{ it.title == "Parachutes" }
		
		when:
		app.execute "search vinyl Parachutes"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 1 with title matching 'Parachutes'", parachutes]
		}
		
		when:
		app.execute "search vinyl PARAChutes"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 1 with title matching 'PARAChutes'", parachutes]
		}
		
		when:
		app.execute "search vinyl chu"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 1 with title matching 'chu'", parachutes]
		}
	}
	
	def "should not find an uniexistant vinyl"() {
		when:
		app.execute "search vinyl UNEXISTENT"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 0 with title matching 'UNEXISTENT'"]
		}
	}
	
}