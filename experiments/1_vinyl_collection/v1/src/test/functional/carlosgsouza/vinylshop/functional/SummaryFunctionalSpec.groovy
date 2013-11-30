package carlosgsouza.vinylshop.functional

import spock.lang.Specification
import carlosgsouza.derails.Console
import carlosgsouza.derails.Form
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.VinylCollectionApp
import carlosgsouza.vinylshop.model.Summary
import carlosgsouza.vinylshop.model.Vinyl

class SummaryFunctionalSpec extends Specification {
	
	VinylCollectionApp app 
	
	def setup() {
		app = new VinylCollectionApp()
		app.console = Mock(Console)
		
		app.bootstrap()
	}
	
	def "should show a summary of the vinyls"() {
		when:
		app.execute "show summary"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Collection Summary", new Summary(vinylCount:7, artistCount: 6, songCount:18, genreCount:4)]
		}
	}
	
}