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
	
	def "should show a summary of the vinyls when there are vinyls with more than one artist"() {
		given:
		def vinylWithTwoArtists = new Vinyl(artist:["Pearl Jam", "Ximbinha"], title:"Hard Metal, Soft Heart", songs:["Even Fu™", "Ahhhhhlive"], year:"2014", genre:"Rockalypso")
		
		and:
		app.vinylController.create vinylWithTwoArtists
		
		when:
		app.execute "show summary"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Collection Summary", new Summary(vinylCount:8, artistCount: 7, songCount:20, genreCount:5)]
		}
	}
	
}