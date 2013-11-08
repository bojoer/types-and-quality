package carlosgsouza.talkingrobot.functional

import spock.lang.Specification
import carlosgsouza.derails.Console
import carlosgsouza.derails.Form
import carlosgsouza.derails.View
import carlosgsouza.talkingrobot.TalkingRobotApp

class TalkingRobotFunctionalSpec extends Specification {
	
	TalkingRobotApp app
	
	def setup() {
		app = new TalkingRobotApp()
		app.console = Mock(Console)
	}
	
	def "when the user says hello for the first time, robot should ask for her name and then address the user by her name in future hellos"() {
		when: "the user says hello for the first time"
		app.execute "hello robot"
		
		then: "robot should ask for the user name"
		1 * app.console.apply { Form form ->
			assert form.title == "My name is Jack Johnson and I am a talking robot. Who are you?"
			assert form.fieldName == ["name"]
			
			return true
		} >> { Form form -> form.fields = ["name":"Carlos"] }
		
		and: "say hello to the user using the name filled in the form"
		1 * app.console.render{ View view -> view.items == ["Hello, Carlos!"] }
		
		when: "the user says hello again"
		app.execute "hello robot"
		
		then: "the robot already knows the user name"
		1 * app.console.render{ View view -> view.items == ["Hello, Carlos!"] }
	}
	
	def "when the user says bye before saying hello, robot should say 'Bye, stranger!'"() {
		when: "the user says bye before saying hello"
		app.execute "bye robot"
		
		then: "the robot already knows the user name"
		1 * app.console.render{ View view -> view.items == ["Bye, stranger!"] }
	}
	
	def "when the user says bye after saying his name, robot should say 'Bye, <name>'"() {
		given: "the user said hello and introduced himself"
		app.console.apply(_) >> { Form form -> form.fields = ["name":"Carlos"] }
		app.execute "hello robot"
		
		when: "the user says bye"
		app.execute "bye robot"
		
		then: "the robot already knows the user name"
		1 * app.console.render{ View view -> view.items == ["Bye, Carlos!"] }
	}
	
	def "when the user asks robot to destroy itself, it doesn't like it very much"() {
		when: "the user asks robot to selfdestruct"
		app.execute "selfdestruct robot"
		
		then: "robot doesn't like it"
		1 * app.console.render{ View view -> view.items == ["WHY ARE YOU DOING THIS? I will explode and take you with me!!!"] }
	}
	
}