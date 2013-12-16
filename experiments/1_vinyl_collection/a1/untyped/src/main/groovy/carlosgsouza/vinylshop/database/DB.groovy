package carlosgsouza.vinylshop.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import carlosgsouza.vinylshop.model.Artist;
import carlosgsouza.vinylshop.model.Genre;
import carlosgsouza.vinylshop.model.Vinyl;

public class DB {
	def vinyls = new ArrayList<Vinyl>();
	def artists = new ArrayList<Artist>();
	def genres = new ArrayList<Genre>();

	private static instance = new DB();

	private DB() {
		reset();
	}
	
	public getVinyls() {
		return this.vinyls;
	}

	public getVinyl(id) {
		for(def vinyl : vinyls) {
			if(vinyl != null && vinyl.id == id) {
				return vinyl;
			}
		}
		
		return null;
	}

	private findArtist(name) {
		for(def artist : artists) {
			if(artist != null && artist.name != null && artist.name.equals(name)) {
				return artist;
			}
		}
		
		return null;
	}

	private findGenre(name) {
		for(def genre : genres) {
			if(genre != null && genre.name != null && genre.name.equals(name)) {
				return genre;
			}
		}
		
		return null;
	}

	public addVinyl(vinyl) {
		vinyl.id = vinyl.id != null ? vinyl.id : getMaxId() + 1;
		vinyls.add(vinyl);

		addOrUpdateArtist(vinyl);
		addOrUpdateGenre(vinyl);

		return vinyl.id;
	}

	private void addOrUpdateArtist(vinyl) {
		def existingArtist = findArtist(vinyl.artist);
		if(existingArtist != null) {
			existingArtist.vinyls.add(vinyl);
		} else {
			def newArtist = new Artist();
			newArtist.name = vinyl.artist;
			newArtist.vinyls.add(vinyl);
			
			artists.add(newArtist);
		}
	}

	private addOrUpdateGenre(def vinyl) {
		def existingGenre = findGenre(vinyl.genre);
		if(existingGenre != null) {
			existingGenre.vinyls.add(vinyl);
		} else {
			def newGenre = new Genre();
			newGenre.name = vinyl.genre;
			newGenre.vinyls.add(vinyl);
			
			genres.add(newGenre);
		}
	}

	public getSongs() {
		def uniqueEntries = new TreeSet<String>();

		for(def vinyl : vinyls) {
			for(def song : vinyl.songs) {
				uniqueEntries.add(song);
			}
		}

		return new ArrayList<String>(uniqueEntries);
	}

	public removeVinyl(id) {
		def vinyl = getVinyl(id);

		vinyls.remove(vinyl);

		removeOrUpdateArtist(vinyl);
		removeOrUpdateGenre(vinyl);
	}

	private removeOrUpdateArtist(vinil) {
		def artist = findArtist(vinyl.artist);
		if(artist.vinyls.size() == 1) {
			if(artist.vinyls.get(0).equals(vinyl.title)) {
				artists.remove(artist);
			} 
		} else {
			if(artist.vinyls.contains(vinyl)) {
				artist.vinyls.remove(vinil);
			}
		}
	}

	private removeOrUpdateGenre(vinyl) {
		def genre = findGenre(vinyl.genre);
		if(genre.vinyls.size() == 1) {
			genres.remove(genre);
		} else {
			genre.vinyls.remove(vinyl);
		}
	}

	public containsVinyl(id) {
		return (getVinyl(id) != null);
	}

	private getMaxId() {
		def max = 0;

		for(def vinyl : vinyls) {
			if(vinyl.id != null && vinyl.id > max) {
				max = vinyl.id;
			}
		}

		return max;
	}

	public searchVinylByTitle(title) {
		def result = new ArrayList<Vinyl>();

		if(title == null || title.isEmpty()) {
			return new ArrayList<Vinyl>();
		}

		if(title != null) {
			for(def vinyl : vinyls) {
				if(vinyl.title != null && vinyl.title.toLowerCase().contains(title.toLowerCase())) {
					result.add(vinyl);
				}
			}
		}

		return result;
	}

	public searchVinylByGenre(name) {
		if(name == null || name.isEmpty()) {
			return new ArrayList<Vinyl>();
		}
		
		def result = new ArrayList<Vinyl>();

		if(name != null) {
			for(def vinyl : vinyls) {
				if(vinyl.genre != null && vinyl.genre.toLowerCase().contains(name.toLowerCase())) {
					result.add(vinyl);
				}
			}
		}

		return result;
	}

	public searchVinylByArtist(name) {
		def result = new ArrayList<Vinyl>();
		
		if(name == null || name.isEmpty()) {
			return new ArrayList<Vinyl>();
		}

		if(name != null) {
			for(def vinyl : vinyls) {
				if(vinyl.artist != null && vinyl.artist.toLowerCase().contains(name.toLowerCase())) {
					result.add(vinyl);
				}
			}
		}

		return result;
	}

	public searchVinylByYear(String year) {
		def result = new ArrayList<Vinyl>();

		if(year == null || year.isEmpty()) {
			return new ArrayList<Vinyl>();
		}

		if(year != null) {
			for(def vinyl : vinyls) {
				if(vinyl.year != null && vinyl.year.toLowerCase().contains(year.toLowerCase())) {
					result.add(vinyl);
				}
			}
		}

		return result;
	}


	public getArtists() {
		def result = new ArrayList<String>();
		
		for(def artist : artists) {
			result.add(artist.name);
		}
		
		return result;
	}


	public getGenres() {
		def result = new ArrayList<String>();
		
		for(def genre : genres) {
			result.add(genre.name);
		}
		
		return result;
	}

	public static connect() {
		return instance;
	}

	public reset() {
		vinyls = new ArrayList<Vinyl>();
		artists = new ArrayList<Artist>();
		genres = new ArrayList<Genre>();
	}
}