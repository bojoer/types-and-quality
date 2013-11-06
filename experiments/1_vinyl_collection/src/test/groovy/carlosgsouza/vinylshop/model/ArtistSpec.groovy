package carlosgsouza.vinylshop.model

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class ArtistSpec extends Specification {
	
	@Shared Vinyl vinylA
	@Shared Vinyl vinylB
	@Shared Vinyl vinylC
	
	def setup() {
		vinylA = new Vinyl(id:1, artist:"Artist", title:"A", songs:["A1", "A2", "A3"], year:"A", genre:"A")
		vinylB = new Vinyl(id:2, artist:"Artist", title:"B", songs:["B1", "B2", "B3"], year:"B", genre:"B")
		vinylC = new Vinyl(id:3, artist:"Artist", title:"C", songs:["C1", "C2", "C3"], year:"C", genre:"C")
	}
	
	def "should generate a string with the name of the artist and his/her albuns"() {
		when:
		def artistWithNoVinyls = new Artist(name: "Artist", vinyls:[]).toString()
		def artistWithOneVinyl = new Artist(name: "Artist", vinyls:[vinylA]).toString()
		def artistWithManyVinyls = new Artist(name: "Artist", vinyls:[vinylA, vinylB, vinylC]).toString()
		
		then:
		artistWithNoVinyls.toString() == "Artist, Vinyls:[]"
		artistWithOneVinyl.toString() == "Artist, Vinyls:[A]"
		artistWithManyVinyls.toString() == "Artist, Vinyls:[A, B, C]"
	}
	
	@Unroll
	def "should consider two artist equal to each other if they have the same name and vinyls"(equals, artist1, artist2) {
		expect:
		equals == (artist1.equals(artist2))
		
		where:
		equals	| artist1										| artist2
		true	| new Artist()									| new Artist()
		false	| new Artist()									| null
		false	| new Artist()									| "a string"
		// name
		true	| new Artist(name:"A")							| new Artist(name:"A")
		true	| new Artist(name:"")							| new Artist(name:"")
		true	| new Artist(name:null)							| new Artist(name:null)
		false	| new Artist(name:null)							| new Artist(name:"A")
		false	| new Artist(name:"A")							| new Artist(name:"")
		false	| new Artist(name:"A")							| new Artist(name:"B")
		false	| new Artist(name:"A")							| new Artist(name:null)
		// vinyls
		true	| new Artist(name:"A", vinyls:[])				| new Artist(name:"A", vinyls:[])
		true	| new Artist(name:"A", vinyls:[vinylA])			| new Artist(name:"A", vinyls:[vinylA])
		true	| new Artist(name:"A", vinyls:null)				| new Artist(name:"A", vinyls:null)
		false	| new Artist(name:"A", vinyls:null)				| new Artist(name:"A", vinyls:[vinylA])
		false	| new Artist(name:"A", vinyls:[vinylA, vinylB])	| new Artist(name:"A", vinyls:[vinylB, vinylA])
		false	| new Artist(name:"A", vinyls:[vinylA])			| new Artist(name:"A", vinyls:[vinylB])
		false	| new Artist(name:"A", vinyls:[vinylA])			| new Artist(name:"A", vinyls:[])
		false	| new Artist(name:"A", vinyls:[vinylA])			| new Artist(name:"A", vinyls:null)
		false	| new Artist(name:"A", vinyls:[vinylA])			| new Artist(name:"A", vinyls:null)
		false	| new Artist(name:"A", vinyls:[vinylA, vinylB])	| new Artist(name:"A", vinyls:[vinylB, vinylC])
	}
	
	def "should have not null default values"() {
		when:
		def artist = new Artist()
		
		then:
		artist == new Artist(name:"", vinyls:[])
	}
}