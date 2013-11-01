package carlosgsouza.vinylshop.controller

import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.model.Report

class ReportController {
	
	DB db = DB.connect()
	
	Report artist() {
		Report result = new Report()
		
		def artistCount = db.all*.artist.unique().size()
		result.data["Number of artists"] = artistCount.toString()
		
		if(artistCount == 0) {
			return result
		}
		
		def artist_vinylCount = [:]
		def artist_songCount = [:]
		db.all.each { vinyl ->
			if(!artist_vinylCount[vinyl.artist]) {
				artist_vinylCount[vinyl.artist] = 0
				artist_songCount[vinyl.artist] = 0
			}
			artist_vinylCount[vinyl.artist]++
			artist_songCount[vinyl.artist] += vinyl.songs.size()
		}
		
		def topArtist = artist_vinylCount.max{it.value}.key
		
		
		result.data["Top artist"] = topArtist
		result.data["Number of vinyls by $topArtist"] = artist_vinylCount[topArtist].toString()
		result.data["Number of songs by $topArtist"] = artist_songCount[topArtist].toString()
		
		return result
	}
	
	Report genre() {
		Report result = new Report()
		
		def genreCount = db.all*.genre.unique().size()
		result.data["Number of genres"] = genreCount.toString()
		
		if(genreCount == 0) {
			return result
		}
		
		def genre_vinylCount = [:]
		def genre_songCount = [:]
		db.all.each { vinyl ->
			if(!genre_vinylCount[vinyl.genre]) {
				genre_vinylCount[vinyl.genre] = 0
				genre_songCount[vinyl.genre] = 0
			}
			genre_vinylCount[vinyl.genre]++
			genre_songCount[vinyl.genre] += vinyl.songs.size()
		}
		
		def topGenre = genre_vinylCount.max{it.value}.key
		
		
		result.data["Top genre"] = topGenre
		result.data["Number of $topGenre vinyls"] = genre_vinylCount[topGenre].toString()
		result.data["Number of $topGenre songs"] = genre_songCount[topGenre].toString()
		
		return result
	}
	
}