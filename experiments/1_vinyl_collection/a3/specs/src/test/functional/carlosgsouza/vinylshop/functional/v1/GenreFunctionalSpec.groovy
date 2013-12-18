package carlosgsouza.vinylshop.functional.v1

import spock.lang.Specification
import carlosgsouza.derails.Console
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.VinylCollectionApp
import carlosgsouza.vinylshop.model.Report

class GenreFunctionalSpec extends Specification {
	
	VinylCollectionApp app
	def genres = [:]
	
	def setup() {
		app = new VinylCollectionApp()
		app.console = Mock(Console)
		
		app.bootstrap()
		
		app.db.@genres.each {
			genres[it.name] = it
		}
	}
	
	def "should show no results if the genre is not provided for the genre search"() {
		when:
		app.execute "search genre"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 0 with genre matching ''"]
		}
	}
	
	def "should show no results if there are no vinyls with the given genre"() {
		when:
		app.execute "search genre Sertanejo"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 0 with genre matching 'Sertanejo'"]
		}
	}
	
	def "should list all genres"() {
		when:
		app.execute "list genre"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 4 genres", "Pop", "Rock", "Metal", "Alternative"]
		}
	}
}