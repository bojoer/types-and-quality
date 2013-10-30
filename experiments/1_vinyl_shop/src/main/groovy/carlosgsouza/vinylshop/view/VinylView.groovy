package carlosgsouza.vinylshop.view

import carlosgsouza.vinylshop.model.Vinyl

public class VinylView {
	
	public void list(List<Vinyl> vinylList) {
		vinylList.each {
			println it
		}
	}
	
	public void show(Vinyl vinyl) {
		println vinyl
	}
	
	public void delete() {
		println "Vinyl deleted"
	}
	
	public Vinyl create() {
		println "Please enter the vinyl details below"
		
		def artist = readText("Artist")
		def title = readText("Title")
		def songs = readText("Songs").split(",")*.trim()
		def year = readText("Year")
		def genre = readText("Genre")
		
		new Vinyl(artist:artist, title:title, songs:songs, year:year, genre:genre)
	}
	
	public String readText(field) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
		print "$field:\t"
		
		br.readLine()
	}
}
