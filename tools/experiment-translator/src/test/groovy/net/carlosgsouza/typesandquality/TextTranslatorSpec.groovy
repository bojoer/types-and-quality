package net.carlosgsouza.typesandquality

import spock.lang.Specification

class TextTranslatorSpec extends Specification {
	
	def translator
	
	def setup() {
		
		def dictionary = [
				"vinyl" : "celebrity",
				"collection" : "catalog",
				"shop" : "catalog",
				"title" : "name",
				"genre" : "city",
				"artist" : "drink",
				"song" : "car",
				"year" : "age",
				"summary" : "overview",
				"report" : "stats",
				
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
		translator.translateFiles(sourceFolder, destinationFolder)
		
		then:
		destinationFolder.exists()
		
		and:
		new File(destinationFolder, "typed/src/main/groovy").exists()
		new File(destinationFolder, "untyped/src/main/groovy").exists()
		new File(destinationFolder, "gradlew").exists()
		new File(destinationFolder, "specs").exists()
		
		and:
		new File(destinationFolder, "typed/src/main/groovy/celebritycatalog").exists()
		new File(destinationFolder, "typed/src/main/groovy/celebritycatalog/handler").exists()
		new File(destinationFolder, "typed/src/main/groovy/celebritycatalog/handler/DrinkHandler").exists()
		new File(destinationFolder, "typed/src/main/groovy/celebritycatalog/model/Overview.java").exists()
		
		and:
		new File(destinationFolder, "untyped/src/main/groovy/celebritycatalog").exists()
		new File(destinationFolder, "untyped/src/main/groovy/celebritycatalog/database/DB.groovy").exists()
		new File(destinationFolder, "untyped/src/main/groovy/celebritycatalog/handler/DrinkHandler").exists()
		new File(destinationFolder, "untyped/src/main/groovy/celebritycatalog/view/ShowStatsView.groovy").exists()
		
//		cleanup:
//		destinationFolder.deleteDir()
	}
}
