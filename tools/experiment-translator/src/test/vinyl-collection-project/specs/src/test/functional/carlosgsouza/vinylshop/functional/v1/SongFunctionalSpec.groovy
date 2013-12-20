package carlosgsouza.vinylshop.functional.v1

import spock.lang.Specification
import carlosgsouza.derails.Console
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.VinylCollectionApp
import carlosgsouza.vinylshop.model.Report

class SongFunctionalSpec extends Specification {
	
	VinylCollectionApp app
	
	def setup() {
		app = new VinylCollectionApp()
		app.console = Mock(Console)
		
		app.bootstrap()
	}
	
	def "should list all songs"() {
		when:
		app.execute "list song"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 18 songs", "Carmen", "Deus Le Volt!", "Don't Panic", "Getaway", "Gorilla", "Just Breathe", "Mind Your Manners", "Off to Races", "Quimica do Amor", "Radio", "Shiver", "Spies", "Supersonic", "Te vivo", "Treasure", "Waiting Silence", "Young Girls", "Young Sirens"]
		}
		
		
	}
	
	
}