package carlosgsouza.vinylshop.model

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class GenreSpec extends Specification {
	
	@Shared Vinyl vinylA
	@Shared Vinyl vinylB
	@Shared Vinyl vinylC
	
	def setup() {
		vinylA = new Vinyl(id:1, artist:"Artist", title:"A", songs:["A1", "A2", "A3"], year:"A", genre:"A")
		vinylB = new Vinyl(id:2, artist:"Artist", title:"B", songs:["B1", "B2", "B3"], year:"B", genre:"B")
		vinylC = new Vinyl(id:3, artist:"Artist", title:"C", songs:["C1", "C2", "C3"], year:"C", genre:"C")
	}
	
	def "should generate a string with the name of the genre and albuns of that genre"() {
		when:
		def genreWithNoVinyls = new Genre(name: "Genre", vinyls:[]).toString()
		def genreWithOneVinyl = new Genre(name: "Genre", vinyls:[vinylA]).toString()
		def genreWithManyVinyls = new Genre(name: "Genre", vinyls:[vinylA, vinylB, vinylC]).toString()
		
		then:
		genreWithNoVinyls.toString() == "Genre, Vinyls:[]"
		genreWithOneVinyl.toString() == "Genre, Vinyls:[A]"
		genreWithManyVinyls.toString() == "Genre, Vinyls:[A, B, C]"
	}
	
	@Unroll
	def "should consider two egnres equal to each other if they have the same name and vinyls"(equals, genre1, genre2) {
		expect:
		equals == (genre1.equals(genre2))
		
		where:
		equals	| genre1										| genre2
		true	| new Genre()									| new Genre()
		false	| new Genre()									| null
		false	| new Genre()									| "a string"
		// name
		true	| new Genre(name:"A")							| new Genre(name:"A")
		true	| new Genre(name:"")							| new Genre(name:"")
		true	| new Genre(name:null)							| new Genre(name:null)
		false	| new Genre(name:null)							| new Genre(name:"A")
		false	| new Genre(name:"A")							| new Genre(name:"")
		false	| new Genre(name:"A")							| new Genre(name:"B")
		false	| new Genre(name:"A")							| new Genre(name:null)
		// vinyls
		true	| new Genre(name:"A", vinyls:[])				| new Genre(name:"A", vinyls:[])
		true	| new Genre(name:"A", vinyls:[vinylA])			| new Genre(name:"A", vinyls:[vinylA])
		true	| new Genre(name:"A", vinyls:null)				| new Genre(name:"A", vinyls:null)
		false	| new Genre(name:"A", vinyls:null)				| new Genre(name:"A", vinyls:[vinylA])
		false	| new Genre(name:"A", vinyls:[vinylA, vinylB])	| new Genre(name:"A", vinyls:[vinylB, vinylA])
		false	| new Genre(name:"A", vinyls:[vinylA])			| new Genre(name:"A", vinyls:[vinylB])
		false	| new Genre(name:"A", vinyls:[vinylA])			| new Genre(name:"A", vinyls:[])
		false	| new Genre(name:"A", vinyls:[vinylA])			| new Genre(name:"A", vinyls:null)
		false	| new Genre(name:"A", vinyls:[vinylA])			| new Genre(name:"A", vinyls:null)
		false	| new Genre(name:"A", vinyls:[vinylA, vinylB])	| new Genre(name:"A", vinyls:[vinylB, vinylC])
	}
	
	def "should have not null default values"() {
		when:
		def genre = new Genre()
		
		then:
		genre.name == ""
		genre.vinyls == []
	}
}