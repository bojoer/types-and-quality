package carlosgsouza.vinylshop.functional.v2

import spock.lang.Specification
import carlosgsouza.derails.Console
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.VinylCollectionApp

class YearFunctionalSpec extends Specification {
	
	
	VinylCollectionApp app 
	
	def setup() {
		app = new VinylCollectionApp()
		app.console = Mock(Console)
		
		app.bootstrap()
	}
	
	def "should show an error message if the year is not provided for the year search"() {
		when:
		app.execute "search year"
		
		then:
		1 * app.console.render { View view ->
			view.items[0].contains("(error) Year must be a number")
		}
	}
	
	def "should show an error message when a non numerical year is specified on the year search"() {
		when:
		app.execute "search year string"
		
		then:
		1 * app.console.render { View view ->
			view.items[0].contains("(error) Year must be a number")
		}
	}
	
}