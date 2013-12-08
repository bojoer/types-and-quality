package carlosgsouza.vinylshop.controller

import spock.lang.Specification
import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.model.Summary
import carlosgsouza.vinylshop.model.Vinyl


class SummaryControllerSpec extends Specification {
	
	SummaryController controller
	
	Vinyl vinylA
	Vinyl vinylB
	Vinyl vinylC
	
	def setup() {
		vinylA = new Vinyl(id:1, artist:"A", title:"A", songs:["A1", "A2", "A3"], year:"A", genre:"B")
		vinylB = new Vinyl(id:2, artist:"A", title:"B", songs:["B1", "B2", "B3"], year:"B", genre:"B")
		vinylC = new Vinyl(id:3, artist:"C", title:"C", songs:["C1", "C2"], year:"C", genre:"B")
		
		controller = new SummaryController()
		
		controller.db.reset()
	}
	
	def "should count the number of vinyls, artist, songs and genres"() {
		given:
		controller.db.addVinyl vinylA
		controller.db.addVinyl vinylB
		controller.db.addVinyl vinylC
		
		when:
		def summary = controller.show()
		
		then:
		summary.vinylCount == 3
		summary.artistCount == 2
		summary.songCount == 8
		summary.genreCount == 1
	}
	
	def "should not count duplicated artists or genres"() {
		given:
		controller.db.addVinyl vinylA
		controller.db.addVinyl vinylA
		controller.db.addVinyl vinylA
		
		when:
		def summary = controller.show()
		
		then:
		summary.vinylCount == 3
		summary.artistCount == 1
		summary.songCount == 9
		summary.genreCount == 1
	}
	
	def "should return a summary with every count == 0 when no vinyls are on the database"() {
		when:
		def summary = controller.show()
		
		then:
		summary.vinylCount == 0
		summary.artistCount == 0
		summary.songCount == 0
		summary.genreCount == 0
	}
	
}