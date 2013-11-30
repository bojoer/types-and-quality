package carlosgsouza.vinylshop.functional

import spock.lang.Specification
import carlosgsouza.derails.Console;
import carlosgsouza.derails.Form
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.VinylCollectionApp
import carlosgsouza.vinylshop.model.Vinyl

class VinylFunctionalSpec extends Specification {
	
	VinylCollectionApp app 
	
	def setup() {
		app = new VinylCollectionApp()
		app.console = Mock(Console)
		
		app.bootstrap()
	}
	
	def "should list vinyls"() {
		when:
		app.execute "list vinyl"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 7 items"] + app.preloadedVinyls
		}
	}
	
	def "should show a vinyl"() {
		when:
		app.execute "show vinyl 1"
		
		then:
		1 * app.console.render { View view ->
			view.items == [app.preloadedVinyls[0]]
		}
	}
	
	def "should show an error message when trying to shown an inexistent vinyl"() {
		when:
		app.execute "show vinyl 657"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["(error) Vinyl doesn't exist"]
		}
	}
	
	def "should delete a vinyl"() {
		when:
		app.execute "delete vinyl 7"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Vinyl deleted"]
		}
		
		when:
		app.execute "list vinyl"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 6 items"] + app.preloadedVinyls - app.preloadedVinyls[6]
		}
	}
	
	def "should show an error message when trying to delete an inexistent vinyl"() {
		when:
		app.execute "delete vinyl 657"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["(error) Vinyl doesn't exist"]
		}
	}
	
	def "should search for a vinyl given its name, ignoring the case and matching the query anywhere in the name"() {
		when:
		app.execute "search vinyl Parachutes"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 1 items matching 'Parachutes'", app.preloadedVinyls[5]]
		}
		
		when:
		app.execute "search vinyl PARAChutes"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 1 items matching 'PARAChutes'", app.preloadedVinyls[5]]
		}
		
		when:
		app.execute "search vinyl chu"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 1 items matching 'chu'", app.preloadedVinyls[5]]
		}
	}
	
	def "should not find an uniexistant vinyl"() {
		when:
		app.execute "search vinyl UNEXISTENT"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 0 items matching 'UNEXISTENT'"]
		}
	}
	
	def "should create a vinyl"() {
		given:
		def newVinyl = new Vinyl(artist:"Artist", title:"Title", songs:["Song 1", "Song 2"], year:"1990", genre:"Genre")
		
		when:
		app.execute "create vinyl"
		
		then:
		1 * app.console.apply { Form form ->
				form.title == "Please enter the vinyl details below" &&
				form.fieldName == ["Artist", "Title", "Songs", "Year", "Genre"]
			} >> { Form form ->
				form.fields = [
							"Artist":newVinyl.artist, 
							"Title":newVinyl.title,
							"Songs":newVinyl.songs.join(", "), 
							"Year":newVinyl.year, 
							"Genre":newVinyl.genre]
			} 
		
		when:
		app.execute "list vinyl"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 8 items"] + app.preloadedVinyls + newVinyl
		}
			
		when:
		app.execute "show vinyl 8"
		
		then:
		1 * app.console.render { View view ->
			view.items == [newVinyl]
		}
	}
	
	def "should not create an invalid vinyl"() {
		given:
		app.console.apply(_) >> { Form form -> form.fields = ["Artist":"", "Title":"", "Songs":"", "Year":"", "Genre":""] }
		
		when:
		app.execute "create vinyl"
		
		then:
		1 * app.console.render { it.items == ["(error) Can't create invalid vinyl"] }
	}
	
}