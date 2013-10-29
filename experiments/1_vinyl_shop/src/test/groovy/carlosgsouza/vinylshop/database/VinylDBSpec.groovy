package carlosgsouza.vinylshop.database

import spock.lang.Specification
import carlosgsouza.vinylshop.model.Vinyl

class VinylDBSpec extends Specification {
	
	VinylDB db
	
	Vinyl vinylA
	Vinyl vinylB
	Vinyl vinylC
	
	
	def setup() {
		db = new VinylDB()
		
		vinylA = new Vinyl(id:1, artist:"A", title:"A", songs:["A1", "A2", "A3"], year:"A", genre:"A")
		vinylB = new Vinyl(id:2, artist:"B", title:"B", songs:["B1", "B2", "B3"], year:"B", genre:"B")
		vinylC = new Vinyl(id:3, artist:"C", title:"C", songs:["C1", "C2", "C3"], year:"C", genre:"C")
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
	
	def "should fail when trying to add an invalid vinyl"() {
		given:
		def vinyl = Mock(Vinyl) {
			isValid() >> false
		}
		
		when:
		db.add(vinyl)
		
		then:
		thrown IllegalArgumentException
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
}