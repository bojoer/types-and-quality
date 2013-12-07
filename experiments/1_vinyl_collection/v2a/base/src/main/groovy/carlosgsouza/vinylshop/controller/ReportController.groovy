package carlosgsouza.vinylshop.controller

import carlosgsouza.vinylshop.database.DB
import carlosgsouza.vinylshop.model.Report
import carlosgsouza.vinylshop.model.Vinyl


class ReportController {
	
	DB db = DB.connect()
	
	Report artist() {
		Report result = new Report()
		
		List<String> artists = db.artists
		int artistCount = artists.size()
		
		result.data["Number of artists"] = artistCount.toString()
		
		if(artistCount == 0) {
			return result
		}
		
		def artist_vinylCount = [:]
		def artist_songCount = [:]
		
		for(artist in artists) {
			List<Vinyl> artistVinyls = db.searchVinylByArtist(artist)
			
			artist_vinylCount[artist] = artistVinyls.size()
			artist_songCount[artist] = 0
			
			for(vinyl in artistVinyls) {
				artist_songCount[artist] += vinyl.songs.size()
			}
		}
		
		def topArtist = artist_vinylCount.max{it.value}.key
		
		result.data["Top artist"] = topArtist
		result.data["Number of vinyls by $topArtist"] = artist_vinylCount[topArtist].toString()
		result.data["Number of songs by $topArtist"] = artist_songCount[topArtist].toString()
		
		return result
	}
	
	Report genre() {
		Report result = new Report()
		
		Set<String> genres = new HashSet<String>() 
		for(vinyl in db.vinyls) {
			genres.add(vinyl.genre)	
		}
		
		result.data["Number of genres"] = genres.size().toString()
		
		if(genres.size() == 0) {
			return result
		}
		
		def genre_vinylCount = [:]
		def genre_songCount = [:]
		for(vinyl in db.vinyls) {
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