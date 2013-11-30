package carlosgsouza.vinylshop.functional

import spock.lang.Specification
import carlosgsouza.derails.Console
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.VinylCollectionApp
import carlosgsouza.vinylshop.model.Report

class ArtistFunctionalSpec extends Specification {
	
	VinylCollectionApp app
	
	def setup() {
		app = new VinylCollectionApp()
		app.console = Mock(Console)
		
		app.bootstrap()
	}
	
	def "should list all artists"() {
		when:
		app.execute "list artist"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 6 artists", "Lana Del Rey", "Bruno Mars", "Pearl Jam", "Angra", "Luan Santana", "Coldplay"]
		}
	}
	
	def "should search for vinyls given the artist"() {
		given:
		def bornToDie = app.preloadedVinyls[0]
		
		expect:
		bornToDie.artist == "Lana Del Rey"
		
		when:
		app.execute "search artist Lana Del Rey"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 1 with artist matching 'Lana Del Rey'"] + bornToDie
		}
	}
	
	def "should ignore the case when searching for vinyls given the artist"() {
		given:
		def bornToDie = app.preloadedVinyls[0]
		
		expect:
		bornToDie.artist == "Lana Del Rey"
		
		when:
		app.execute "search artist lAna DEl rEy"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 1 with artist matching 'lAna DEl rEy'"] + bornToDie
		}
	}
	
	def "should match partially when searching for vinyls given the artist"() {
		given:
		def bornToDie = app.preloadedVinyls[0]
		
		expect:
		bornToDie.artist == "Lana Del Rey"
		
		when:
		app.execute "search artist Lana"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 1 with artist matching 'Lana'"] + bornToDie
		}
	}
	
	def "should show no results if the artist is not provided for the artist search"() {
		when:
		app.execute "search artist"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 0 with artist matching ''"]
		}
	}
	
	def "should show no results if there are no vinyls with the given artist"() {
		when:
		app.execute "search artist Tiririca"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 0 with artist matching 'Tiririca'"]
		}
	}
}