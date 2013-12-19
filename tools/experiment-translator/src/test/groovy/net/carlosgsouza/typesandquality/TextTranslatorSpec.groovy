package net.carlosgsouza.typesandquality

import spock.lang.Specification

class TextTranslatorSpec extends Specification {
	
	def translator
	
	def setup() {
		
		def dictionary = [
		        "DJ PopCorn - Amazing Vinyl Collection" : "The Celbrities Catalog - Adding stats to your party",
				
				"Born to Die" : "Edy",
				"Unorthodox Jukebox" : "Cris",
				"Lightning Bolt" : "Mateus",
				"Temple of Shadows" : "Donnys",
				"Quando Chega a Noite" : "Danielzin",
				"Parachutes" : "Gush",
				"Backspacer" : "Lidy",
				
				"Lana Del Rey" : "Nova skin",
				"Bruno Mars" : "51",
				"Pearl Jam" : "Dreher",
				"Angra" : "Orange Juice",
				"Luan Santana" : "Balalaika",
				"Coldplay" : "Catuaba Selvagem",
				
				"2012" : "24",
				"2013" : "16", 
				"2004" : "85",
				"2009" : "18",
				"2010" : "21",
				"2000" : "33",
				
				"Pop" : "Hollywood",
				"Rock" : "Contagem City",
				"Alternative" : "Paris",
				"Metal" : "Las Vegas",
				
				"Off to Races" : "Chevette", 
				"Radio" : "Honda Bis", 
				"Carmen" : "Gol Power",
				"Gorilla" : "Ferrari", 
				"Treasure" : "Lamborghini", 
				"Young Girls" : "Corsa",
				"Getaway" : "Punto", 
				"Mind Your Manners" : "Brasilia", 
				"Young Sirens" : "Carrim de Rolima",
				"Deus Le Volt!" : "S21", 
				"Waiting Silence" : "Wark Tank",
				"Te vivo" : "Volvo", 
				"Quimica do Amor" : "Scania",
				"Don't Panic" : "Hyunday", 
				"Shiver" : "Fitim 147", 
				"Spies" : "Fusca",
				"Just Breathe" : "Kombi", 
				"Supersonic" : "Caloi",
				
				"vinylshop" : "celebritycatalog",
				"vinyls" : "celebrities",
				"vinyl" : "celebrity",
				"collection" : "catalog",
				"shop" : "catalog",
				"title" : "name",
				"genres" : "cities",
				"genre" : "city",
				"artist" : "drink",
				"song" : "car",
				"year" : "age",
				"summary" : "overview",
				
				"controller" : "handler"
			]
	
		translator = new Translator(dictionary)
	}

	def "should translate the contents of a file"() {
		given:
		def text = """
import net.carlosgsouza.vinylcollection.*

public class Vinyl {
	public id;
	public artist;
	private ArtistController
	private GenreView
	private YearForm yearForm;
	
	public Vinyl() {}
	
	public Vinyl(artist, title, songs, year, genre) {
		this.artist = artist;
		this.title = "\$title";
	}
"""
		when:
		def result = translator.translateText(text)
		
		then:
		result == """
import net.carlosgsouza.celebritycatalog.*

public class Celebrity {
	public id;
	public drink;
	private DrinkHandler
	private CityView
	private AgeForm ageForm;
	
	public Celebrity() {}
	
	public Celebrity(drink, name, cars, age, city) {
		this.drink = drink;
		this.name = "\$name";
	}
"""
	}
	
	def "should translate the name of all files and directories"() {
		given:
		def sourceFolder = new File("src/test/vinyl-collection-project")
		def destinationFolder = new File("test-celebrity-catalog-project")
		
		when:
		translator.translateProject(sourceFolder, destinationFolder)
		
		then:
		destinationFolder.exists()
		
		and:
		new File(destinationFolder, "typed/src/main/groovy").exists()
		new File(destinationFolder, "untyped/src/main/groovy").exists()
		new File(destinationFolder, "gradlew").exists()
		new File(destinationFolder, "specs").exists()
		
		and:
		new File(destinationFolder, "typed/src/main/groovy/carlosgsouza/celebritycatalog").exists()
		new File(destinationFolder, "typed/src/main/groovy/carlosgsouza/celebritycatalog/handler").exists()
		new File(destinationFolder, "typed/src/main/groovy/carlosgsouza/celebritycatalog/handler/DrinkHandler.java").exists()
		new File(destinationFolder, "typed/src/main/groovy/carlosgsouza/celebritycatalog/model/Overview.java").exists()
		
		and:
		new File(destinationFolder, "untyped/src/main/groovy/carlosgsouza/celebritycatalog").exists()
		new File(destinationFolder, "untyped/src/main/groovy/carlosgsouza/celebritycatalog/database/DB.groovy").exists()
		new File(destinationFolder, "untyped/src/main/groovy/carlosgsouza/celebritycatalog/handler/DrinkHandler.groovy").exists()
		new File(destinationFolder, "untyped/src/main/groovy/carlosgsouza/celebritycatalog/view/ListCitiesView.groovy").exists()
		
		and:
		new File(destinationFolder, "untyped/src/main/groovy/carlosgsouza/celebritycatalog/handler/DrinkHandler.groovy").text.contains("class DrinkHandler")
		
		and:
		def appText = new File(destinationFolder, "untyped/src/main/groovy/carlosgsouza/celebritycatalog/CelebrityCatalogApp.groovy").text
		println appText
		appText.contains("The Celbrities Catalog - Adding stats to your party")
		appText.contains("Caloi")
		appText.contains("Edy")
		appText.contains("Contagem City")
		appText.contains("32")
		
		cleanup:
		destinationFolder.deleteDir()
	}
}
