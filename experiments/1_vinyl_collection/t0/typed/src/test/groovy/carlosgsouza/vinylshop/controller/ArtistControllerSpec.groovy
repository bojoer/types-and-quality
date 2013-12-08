package carlosgsouza.vinylshop.controller

import spock.lang.Specification
import spock.lang.Unroll
import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.model.Vinyl


class ArtistControllerSpec extends Specification {
	
	ArtistController controller
	
	DB db
	
	Vinyl vinylA
	Vinyl vinylB
	Vinyl vinylC
	
	def setup() {
		vinylA = new Vinyl(id:1, artist:"A", title:"A", songs:["A1", "A2", "A3"], year:"A", genre:"A")
		vinylB = new Vinyl(id:2, artist:"B", title:"B", songs:["B1", "B2", "B3"], year:"B", genre:"B")
		vinylC = new Vinyl(id:3, artist:"C", title:"C", songs:["C1", "C2", "C3"], year:"C", genre:"C")
		
		db = Mock(DB)
		
		controller = new ArtistController(db:db)
	}
	
	def "should list tall artists of all vinyls on the database"() {
		when:
		def all = controller.list()
		
		then:
		1 * db.artists >> ["A", "B", "C"]
		
		and:
		all == ["A", "B", "C"]
	}
	
	def "should search for vinyls by artist"() {
		when:
		def all = controller.search("artist name")
		
		then:
		1 * db.searchVinylByArtist("artist name") >> [vinylA, vinylB]
		
		and:
		all == [vinylA, vinylB]
	}
	
	
	
	
}