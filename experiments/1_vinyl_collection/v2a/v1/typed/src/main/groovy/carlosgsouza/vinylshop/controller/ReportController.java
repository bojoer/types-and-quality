package carlosgsouza.vinylshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import carlosgsouza.vinylshop.database.DB;
import carlosgsouza.vinylshop.model.Report;
import carlosgsouza.vinylshop.model.Vinyl;


public class ReportController {;
	
	DB db = DB.connect();
	
	Report artist() {
		Report result = new Report();
		
		List<String> artists = db.getArtists();
		Integer artistCount = artists.size();
		
		result.getData().put("Number of artists", artistCount.toString());
		
		if(artistCount == 0) {
			return result;
		}
		
		Map<String, Integer> artist_vinylCount = new HashMap<String, Integer>();
		Map<String, Integer> artist_songCount = new HashMap<String, Integer>();
		
		for(String artist : artists) {
			List<Vinyl> artistVinyls = db.searchVinylByArtist(artist);
			
			artist_vinylCount.put(artist, artistVinyls.size());
			artist_songCount.put(artist, 0);
			
			for(Vinyl vinyl : artistVinyls) {
				artist_songCount.put(artist, artist_songCount.get(artist) + vinyl.songs.size());
			}
		}
		
		String topArtist = "";
		int topArtistVinylCount = 0;
		for(String artst : artist_vinylCount.keySet()) {
			if(topArtistVinylCount < artist_vinylCount.get(artst)) {
				topArtist = artst;
				topArtistVinylCount = artist_vinylCount.get(artst);
			}
		}
		
		result.getData().put("Top artist", topArtist);
		result.getData().put("Number of vinyls by "+topArtist, artist_vinylCount.get(topArtist).toString());
		result.getData().put("Number of songs by "+topArtist, artist_songCount.get(topArtist).toString());
		
		return result;
	}
	
	
	
	Report genre() {
		Report result = new Report();
		
		List<String> genres = db.getGenres();
		Integer genreCount = genres.size();
		
		result.getData().put("Number of genres", genreCount.toString());
		
		if(genreCount == 0) {
			return result;
		}
		
		Map<String, Integer> genre_vinylCount = new HashMap<String, Integer>();
		Map<String, Integer> genre_songCount = new HashMap<String, Integer>();
		
		for(String genre : genres) {
			List<Vinyl> genreVinyls = db.searchVinylByGenre(genre);
			
			genre_vinylCount.put(genre, genreVinyls.size());
			genre_songCount.put(genre, 0);
			
			for(Vinyl vinyl : genreVinyls) {
				genre_songCount.put(genre, genre_songCount.get(genre) + vinyl.songs.size());
			}
		}
		
		String topGenre = "";
		int topGenreVinylCount = 0;
		for(String artst : genre_vinylCount.keySet()) {
			if(topGenreVinylCount < genre_vinylCount.get(artst)) {
				topGenre = artst;
				topGenreVinylCount = genre_vinylCount.get(artst);
			}
		}
		
		result.getData().put("Top genre", topGenre);
		result.getData().put("Number of "+topGenre+" vinyls", genre_vinylCount.get(topGenre).toString());
		result.getData().put("Number of "+topGenre+" songs", genre_songCount.get(topGenre).toString());
		
		return result;
	}
	
}
