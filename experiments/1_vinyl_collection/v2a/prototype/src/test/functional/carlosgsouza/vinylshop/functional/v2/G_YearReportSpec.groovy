package carlosgsouza.vinylshop.functional.v2

import spock.lang.Specification
import carlosgsouza.derails.Console
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.VinylCollectionApp
import carlosgsouza.vinylshop.model.Report
import carlosgsouza.vinylshop.model.Vinyl

class G_YearReportSpec extends Specification {
	
	VinylCollectionApp app 
	
	def setup() {
		app = new VinylCollectionApp()
		app.console = Mock(Console)
		
		app.bootstrap()
	}
	
	def "should show a year report"() {
		when:
		app.execute "year report"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Year Report", new Report(data:["Top year":"2012", "Newest vinyl": "Lightning Bolt", "Oldest vinyl":"Parachutes"] ) ]
		}
	}
	
	def "should consider the newest vinyl as the one with the highest id in case of a draw"() {
		given:
		app.db.reset()
		
		and:
		app.vinylController.create app.preloadedVinyls[0]
		app.vinylController.create app.preloadedVinyls[1]
		app.vinylController.create app.preloadedVinyls[5]
		
		expect:
		app.vinylController.list()*.id == [1, 2, 6]
		app.vinylController.list()*.year == ["2012", "2012", "2000"]
		app.vinylController.list()*.title == ["Born to Die", "Unorthodox Jukebox", "Parachutes"]
		
		when:
		app.execute "year report"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Year Report", new Report(data:["Top year":"2012", "Newest vinyl": "Unorthodox Jukebox", "Oldest vinyl":"Parachutes"] ) ]
		}
	}
	
	def "should consider the oldest vinyl as the one with the lowest id in case of a draw"() {
		given:
		app.db.reset()
		
		and:
		app.vinylController.create app.preloadedVinyls[0]
		app.vinylController.create app.preloadedVinyls[1]
		app.vinylController.create app.preloadedVinyls[2]
		
		expect:
		app.vinylController.list()*.id == [1, 2, 3]
		app.vinylController.list()*.year == ["2012", "2012", "2013"]
		app.vinylController.list()*.title == ["Born to Die", "Unorthodox Jukebox", "Lightning Bolt"]
		
		when:
		app.execute "year report"
		
		then:
		1 * app.console.render { View view ->
			view.items == ["Year Report", new Report(data:["Top year":"2012", "Newest vinyl": "Lightning Bolt", "Oldest vinyl":"Born to Die"] ) ]
		}
	}
	
}