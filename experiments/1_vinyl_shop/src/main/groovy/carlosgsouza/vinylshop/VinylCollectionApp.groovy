package carlosgsouza.vinylshop

import carlosgsouza.derails.App
import carlosgsouza.derails.Form
import carlosgsouza.vinylshop.controller.VinylController
import carlosgsouza.vinylshop.model.Vinyl
import carlosgsouza.vinylshop.view.ViewFactory

class VinylCollectionApp extends App {
	
	VinylController vinylController = new VinylController()
	ViewFactory viewFactory = new ViewFactory()
	
	VinylCollectionApp() {
		super("DJ PopCorn - Amazing Vinyl Collection")
	}
	
	void routeRequest(String controller, String action, String parameter) {
		if(controller == "vinyl") {
			switch(action) {
				case "list":
					def vinyls = vinylController.list()
					console.render viewFactory.list(vinyls)
					return
				case "create":
					def form = new Form("Please enter the vinyl details below", "Artist", "Title", "Songs", "Year", "Genre")
					console.apply form
					
					def vinyl = new Vinyl(
						artist:form.field["Artist"],
						title:form.field["Title"],
						songs:form.field["Songs"].split(",")*.trim(),
						year:form.field["Year"],
						genre:form.field["Genre"])
					
					def id = vinylController.create(vinyl)
					def createdVinyl = vinylController.get(id)
					
					console.render viewFactory.show(createdVinyl)
					return
				case "show":
					def id = Integer.valueOf parameter
					def vinyl = vinylController.get(id)
					
					console.render viewFactory.show(vinyl)
					return
				case "delete":
					def id = Integer.valueOf parameter
					vinylController.delete(id)
					
					console.render viewFactory.delete()
					return
				case "find":
					def result = vinylController.find(parameter)
					console.render viewFactory.list(result)
					return
			}
		}
		
		println "command not found"
	}
	
	void bootstrap() {
		vinylController.create new Vinyl(artist:"Lana Del Rey", title:"Born to Dei", songs:["Off to Races", "Radio", "Carmen"], year:"2012", genre:"Pop")
		vinylController.create new Vinyl(artist:"Bruno Mars", title:"Unorthodox Jukebox", songs:["Gorilla", "Treasure", "Young Girls"], year:"2012", genre:"Pop")
		vinylController.create new Vinyl(artist:"Pearl Jam", title:"Lightning Bolt", songs:["Getaway", "Mind Your Manners", "Young Sirens"], year:"2013", genre:"Rock")
		vinylController.create new Vinyl(artist:"Temple of Shadows", title:"Angra", songs:["Spread Your Fire", "Deus Le Volt!", "Waiting Silence"], year:"2004", genre:"Metal")
		vinylController.create new Vinyl(artist:"Luan Santana", title:"Quando Chega a Noite", songs:["Te Esperando", "Te vivo", "Química do Amor"], year:"2010", genre:"Rock")
		vinylController.create new Vinyl(artist:"Coldplay", title:"Parachutes", songs:["Don't Panic", "Shiver", "Spies"], year:"2000", genre:"Alternative")
		vinylController.create new Vinyl(artist:"Pearl Jam", title:"Backspacer", songs:["Just Breathe", "Mind Your Amongst the Waves", "Supersonic"], year:"2009", genre:"Rock")
	}
	
	public static void main(String[] args) {
		new VinylCollectionApp().run()
	}
}