package carlosgsouza.vinylshop.functional

import spock.lang.Specification
import carlosgsouza.derails.Console
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.VinylCollectionApp
import carlosgsouza.vinylshop.model.Report

class YearFunctionalSpec extends Specification {
	
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
	
	def "should search for vinyls given the year"() {
		given:
		def parachutes = app.preloadedVinyls[5]
		
		expect:
		parachutes.year == "2000"
		
		when:
		app.execute "search year 2000"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 1 with year matching '2000'"] + parachutes 
		}
	}
	
	def "should show no results if the year is not provided for the year search"() {
		when:
		app.execute "search year"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 0 with year matching ''"] 
		}
	}
	
	def "should show no results if there are no vinyls released at the provided year"() {
		when:
		app.execute "search year 876"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 0 with year matching '876'"] 
		}
	}
	
}