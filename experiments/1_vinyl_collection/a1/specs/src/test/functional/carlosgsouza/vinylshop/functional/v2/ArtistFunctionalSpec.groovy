package carlosgsouza.vinylshop.functional.v2

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
	
	def "should list all artists"() {
		when:
		app.execute "list artist"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 6 artists", "Lana Del Rey", "Bruno Mars", "Pearl Jam", "Angra", "Luan Santana", "Coldplay"]
		}
	}
	
	
	def "should show a message without plural when there is only one artist"() {
		given:
		app.db.reset()
		
		when:
		app.execute "list artist"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Unexpected error. Artist list had 0 items"]
		}
	}
	
	def "should shown an error message when there are no artists"() {
		given:
		app.db.reset()
		
		and:
		app.vinylController.create app.preloadedVinyls[0]
		
		when:
		app.execute "list artist"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 1 artist", "Lana Del Rey"]
		}
	}
}