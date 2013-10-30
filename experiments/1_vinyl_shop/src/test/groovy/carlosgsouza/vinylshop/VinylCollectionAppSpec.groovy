package carlosgsouza.vinylshop

import spock.lang.Specification
import carlosgsouza.vinylshop.controller.VinylController
import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.model.Vinyl
import carlosgsouza.vinylshop.view.VinylView


class VinylCollectionAppSpec extends Specification {
	
	VinylController vinylController
	VinylView vinylView
	
	Vinyl vinylA
	Vinyl vinylB
	Vinyl vinylC
	
	VinylCollectionApp app
	
	def setup() {
		vinylA = new Vinyl(id:1, artist:"A", title:"A", songs:["A1", "A2", "A3"], year:"A", genre:"A")
		vinylB = new Vinyl(id:2, artist:"B", title:"B", songs:["B1", "B2", "B3"], year:"B", genre:"B")
		vinylC = new Vinyl(id:3, artist:"C", title:"C", songs:["C1", "C2", "C3"], year:"C", genre:"C")
		
		
		vinylController = Mock(VinylController)
		vinylView = Mock(VinylView)
		
		app = new VinylCollectionApp(vinylController:vinylController, vinylView:vinylView)
	}
	
	def "should create a bunch of vinyl during bootstrap"() {
		when:
		app.bootstrap()
		
		then:
		(1.._) * vinylController.create(_)
	}
	
	def "should list vinyls"() {
		when:
		app.routeRequest("vinyl", "list", null)
		
		then:
		1 * vinylController.list() >> [vinylA, vinylB]
		1 * vinylView.list([vinylA, vinylB])
	}
	
	def "should create a vinyl"() {
		when:
		app.routeRequest("vinyl", "create", null)
		
		then:
		1 * vinylView.create() >> vinylA
		1 * vinylController.create(vinylA) >> 1
		1 * vinylController.show(1) >> vinylA
		1 * vinylView.show(vinylA)
	}
	
	def "should delete a vinyl"() {
		when:
		app.routeRequest("vinyl", "delete", "1")
		
		then:
		1 * vinylController.delete(1)
		1 * vinylView.delete()
	}
	
	def "should show a vinyl"() {
		when:
		app.routeRequest("vinyl", "show", "1")
		
		then:
		1 * vinylController.show(1) >> vinylA
		1 * vinylView.show(vinylA)
	}
	
}