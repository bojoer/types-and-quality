package carlosgsouza.vinylshop

import jline.console.ConsoleReader
import carlosgsouza.vinylshop.controller.VinylController
import carlosgsouza.vinylshop.view.VinylView

abstract class DeRailsApp {
	
	String name
	
	DeRailsApp(name) {
		this.name = name
	}
	
	void run() {
		bootstrap()
		
		println name
		
		print '> '
		System.in.eachLine { line ->
			try {
				if(line == "exit") {
					println "bye"
					System.exit(0)
				}
				
				routeRequest(line)
				
				print '> '
			} catch(e) {
				println "(error) $e.message"
			}
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
	
	abstract void routeRequest(String controller, String action, String parameter);
	
	void bootstrap() {
		
	}
}