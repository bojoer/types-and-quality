package carlosgsouza.vinylshop.database

import spock.lang.Specification
import spock.lang.Unroll
import carlosgsouza.vinylshop.model.Vinyl

class DBSpec extends Specification {
	
	DB db
	
	Vinyl vinylA
	Vinyl vinylB
	Vinyl vinylC
	
	
	def setup() {
		db = new DB()
		
		vinylA = new Vinyl(id:1, artist:"A", title:"A", songs:["A1", "A2", "A3"], year:"2001", genre:"A")
		vinylB = new Vinyl(id:2, artist:"B", title:"B", songs:["B1", "B2", "B3"], year:"2002", genre:"B")
		vinylC = new Vinyl(id:3, artist:"C", title:"C", songs:["C1", "C2", "C3"], year:"2003", genre:"C")
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
		def id = db.add(newVinyl)
		
		then:
		id == 4
	}
	
	def "should assign id=1 to the first vinyl added to the database"() {
		given:
		Vinyl newVinyl = new Vinyl(artist:"A", title:"A", songs:["A1", "A2", "A3"], year:"A", genre:"A")
		
		expect:
		db.vinyls == []
		
		when:
		def id = db.add(newVinyl)
		
		then:
		id == 1
	}
	
	def "should return all vinyls on the database"() {
		given:
		db.vinyls = [vinylA, vinylB, vinylC]
		
		when:
		def all = db.all
		
		then:
		all == [vinylA, vinylB, vinylC]
	}
	
	def "should remove a vinyl from the database"() {
		given:
		db.vinyls = [vinylA, vinylB, vinylC]
		
		when:
		db.remove(2)
		
		then:
		db.all == [vinylA, vinylC]
	}
	
	@Unroll
	def "should tell if a vinyl exists or not in the database"(id, exists) {
		given:
		db.vinyls = [vinylA, vinylB, vinylC]
		
		expect:
		def all = db.contains(2)
		
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
		db.all == []
		
		when:
		def vinyl = db.get(89)
		
		then:
		vinyl == null
	}
	
	def "should search for vinyls"() {
		given:
		db.vinyls = [vinylA, vinylB, vinylC]
		
		when:
		def result = db.searchVinylByTitle("A")
		
		then:
		result == [vinylA]
	}
	
	def "should return an empty list if the search for vinyls doesn't match any elements"() {
		given:
		db.vinyls = [vinylA, vinylB, vinylC]
		
		when:
		def result = db.searchVinylByTitle("wont match")
		
		then:
		result == []
	}
	
	def "should return multiple vinyls if the search matches multiple items"() {
		given:
		db.vinyls = [vinylA, vinylA, vinylC]
		
		when:
		def result = db.searchVinylByTitle("A")
		
		then:
		result == [vinylA, vinylA]
	}
	
	def "should match vinyls without considering the case"() {
		given:
		db.vinyls = [vinylA, vinylB, vinylC]
		
		when:
		def result = db.searchVinylByTitle("a")
		
		then:
		result == [vinylA]
	}
	
	def "should list the set of all artists"() {
		given:
		db.vinyls = [vinylA, vinylB, vinylC]
		
		when:
		def result = db.allArtists
		
		then:
		result == ["A", "B", "C"]
	}
	
	def "should not repeat an artist on the list of artists"() {
		given:
		db.vinyls = [vinylA, vinylA, vinylA, vinylA, vinylA]
		
		when:
		def result = db.allArtists
		
		then:
		result == ["A"]
	}
	
	def "should search for vinyls by artist"() {
		given:
		db.vinyls = [vinylA, vinylB, vinylC]
		
		when:
		def result = db.searchVinylByArtist("A")
		
		then:
		result == [vinylA]
	}
	
	def "should return an empty list if the search for vinyls by artist doesn't match any elements"() {
		given:
		db.vinyls = [vinylA, vinylB, vinylC]
		
		when:
		def result = db.searchVinylByArtist("wont match")
		
		then:
		result == []
	}
	
	def "should return multiple vinyls if the search by artist matches multiple items"() {
		given:
		db.vinyls = [vinylA, vinylA, vinylC]
		
		when:
		def result = db.searchVinylByArtist("A")
		
		then:
		result == [vinylA, vinylA]
	}
	
	def "should search for vinyls by year"() {
		given:
		db.vinyls = [vinylA, vinylB, vinylC]
		
		when:
		def result = db.searchVinylByYear("2001")
		
		then:
		result == [vinylA]
	}
	
	def "should return an empty list if the search for vinyls by year doesn't match any elements"() {
		given:
		db.vinyls = [vinylA, vinylB, vinylC]
		
		when:
		def result = db.searchVinylByYear("3379")
		
		then:
		result == []
	}
	
	def "should return multiple vinyls if the search by year matches multiple items"() {
		given:
		db.vinyls = [vinylA, vinylA, vinylC]
		
		when:
		def result = db.searchVinylByYear("2001")
		
		then:
		result == [vinylA, vinylA]
	}
	
	def "should search for vinyls by Genre"() {
		given:
		db.vinyls = [vinylA, vinylB, vinylC]
		
		when:
		def result = db.searchVinylByGenre("A")
		
		then:
		result == [vinylA]
	}
	
	def "should return an empty list if the search for vinyls by Genre doesn't match any elements"() {
		given:
		db.vinyls = [vinylA, vinylB, vinylC]
		
		when:
		def result = db.searchVinylByGenre("no match")
		
		then:
		result == []
	}
	
	def "should return multiple vinyls if the search by Genre matches multiple items"() {
		given:
		db.vinyls = [vinylA, vinylA, vinylC]
		
		when:
		def result = db.searchVinylByGenre("A")
		
		then:
		result == [vinylA, vinylA]
	}
	
	def "should search for vinyls by Song"() {
		given:
		db.vinyls = [vinylA, vinylB, vinylC]
		
		when:
		def result = db.searchVinylBySong("A1")
		
		then:
		result == [vinylA]
	}
	
	def "should return an empty list if the search for vinyls by Song doesn't match any elements"() {
		given:
		db.vinyls = [vinylA, vinylB, vinylC]
		
		when:
		def result = db.searchVinylBySong("no match")
		
		then:
		result == []
	}
	
	def "should return multiple vinyls if the search by Song matches multiple items"() {
		given:
		db.vinyls = [vinylA, vinylA, vinylC]
		
		when:
		def result = db.searchVinylBySong("A1")
		
		then:
		result == [vinylA, vinylA]
	}
}