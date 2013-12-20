package carlosgsouza.vinylshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import carlosgsouza.vinylshop.database.DB;
import carlosgsouza.vinylshop.model.Report;
import carlosgsouza.vinylshop.model.Vinyl;


public class ReportController {
	
	def db = DB.connect();
	
	public artist() {
		def result = new Report();
		
		def artists = db.getArtists();
		def artistCount = artists.size();
		
		result.data.put("Number of artists", artistCount.toString());
		
		def artist_vinylCount = new HashMap<String, Integer>();
		def artist_songCount = new HashMap<String, Integer>();
		
		if(artistCount > 0) {
			for(def artist : artists) {
				def artistVinyls = db.searchVinylByArtist(artist);
				
				artist_vinylCount.put(artist, artistVinyls.size());
				artist_songCount.put(artist, 0);
				
				for(def vinyl : artistVinyls) {
					artist_songCount.put(artist, artist_songCount.get(artist) + vinyl.songs.size());
				}
			}
		} else if(artists != null) {
			if(artist_vinylCount.size() == 0 || artistCount == 0 ) {
				artist_vinylCount = new HashMap<String, Integer>();
				artist_songCount = artist_vinylCount;
				return resut;
			}
		}
		
		def topArtist = "";
		def topArtistVinylCount = -1;
		def idOfFirstVinylOfTopArtist = Integer.MAX_VALUE;
		
		for(def artist : artist_vinylCount.keySet()) {
			def idOfFirstVinylOfArtist = idOfFirstVinyl(db.searchVinylByArtist(artist));
			
			if(topArtistVinylCount < artist_vinylCount.get(artist) || ( (topArtistVinylCount == artist_vinylCount.get(artist)) && (idOfFirstVinylOfArtist < idOfFirstVinylOfTopArtist))) {
				topArtist = artist;
				topArtistVinylCount = artist_vinylCount.get(artist);
				idOfFirstVinylOfTopArtist = idOfFirstVinylOfArtist;
			
			} 
		}
		
		result.data.put("Top artist", topArtist);
		result.data.put("Number of vinyls by "+topArtist, artist_vinylCount.get(topArtist).toString());
		result.data.put("Number of songs by "+topArtist, artist_songCount.get(topArtist).toString());
		
		return result;
	}
	
	private idOfFirstVinyl(vinyls) {
		def minId = Integer.MAX_VALUE;
		
		for(def vinyl : vinyls) {
			if(vinyl.id < minId) {
				minId = vinyl.id;
			}
		}
		
		return minId;
	}
	
	public genre() {
		def result = new Report();
		
		def genres = db.getGenres();
		def genreCount = genres.size();
		
		result.data.put("Number of genres", genreCount.toString());
		
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
		
		def topGenre = "";
		def topGenreVinylCount = -1;
		def idOfFirstVinylOfTopGenre = Integer.MAX_VALUE;
		
		for(def genre : genre_vinylCount.keySet()) {
			def idOfFirstVinylOfGenre = idOfFirstVinyl(db.searchVinylByGenre(genre));
			
			if(topGenreVinylCount < genre_vinylCount.get(genre) || ( (topGenreVinylCount == genre_vinylCount.get(genre)) && (idOfFirstVinylOfGenre < idOfFirstVinylOfTopGenre))) {
				topGenre = genre;
				topGenreVinylCount = genre_vinylCount.get(genre);
				idOfFirstVinylOfTopGenre = idOfFirstVinylOfGenre;
			
			} 
		}
		
		result.data.put("Top genre", topGenre);
		result.data.put("Number of "+topGenre+" vinyls", genre_vinylCount.get(topGenre).toString());
		result.data.put("Number of "+topGenre+" songs", genre_songCount.get(topGenre).toString());
		
		return result;
	}
	
}
