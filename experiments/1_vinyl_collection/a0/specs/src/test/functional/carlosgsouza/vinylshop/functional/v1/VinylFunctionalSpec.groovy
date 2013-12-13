package carlosgsouza.vinylshop.functional.v1

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
	
	def "should show an error message when trying to shown an inexistent vinyl"() {
		when:
		app.execute "show vinyl 657"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["(error) Vinyl doesn't exist"]
		}
	}
	
	def "should show an error message when trying to delete an inexistent vinyl"() {
		when:
		app.execute "delete vinyl 657"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["(error) Vinyl doesn't exist"]
		}
	}
	
	def "should search for a vinyl given its name, ignoring the case and matching the query anywhere in the name"() {
		given:
		def parachutes = app.preloadedVinyls.find{ it.title == "Parachutes" }
		
		when:
		app.execute "search vinyl Parachutes"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 1 items matching 'Parachutes'", parachutes]
		}
		
		when:
		app.execute "search vinyl PARAChutes"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 1 items matching 'PARAChutes'", parachutes]
		}
		
		when:
		app.execute "search vinyl chu"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 1 items matching 'chu'", parachutes]
		}
	}
	
	def "should not find an uniexistant vinyl"() {
		when:
		app.execute "search vinyl UNEXISTENT"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 0 items matching 'UNEXISTENT'"]
		}
	}
	
	def "should not create an invalid vinyl"() {
		given:
		app.console.apply(_) >> { Form form -> form.fields = ["Artist":"", "Title":"", "Songs":"", "Year":"", "Genre":""] }
		
		when:
		app.execute "create vinyl"
		
		then:
		1 * app.console.render { it.items == ["(error) Can't create invalid vinyl"] }
	}
	
}