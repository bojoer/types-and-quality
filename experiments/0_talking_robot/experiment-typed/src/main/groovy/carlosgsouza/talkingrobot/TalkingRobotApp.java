package carlosgsouza.talkingrobot;

import carlosgsouza.derails.App;
import carlosgsouza.derails.Form;
import carlosgsouza.derails.View;
import carlosgsouza.talkingrobot.view.UiFactory;

public class TalkingRobotApp extends App {
	
	static TalkingRobotApp app = new TalkingRobotApp();
	UiFactory uiFactory = new UiFactory();
	
	String userName;
	
	TalkingRobotApp() {
		super("Talking Robot - The future is now");
	}
	
	public void routeRequest(String controller, String action, String parameter) {
		if(controller.equals("robot")) {
			if(action.equals("bye")) {
				if(userName != null) {
					console.render(uiFactory.bye(userName));
				} else {
					console.render(uiFactory.bye("stranger"));
				}
				return;
			} else if(action.equals("hello")) {
				if(userName == null) {
					Form form = uiFactory.nameForm();
					console.apply(form);
					userName = form.fields.get("name");
				}
				console.render(uiFactory.hi(userName));
				return;
			} else if(action.equals("selfdestruct")) {
				console.render(uiFactory.selfdestruct());
				return;
			}
		}
		
		console.render(new View("command not found"));
	}
	
	public void bootstrap() {
		userName = null;
	}
	
	public static void main(String[] args) {
		app.run();
	}
}