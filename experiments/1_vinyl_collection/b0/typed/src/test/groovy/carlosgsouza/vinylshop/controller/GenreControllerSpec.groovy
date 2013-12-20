package carlosgsouza.vinylshop.controller

import spock.lang.Specification
import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.model.Genre
import carlosgsouza.vinylshop.model.Vinyl


class GenreControllerSpec extends Specification {
	
	GenreController controller
	
	DB db
	
	Vinyl vinylA
	Vinyl vinylB1
	Vinyl vinylB2
	
	Genre genreA
	Genre genreB
	
	def setup() {
		vinylA = new Vinyl(id:1, artist:"A", title:"A", songs:["A1", "A2", "A3"], year:"0", genre:"A")
		vinylB1 = new Vinyl(id:2, artist:"B1", title:"B1", songs:["B11", "B12", "B13"], year:"1", genre:"B")
		vinylB2 = new Vinyl(id:3, artist:"B2", title:"B2", songs:["B21", "B22", "B23"], year:"2", genre:"B")
		
		genreA = new Genre(name:"A", vinyls:[vinylA])
		genreB = new Genre(name:"B", vinyls:[vinylB1, vinylB2])
		
		db = Mock(DB)
		
		controller = new GenreController(db:db)
	}
	
	def "should list tall Genres of all vinyls on the database"() {
		when:
		def all = controller.list()
		
		then:
		1 * db.genres >> ["A", "B", "C"]
		
		and:
		all == ["A", "B", "C"]
	}
	
	def "should search for vinyls by Genre"() {
		given:
		// this behavior is incorrect. This should actually return the genre model objects
		db.getGenres() >> ["A", "B"] 
		
		when:
		def all = controller.search("B")
		
		then:
		1 * db.searchVinylByGenre("B") >> [vinylB1, vinylB2]
		
		and:
		all == [vinylB1, vinylB2]
	}
	
	
	
	
}