package carlosgsouza.vinylshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import carlosgsouza.vinylshop.database.DB;
import carlosgsouza.vinylshop.model.Report;
import carlosgsouza.vinylshop.model.Vinyl;


public class ReportController {
	
	def db = DB.connect();
	def stuff;
	
	public artist() {
		def result = new Report();
		
		def artists = db.getArtists();
		def artistCount = artists.size();
		
		result.data.put("Number of artists", artistCount);
		
		if(artistCount == 0) {
			return result;
		}
		
		def artist_vinylCount = new HashMap<String, Integer>();
		def artist_songCount = new HashMap<String, Integer>();
		
		for(def artist : artists) {
			def artistVinyls = db.searchVinylByArtist(artist);
			
			artist_vinylCount.put(artist, artistVinyls.size());
			artist_songCount.put(artist, 0);
			
			for(def vinyl : artistVinyls) {
				artist_songCount.put(artist, artist_songCount.get(artist) + vinyl.songs.size());
			}
		}
		
		def topArtist = "";
		def topArtistVinylCount = -1;
		def idOfFirstVinylOfTopArtist = Integer.MAX_VALUE;
		
		for(def artist : artist_vinylCount.keySet()) {
			stuff = db.searchVinylByArtist(artist);
			def idOfFirstVinylOfArtist = idOfFirstVinyl();
			
			if(topArtistVinylCount < artist_vinylCount.get(artist) || ( (topArtistVinylCount == artist_vinylCount.get(artist)) && (idOfFirstVinylOfArtist < idOfFirstVinylOfTopArtist))) {
				topArtist = artist;
				topArtistVinylCount = artist_vinylCount.get(artist);
				idOfFirstVinylOfTopArtist = idOfFirstVinylOfArtist;
			
			} 
		}
		
		result.data.put("Top artist", topArtist);
		result.data.put("Number of vinyls by "+topArtist, artist_vinylCount.get(topArtist));
		result.data.put("Number of songs by "+topArtist, artist_songCount.get(topArtist));
		
		return result;
	}
	
	private idOfFirstVinyl() {
		def minId = Integer.MAX_VALUE;
		
		for(def vinyl : stuff) {
			if(vinyl.id < minId) {
				minId = vinyl.id;
			}
		}
		
		return minId;
	}
	
	public genre() {
		def topGenreVinylCount = -1;
		def idOfFirstVinylOfTopGenre = Integer.MAX_VALUE;
		
		def result = new Report();
		
		def genres = db.getGenres();
		def stuff = "";
		def genreCount = genres.size();
		
		result.data.put("Number of genres", String.valueOf(genreCount));
		
		if(genreCount == 0) {
			return result;
		}
		
		def genre_vinylCount = new HashMap<String, Integer>();
		def genre_songCount = new HashMap<String, Integer>();
		
		for(def genre : genres) {
			def genreVinyls = db.searchVinylByGenre(genre);
			
			genre_vinylCount.put(genre, genreVinyls.size());
			genre_songCount.put(genre, 0);
			
			for(def vinyl : genreVinyls) {
				genre_songCount.put(genre, genre_songCount.get(genre) + vinyl.songs.size());
			}
		}
		
		def topItem = "";
		for(def genre : genre_vinylCount.keySet()) {
			stuff = db.searchVinylByGenre(genre);
			def idOfFirstVinylOfGenre = idOfFirstVinyl();
			
			if(topGenreVinylCount < genre_vinylCount.get(genre) || ( (topGenreVinylCount == genre_vinylCount.get(genre)) && (idOfFirstVinylOfGenre < idOfFirstVinylOfTopGenre))) {
				stuff = genre;
				topGenreVinylCount = genre_vinylCount.get(genre);
				idOfFirstVinylOfTopGenre = idOfFirstVinylOfGenre;
				topItem = stuff;
			} 
		}
		
		result.data.put("Top genre", topItem);
		result.data.put("Number of "+topItem+" vinyls", genre_vinylCount.get(stuff));
		result.data.put("Number of "+topItem+" songs", genre_songCount.get(stuff));
		
		return result;
	}
	
}
