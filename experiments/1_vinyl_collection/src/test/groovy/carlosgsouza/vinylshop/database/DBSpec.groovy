package carlosgsouza.vinylshop.database

import spock.lang.Specification
import spock.lang.Unroll
import carlosgsouza.vinylshop.model.Artist
import carlosgsouza.vinylshop.model.Vinyl

class DBSpec extends Specification {
	
	DB db
	
	Vinyl vinylA
	Vinyl vinylB
	Vinyl vinylC
	Vinyl vinylD1
	Vinyl vinylD2
	
	Artist artistA
	Artist artistB
	Artist artistC
	Artist artistD
	Artist artistDWithVinylD1
	
	def setup() {
		db = new DB()
		
		vinylA = new Vinyl(id:1, artist:"A", title:"A", songs:["A1", "A2", "A3"], year:"2001", genre:"A")
		vinylB = new Vinyl(id:2, artist:"B", title:"B", songs:["B1", "B2", "B3"], year:"2002", genre:"B")
		vinylC = new Vinyl(id:3, artist:"C", title:"C", songs:["C1", "C2", "C3"], year:"2003", genre:"C")
		vinylD1 = new Vinyl(id:4, artist:"D", title:"D1", songs:["D11", "D12", "D13"], year:"2003", genre:"D")
		vinylD2 = new Vinyl(id:5, artist:"D", title:"D2", songs:["D21", "D22", "D23"], year:"2003", genre:"D")
		
		artistA = new Artist(name:"A", vinyls:[vinylA])
		artistB = new Artist(name:"B", vinyls:[vinylA])
		artistC = new Artist(name:"C", vinyls:[vinylA])
		artistDWithVinylD1 = new Artist(name:"D", vinyls:[vinylD1])
		artistD = new Artist(name:"D", vinyls:[vinylD1, vinylD2])
	}
	
	def "should return the same DB instance for multiple connections"() {
		when:
		def db1 = DB.connect()
		def db2 = DB.connect()
		
		then:
		db1.is db2
	}
	
	def "should auto generate an id and add it to the database"() {
		given:
		db.vinyls = [vinylA, vinylB, vinylC]
		
		and:
		Vinyl newVinyl = new Vinyl(artist:"A", title:"A", songs:["A1", "A2", "A3"], year:"A", genre:"A")
		
		when:
		def id = db.addVinyl(newVinyl)
		
		then:
		id == 4
	}
	
	def "should assign id=1 to the first vinyl added to the database"() {
		given:
		Vinyl newVinyl = new Vinyl(artist:"A", title:"A", songs:["A1", "A2", "A3"], year:"A", genre:"A")
		
		expect:
		db.vinyls == []
		
		when:
		def id = db.addVinyl(newVinyl)
		
		then:
		id == 1
	}
	
	def "should return all vinyls on the database"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylB)
		db.addVinyl(vinylC)
		
		when:
		def all = db.vinyls
		
		then:
		all == [vinylA, vinylB, vinylC]
	}
	
	def "should remove a vinyl from the database"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylB)
		db.addVinyl(vinylC)
		
		when:
		db.removeVinyl(2)
		
		then:
		db.vinyls == [vinylA, vinylC]
	}
	
	@Unroll
	def "should tell if a vinyl exists or not in the database"(id, exists) {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylB)
		db.addVinyl(vinylC)
		
		expect:
		def all = db.containsVinyl(2)
		
		where:
		id		| exists
		1		| true
		2		| true
		3		| true
		4		| false
		null	| false
	}
	
	def "should return null if a non existent vinyl is requested"() {
		expect:
		db.vinyls == []
		
		when:
		def vinyl = db.getVinyl(89)
		
		then:
		vinyl == null
	}
	
	def "should search for vinyls"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylB)
		db.addVinyl(vinylC)
		
		when:
		def result = db.searchVinylByTitle("A")
		
		then:
		result == [vinylA]
	}
	
	def "should return an empty list if the search for vinyls doesn't match any elements"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylB)
		db.addVinyl(vinylC)
		
		when:
		def result = db.searchVinylByTitle("wont match")
		
		then:
		result == []
	}
	
	def "should return multiple vinyls if the search matches multiple items"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylA)
		db.addVinyl(vinylC)
		
		when:
		def result = db.searchVinylByTitle("A")
		
		then:
		result == [vinylA, vinylA]
	}
	
	def "should match vinyls without considering the case"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylB)
		db.addVinyl(vinylC)
		
		when:
		def result = db.searchVinylByTitle("a")
		
		then:
		result == [vinylA]
	}
	
	def "should list the set of all Genres"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylB)
		db.addVinyl(vinylC)
		
		when:
		def result = db.genres
		
		then:
		result == ["A", "B", "C"]
	}
	
	def "should not repeat an Genre on the list of Genres"() {
		given:
		3.times{ db.addVinyl(vinylA) }
		
		when:
		def result = db.genres
		
		then:
		result == ["A"]
	}
	
	def "should list the set of all Years"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylB)
		db.addVinyl(vinylC)
		
		when:
		def result = db.years
		
		then:
		result == ["2001", "2002", "2003"]
	}
	
	def "should not repeat an Year on the list of Years"() {
		given:
		5.times{ db.addVinyl(vinylA) }
		
		when:
		def result = db.years
		
		then:
		result == ["2001"]
	}
	
	
	def "should list the set of all Songs"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylB)
		db.addVinyl(vinylC)
		
		when:
		def result = db.songs
		
		then:
		result == ["A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3"]
	}
	
	def "should not repeat an Song on the list of Songs"() {
		given:
		3.times{ db.addVinyl(vinylA) }
		
		when:
		def result = db.songs
		
		then:
		result == ["A1", "A2", "A3"]
	}
	
	
	def "should search for vinyls by artist"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylB)
		db.addVinyl(vinylC)
		
		when:
		def result = db.searchVinylByArtist("A")
		
		then:
		result == [vinylA]
	}
	
	def "should match an artist if the vinyl artist contains part of the query"() {
		given:
		def vinyl = new Vinyl(artist:"Artist")
		db.addVinyl(vinyl)
		
		when:
		def result = db.searchVinylByArtist("Art")
		
		then:
		result == [vinyl]
	}
	
	def "should match an artist if the query case doesnt match the artist name case"() {
		given:
		def vinyl = new Vinyl(artist:"artist")
		db.addVinyl(vinyl)
		
		when:
		def result = db.searchVinylByArtist("ARTIST")
		
		then:
		result == [vinyl]
	}
	
	def "should return an empty list if the search for vinyls by artist doesn't match any elements"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylB)
		db.addVinyl(vinylC)
		
		when:
		def result = db.searchVinylByArtist("wont match")
		
		then:
		result == []
	}
	
	def "should return multiple vinyls if the search by artist matches multiple items"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylA)
		db.addVinyl(vinylC)
		
		when:
		def result = db.searchVinylByArtist("A")
		
		then:
		result == [vinylA, vinylA]
	}
	
	def "should search for vinyls by year"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylB)
		db.addVinyl(vinylC)
		
		when:
		def result = db.searchVinylByYear("2001")
		
		then:
		result == [vinylA]
	}
	
	def "should return an empty list if the search for vinyls by year doesn't match any elements"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylB)
		db.addVinyl(vinylC)
		
		when:
		def result = db.searchVinylByYear("3379")
		
		then:
		result == []
	}
	
	def "should return multiple vinyls if the search by year matches multiple items"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylA)
		db.addVinyl(vinylC)
		
		when:
		def result = db.searchVinylByYear("2001")
		
		then:
		result == [vinylA, vinylA]
	}
	
	def "should search for vinyls by Genre"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylB)
		db.addVinyl(vinylC)
		
		when:
		def result = db.searchVinylByGenre("A")
		
		then:
		result == [vinylA]
	}
	
	def "should return an empty list if the search for vinyls by Genre doesn't match any elements"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylB)
		db.addVinyl(vinylC)
		
		when:
		def result = db.searchVinylByGenre("no match")
		
		then:
		result == []
	}
	
	def "should return multiple vinyls if the search by Genre matches multiple items"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylA)
		db.addVinyl(vinylC)
		
		when:
		def result = db.searchVinylByGenre("A")
		
		then:
		result == [vinylA, vinylA]
	}
	
	def "should search for vinyls by Song"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylB)
		db.addVinyl(vinylC)
		
		when:
		def result = db.searchVinylBySong("A1")
		
		then:
		result == [vinylA]
	}
	
	def "should return an empty list if the search for vinyls by Song doesn't match any elements"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylB)
		db.addVinyl(vinylC)
		
		when:
		def result = db.searchVinylBySong("no match")
		
		then:
		result == []
	}
	
	def "should return multiple vinyls if the search by Song matches multiple items"() {
		given:
		db.addVinyl(vinylA)
		db.addVinyl(vinylA)
		db.addVinyl(vinylC)
		
		when:
		def result = db.searchVinylBySong("A1")
		
		then:
		result == [vinylA, vinylA]
	}
	
	def "should add an artist to the database whenever an album with a new artist is added"() {
		given:
		db.vinyls = []
		db.artists = []
		
		when:
		db.addVinyl(vinylA)
		
		then:
		db.artists == ["A"]
	}
	
	def "should update an artist whenever a new vinyl by that artist is added"() {
		given:
		def artistDWithVinylD1 = new Artist(name:"D", vinyls:[vinylD1])
		
		when:
		db.addVinyl(vinylD1)
		
		then:
		db.artists == [artistDWithVinylD1.name] 
		
		when:
		db.addVinyl(vinylD2)
		
		then:
		db.artists == [artistD.name]
	}
	
	def "should update an artist whenever a new vinyl by that artist is removed"() {
		given:
		db.addVinyl(vinylD1)
		db.addVinyl(vinylD2)
		
		expect:
		db.artists == [artistD.name] 
		
		when:
		db.removeVinyl(vinylD2.id)
		
		then:
		db.artists == [artistDWithVinylD1.name]
		
		when:
		db.removeVinyl(vinylD1.id)
		
		then:
		db.artists == []
	}
	
	
	/*
	 * Tests with DB.artists returning artists instead of their names
	 *
	 
	 def "should add an artist to the database whenever an album with a new artist is added"() {
		given:
		db.vinyls = []
		db.artists = []
		
		when:
		db.addVinyl(vinylA)
		
		then:
		db.artists == [new Artist(name:"A", vinyls:[vinylA])]
	}
	
	def "should update an artist whenever a new vinyl by that artist is added"() {
		given:
		def artistDWithVinylD1 = new Artist(name:"D", vinyls:[vinylD1])
		
		when:
		db.addVinyl(vinylD1)
		
		then:
		db.artists == [artistDWithVinylD1] 
		
		when:
		db.addVinyl(vinylD2)
		
		then:
		db.artists == [artistD]
	}
	
	def "should update an artist whenever a new vinyl by that artist is removed"() {
		given:
		db.addVinyl(vinylD1)
		db.addVinyl(vinylD2)
		
		expect:
		db.artists == [artistD] 
		
		when:
		db.removeVinyl(vinylD2.id)
		
		then:
		db.artists == [artistDWithVinylD1]
		
		when:
		db.removeVinyl(vinylD1.id)
		
		then:
		db.artists == []
	}
	 */
}