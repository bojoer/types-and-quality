package carlosgsouza.vinylshop.controller

import spock.lang.Specification
import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.model.Vinyl


class SongControllerSpec extends Specification {
	
	SongController controller
	
	DB db
	
	Vinyl vinylA
	Vinyl vinylB
	Vinyl vinylC
	
	def setup() {
		vinylA = new Vinyl(id:1, artist:"A", title:"A", songs:["A1", "A2", "A3"], year:"A", genre:"A")
		vinylB = new Vinyl(id:2, artist:"B", title:"B", songs:["B1", "B2", "B3"], year:"B", genre:"B")
		
		db = Mock(DB)
		
		controller = new SongController(db:db)
	}
	
	def "should list tall Songs of all vinyls on the database"() {
		when:
		def all = controller.list()
		
		then:
		1 * db.allSongs >> ["A", "B", "C"]
		
		and:
		all == ["A", "B", "C"]
	}
	
	def "should search for vinyls by Song"() {
		when:
		def all = controller.search("Song name")
		
		then:
		1 * db.searchVinylBySong("Song name") >> [vinylA, vinylB]
		
		and:
		all == [vinylA, vinylB]
	}
	
	
	
	
}