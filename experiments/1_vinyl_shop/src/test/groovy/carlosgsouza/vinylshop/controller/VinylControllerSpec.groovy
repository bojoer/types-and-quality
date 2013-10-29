package carlosgsouza.vinylshop.controller

import spock.lang.Specification
import carlosgsouza.vinylshop.database.VinylDB
import carlosgsouza.vinylshop.model.Vinyl;


class VinylControllerSpec extends Specification {
	
	VinylController controller
	
	VinylDB db
	
	Vinyl vinylA
	Vinyl vinylB
	Vinyl vinylC
	
	def setup() {
		vinylA = new Vinyl(id:1, artist:"A", title:"A", songs:["A1", "A2", "A3"], year:"A", genre:"A")
		vinylB = new Vinyl(id:2, artist:"B", title:"B", songs:["B1", "B2", "B3"], year:"B", genre:"B")
		vinylC = new Vinyl(id:3, artist:"C", title:"C", songs:["C1", "C2", "C3"], year:"C", genre:"C")
		
		db = Mock(VinylDB)
		
		controller = new VinylController(db:db)
	}
	
	def "should list all vinyls on the database"() {
		when:
		def all = controller.list()
		
		then:
		1 * controller.getAll() >> [vinylA, vinylB, vinylC]
		
		and:
		all == [vinylA, vinylB, vinylC]
	}
	
	def "should show one vinyl given its id"() {
		when:
		def shownVinyl = controller.show(2)
		
		then:
		1 * db.get(2) >> vinylB
		
		and:
		shownVinyl == vinylB
	}
	
	def "should thrown an exception when a non existent vinyl is requested"() {
		when:
		controller.show(47)
		
		then:
		1 * db.exists(47) >> false
		
		and:
		thrown IllegalArgumentException
	}
	
	def "should delete an album"() {
		when:
		controller.delete(2)
		
		then:
		db.remove(2)
	}
	
	def "should thrown an exception when trying to remove a non existent album"() {
		when:
		controller.delete(198)
		
		then:
		db.get(198) >> null
		
		and:
		thrown IllegalArgumentException
	}
	
	def "should update an album by removing the exiting one form the DB and adding another with the same id"() {
		given:
		def updatedvinylA = new Vinyl(id:1, artist:"A*", title:"A*", songs:["A1*", "A2*", "A3*"], year:"A*", genre:"A*")
		
		when:
		controller.update(updatedvinylA)
		
		then:
		db.remove(1)
		
		and:
		db.add(updatedvinylA)
	}
	
	def "should fail when trying to update an unexistant vinyl"() {
		given:
		def unexistentVinyl = new Vinyl(id:123, artist:"A*", title:"A*", songs:["A1*", "A2*", "A3*"], year:"A*", genre:"A*")
		
		when:
		controller.update(updatedvinylA)
		
		then:
		db.get(1) >> null
		
		and:
		db.add(updatedvinylA)
	}
	
}