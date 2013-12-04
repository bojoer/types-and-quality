package carlosgsouza.vinylshop.functional

import spock.lang.Specification
import carlosgsouza.derails.Console
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.VinylCollectionApp
import carlosgsouza.vinylshop.model.Report

class ReportFunctionalSpec extends Specification {
	
	VinylCollectionApp app 
	
	def setup() {
		app = new VinylCollectionApp()
		app.console = Mock(Console)
		
		app.bootstrap()
	}
	
	def "should show an artist report"() {
		when:
		app.execute "artist report"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Artist Report", new Report(data:["Number of artists":"6", "Top artist":"Pearl Jam", "Number of vinyls by Pearl Jam": "2", "Number of songs by Pearl Jam":"5"] ) ]
		}
	}
	
	def "in case of a draw, the artist report should consider the top artist to be the one added earliest"() {
		given: "all artists have the same number of albums"
		app.execute "delete vinyl 7"
		
		when:
		app.execute "artist report"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Artist Report", new Report(data:["Number of artists":"6", "Top artist":"Lana Del Rey", "Number of vinyls by Lana Del Rey": "1", "Number of songs by Lana Del Rey":"3"] ) ]
		}
	}
	
	def "should show a genre report"() {
		when:
		app.execute "genre report"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Genre Report", new Report(data:["Number of genres":"4", "Top genre":"Rock", "Number of Rock vinyls": "3", "Number of Rock songs":"7"] ) ]
		}
	}
	
	def "in case of a draw, the genre report should consider the top artist to be the one added earliest"() {
		given: "all genres have the same number of albums"
		app.execute "delete vinyl 7"
		
		when:
		app.execute "genre report"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Genre Report", new Report(data:["Number of genres":"4", "Top genre":"Pop", "Number of Pop vinyls": "2", "Number of Pop songs":"6"] ) ]
		}
	}
	
}