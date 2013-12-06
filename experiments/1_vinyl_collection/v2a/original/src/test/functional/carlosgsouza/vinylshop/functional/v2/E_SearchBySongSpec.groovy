package carlosgsouza.vinylshop.functional.v2

import spock.lang.Specification
import carlosgsouza.derails.Console
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.VinylCollectionApp
import carlosgsouza.vinylshop.model.Report

class E_SearchBySongSpec extends Specification {
	
	VinylCollectionApp app
	
	def setup() {
		app = new VinylCollectionApp()
		app.console = Mock(Console)
		
		app.bootstrap()
	}
	
	def "should search for vinyls given the song name"() {
		given:
		def bornToDie = app.preloadedVinyls[0]
		
		expect:
		bornToDie.songs.contains "Radio"
		
		when:
		app.execute "search song Radio"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 1 with song matching 'Radio'"] + bornToDie
		}
	}
	
	def "should ignore the case when searching for vinyls given the song"() {
		given:
		def bornToDie = app.preloadedVinyls[0]
		
		expect:
		bornToDie.songs.contains "Radio"
		
		when:
		app.execute "search song rADio"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 1 with song matching 'rADio'"] + bornToDie
		}
	}
	
	def "should match partially when searching for vinyls given the song"() {
		given:
		def bornToDie = app.preloadedVinyls[0]
		
		expect:
		bornToDie.songs.contains "Radio"
		
		when:
		app.execute "search song adio"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 1 with song matching 'adio'"] + bornToDie
		}
	}
	
	def "should show no results if the song is not provided for the song search"() {
		when:
		app.execute "search song"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 0 with song matching ''"]
		}
	}
	
	def "should show no results if there are no vinyls with the given song"() {
		when:
		app.execute "search song Florentina"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 0 with song matching 'Florentina'"]
		}
	}
}