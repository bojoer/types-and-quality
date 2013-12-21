package carlosgsouza.vinylshop.functional.v1

import java.util.List;

import spock.lang.Specification
import carlosgsouza.derails.Console
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.VinylCollectionApp
import carlosgsouza.vinylshop.model.Vinyl

class ArtistFunctionalSpec extends Specification {
	
	VinylCollectionApp app
	
	List<Vinyl> preloadedVinyls = [
		new Vinyl(artist:["Lana Del Rey"], title:"Born to Die", songs:["Off to Races", "Radio", "Carmen"], year:"2012", genre:"Pop"),
		new Vinyl(artist:["Bruno Mars"], title:"Unorthodox Jukebox", songs:["Gorilla", "Treasure", "Young Girls"], year:"2012", genre:"Pop"),
		new Vinyl(artist:["Pearl Jam"], title:"Lightning Bolt", songs:["Getaway", "Mind Your Manners", "Young Sirens"], year:"2013", genre:"Rock"),
		new Vinyl(artist:["Angra"], title:"Temple of Shadows", songs:["Deus Le Volt!", "Waiting Silence"], year:"2004", genre:"Metal"),
		new Vinyl(artist:["Luan Santana"], title:"Quando Chega a Noite", songs:["Te vivo", "Quimica do Amor"], year:"2010", genre:"Rock"),
		new Vinyl(artist:["Coldplay"], title:"Parachutes", songs:["Don't Panic", "Shiver", "Spies"], year:"2000", genre:"Alternative"),
		new Vinyl(artist:["Pearl Jam"], title:"Backspacer", songs:["Just Breathe", "Supersonic"], year:"2009", genre:"Rock")]
	
	def setup() {
		app = new VinylCollectionApp()
		app.console = Mock(Console)
		
		app.bootstrap()
	}
	
	def "should search for vinyls given the artist"() {
		given:
		def bornToDie = app.preloadedVinyls[0]
		
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