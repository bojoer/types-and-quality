package carlosgsouza.vinylshop

import jline.console.ConsoleReader
import carlosgsouza.vinylshop.controller.VinylController
import carlosgsouza.vinylshop.model.Vinyl
import carlosgsouza.vinylshop.view.VinylView

class App {
	
	ConsoleReader console = new ConsoleReader()
	
	VinylController vinylController = new VinylController()
	VinylView vinylView = new VinylView()
	
	void run() {
		println "Vinyl Collection"
		
		System.in.eachLine { line ->
			if(line == "exit") {
				println "bye"
				System.exit(0)
			}
			
			routeRequest(line)
		}
	}
	
	void routeRequest(String command) {
		def tokens = command.tokenize()
		
		if(tokens.size() < 2) {
			println "Unreckognized command"
			return
		}
		
		def controller = tokens[1].toLowerCase()
		def action = tokens[0].toLowerCase()
		
		tokens.remove(0)
		tokens.remove(0)
		
		def parameter = tokens.join(" ")
		
		routeRequest(controller, action, parameter)
	}
	
	void routeRequest(String controller, String action, String parameter) {
		if(controller == "vinyl") {
			if(action == "list") {
				def vinylList = vinylController.list()
				vinylView.renderList(vinylList)
			}
		}
	}
	
	void fillDB() {
		vinylController.create new Vinyl(id:1, artist:"A", title:"A", songs:["A1", "A2", "A3"], year:"A", genre:"A")
		vinylController.create new Vinyl(id:2, artist:"B", title:"B", songs:["B1", "B2", "B3"], year:"B", genre:"B")
		vinylController.create new Vinyl(id:3, artist:"C", title:"C", songs:["C1", "C2", "C3"], year:"C", genre:"C")
	}
	
	public static void main(String[] args) {
		def app = new App()
		app.fillDB()
		app.run()
	}
}