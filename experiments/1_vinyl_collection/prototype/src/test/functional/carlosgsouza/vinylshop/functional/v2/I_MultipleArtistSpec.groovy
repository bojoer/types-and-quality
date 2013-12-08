package carlosgsouza.vinylshop.functional.v2

import spock.lang.Specification
import carlosgsouza.derails.Console
import carlosgsouza.derails.Form
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.VinylCollectionApp
import carlosgsouza.vinylshop.model.Report
import carlosgsouza.vinylshop.model.Summary
import carlosgsouza.vinylshop.model.Vinyl

class I_MultipleArtistSpec extends Specification {
	
	VinylCollectionApp app
	
	def setup() {
		app = new VinylCollectionApp()
		app.console = Mock(Console)
		
		app.bootstrap()
	}
	
	def "should list artists of vinyls with more than one artist"() {
		given:
		def vinylWithTwoArtists = new Vinyl(artist:["Vitor", "Leo"], title:"Vida Boa", songs:["Fada", "Arapuca"], year:"2004", genre:"Sertanejo")
		
		and:
		app.db.reset()
		app.vinylController.create vinylWithTwoArtists
		
		when:
		app.execute "list artist"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 2 artists", "Vitor", "Leo"]
		}
	}
	
	def "should search artists of vinyls with more than one artist"() {
		given:
		def vinylWithTwoArtists = new Vinyl(artist:["Vitor", "Leo"], title:"Vida Boa", songs:["Fada", "Arapuca"], year:"2004", genre:"Sertanejo")
		
		and:
		app.db.reset()
		app.vinylController.create vinylWithTwoArtists
		
		when:
		app.execute "search artist Vit"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 1 with artist matching 'Vit'"] + vinylWithTwoArtists
		}
	}
	
	def "should show an artist report when we have vinyls with more than one artist"() {
		given:
		def vinylWithTwoArtists = new Vinyl(artist:["Pearl Jam", "Ximbinha"], title:"Hard Metal, Soft Heart", songs:["Even Fu™", "Ahhhhhlive"], year:"2014", genre:"Rockalypso")
		
		and:
		app.vinylController.create vinylWithTwoArtists
		
		when:
		app.execute "artist report"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Artist Report", new Report(data:["Number of artists":"7", "Top artist":"Pearl Jam", "Number of vinyls by Pearl Jam": "3", "Number of songs by Pearl Jam":"7"] ) ]
		}
	}
	
	def "should create a vinyl with more than one artist"() {
		given:
		def newVinyl = new Vinyl(artist:["Artist 1",  "Artist 2"], title:"Title", songs:["Song 1", "Song 2"], year:"2013", genre:"Genre")
		
		when:
		app.execute "create vinyl"
		
		then:
		1 * app.console.apply { Form form ->
				form.title == "Please enter the vinyl details below" &&
				form.fieldName == ["Artist", "Title", "Songs", "Year", "Genre"]
			} >> { Form form ->
				form.fields = [
							"Artist":newVinyl.artist.join(", "),
							"Title":newVinyl.title,
							"Songs":newVinyl.songs.join(", "),
							"Year":newVinyl.year,
							"Genre":newVinyl.genre]
			}
		
		when:
		app.execute "list vinyl"
		
		then:
		1 * app.console.render { View view ->
			view.items[8] == newVinyl &&
			view.items[8].toString().contains("Artist 1, Artist 2")
		}
			
		when:
		app.execute "show vinyl 8"
		
		then:
		1 * app.console.render { View view ->
			view.items == [newVinyl]
		}
	}
	
	def "should show a summary of the vinyls when there are vinyls with more than one artist"() {
		given:
		def vinylWithTwoArtists = new Vinyl(artist:["Iron Maiden", "Ximbinha"], title:"Hard Metal, Soft Heart", songs:["Even Fu™", "Ahhhhhlive"], year:"2014", genre:"Rockalypso")
		
		and:
		app.vinylController.create vinylWithTwoArtists
		
		when:
		app.execute "show summary"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Collection Summary", new Summary(vinylCount:8, artistCount: 8, songCount:20, genreCount:5)]
		}
	}
}