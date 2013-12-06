package carlosgsouza.vinylshop.functional.v2

import spock.lang.Specification
import carlosgsouza.derails.Console
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.VinylCollectionApp
import carlosgsouza.vinylshop.model.Report

class H_GenreListBugSpec extends Specification {
	
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
	
	def "should list all genres"() {
		when:
		app.execute "list genre"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Listing 4 genres", genres["Pop"], genres["Rock"], genres["Metal"], genres["Alternative"]]
		}
	}
}