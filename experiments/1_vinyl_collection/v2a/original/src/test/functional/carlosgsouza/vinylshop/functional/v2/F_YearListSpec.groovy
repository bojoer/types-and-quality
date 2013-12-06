package carlosgsouza.vinylshop.functional.v2

import spock.lang.Specification
import carlosgsouza.derails.Console
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.VinylCollectionApp

class F_YearListSpec extends Specification {
	
	
	VinylCollectionApp app 
	
	def setup() {
		app = new VinylCollectionApp()
		app.console = Mock(Console)
		
		app.bootstrap()
	}
	
	def "should list all years"() {
		when:
		app.execute "list year"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 6 years", "2000", "2004", "2009", "2010",  "2012", "2013"]
		}
	}
	
}