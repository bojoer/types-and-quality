package carlosgsouza.derails


abstract class App {
	
	public String name = "DeRails App"
	public Console console = new Console()
	
	App(name) {
		this.name = name
	}
	
	void run() {
		bootstrap()
		
		println name
		
		print '> '
		System.in.eachLine { line ->
			execute(line)
			print '> '
		}
	}
	
	void execute(String command) {
		try {
			if(command == "exit") {
				println "bye"
				System.exit(0)
			}
			
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
		} catch(e) {
			// e.printStackTrace()
			console.render new View("(error) $e.message")
		}
	}
	
	abstract void routeRequest(String controller, String action, String parameter);
	
	void bootstrap() {
		
	}
}