package carlosgsouza.talkingrobot.view

import carlosgsouza.derails.Form
import carlosgsouza.derails.View




public class UiFactory {
	
	def bye(String name) {
		new View("Bye, $name!")
	}
	
	def nameForm() {
		new Form("My name is Jack Johnson and I am a talking robot. Who are you?", "name")
	}
	
	def hi(name) {
		new View("Hello, $name!")
	}
	def selfdestruct(){
		new View("WHY ARE YOU DOING THIS? I will explode and take you with me!!!")
	}
}
