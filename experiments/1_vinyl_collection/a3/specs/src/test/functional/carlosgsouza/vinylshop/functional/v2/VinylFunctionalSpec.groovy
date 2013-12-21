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
	
	
	
	
}