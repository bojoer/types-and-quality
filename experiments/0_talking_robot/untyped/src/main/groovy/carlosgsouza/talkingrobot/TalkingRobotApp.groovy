 package carlosgsouza.talkingrobot

import carlosgsouza.derails.App
import carlosgsouza.derails.View
import carlosgsouza.talkingrobot.TalkingRobotApp;
import carlosgsouza.talkingrobot.view.UiFactory

class TalkingRobotApp extends App {
	
	static def app = new TalkingRobotApp()
	def uiFactory = new UiFactory()
	
	def userName 
	
	TalkingRobotApp() {
		super("Talking Robot - The future is now")
	}
	
	void routeRequest(controller, action, parameter) {
		if(controller == "robot") {
			switch(action) {
				case "bye":
					if(userName) {
						console.render uiFactory.bye(userName)
					} else {
						console.render uiFactory.bye("stranger")
					}
					return
				case "hello":
					if(!userName) {
						def form = uiFactory.nameForm()
						console.apply form
						userName = form.fields.name
					}
					console.render uiFactory.hi(userName)
					return
			}
		}
		
		console.render(new View("command not found"))
	}
	
	void bootstrap() {
		userName = null
	}
	
	public static main(args) {
		app.run()
	}
}