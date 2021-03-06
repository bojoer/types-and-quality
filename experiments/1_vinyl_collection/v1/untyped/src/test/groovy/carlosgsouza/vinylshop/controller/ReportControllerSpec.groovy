package carlosgsouza.vinylshop.controller

import spock.lang.Specification
import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.model.Summary
import carlosgsouza.vinylshop.model.Vinyl


class ReportControllerSpec extends Specification {
	
	ReportController controller
	
	DB db
	
	Vinyl vinylA
	Vinyl vinylB
	Vinyl vinylC
	Vinyl vinylD
	
	def setup() {
		vinylA = new Vinyl(id:1, artist:"A", title:"A", songs:["A1"], year:"A", genre:"B")
		vinylB = new Vinyl(id:2, artist:"A", title:"B", songs:["B1"], year:"B", genre:"B")
		vinylC = new Vinyl(id:3, artist:"C", title:"C", songs:["C1", "C2"], year:"C", genre:"C")
		vinylD = new Vinyl(id:2, artist:"A", title:"D", songs:["D1", "D2"], year:"B", genre:"D")
		
		db = Mock(DB)
		
		controller = new ReportController(db:db)
	}
	
	def "should generate a report about the artists"() {
		given:
		db.artists >> ["A", "C"]
		db.searchVinylByArtist("A") >> [vinylA, vinylB, vinylD]
		db.searchVinylByArtist("C") >> [vinylC]
		
		when:
		def report = controller.artist()
		
		then:
		report.data["Number of artists"] == "2"
		report.data["Top artist"] == "A"
		report.data["Number of vinyls by A"] == "3"
		report.data["Number of songs by A"] == "4"
	}
	def "should generate an artist report even when we have no data on the DB"() {
		given:
		db.artists >> []
		
		when:
		def report = controller.artist()
		
		then:
		report.data == ["Number of artists": "0"]
	}
	
	def "should generate a report about the genres"() {
		given:
		db.genres >> ["B", "C", "D"]
		db.searchVinylByGenre("B") >> [vinylA, vinylB]
		db.searchVinylByGenre("C") >> [vinylC]
		db.searchVinylByGenre("D") >> [vinylD]
		
		when:
		def report = controller.genre()
		
		then:
		report.data["Number of genres"] == "3"
		report.data["Top genre"] == "B"
		report.data["Number of B vinyls"] == "2"
		report.data["Number of B songs"] == "2"
	}
	
	def "should generate genre report even when we have no vinyls on the DB"() {
		given:
		db.genres >> []
		
		when:
		def report = controller.genre()
		
		then:
		report.data == ["Number of genres": "0"]
	}
	
	
}