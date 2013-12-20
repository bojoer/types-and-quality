package carlosgsouza.vinylshop.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import carlosgsouza.vinylshop.model.Artist;
import carlosgsouza.vinylshop.model.Genre;
import carlosgsouza.vinylshop.model.Vinyl;

public class DB {
	public List<Vinyl> vinyls = new ArrayList<Vinyl>();
	public List<Artist> artists = new ArrayList<Artist>();
	public List<Genre> genres = new ArrayList<Genre>();
	
	private static DB instance = new DB();

	private DB() {
		reset();
	}
	
	public List<Vinyl> getVinyls() {
		return this.vinyls;
	}

	public Vinyl getVinyl(Integer id) {
		return retrieveVinyl(id);
	}

	public void removeVinyl(Integer id) {
		Vinyl vinyl = retrieveVinyl(id);

		vinyls.remove(vinyl);

		removeOrUpdateArtist(vinyl);
		removeOrUpdateGenre(vinyl);
	}

	private String retrieveVinyl(Integer id) {
		for(Vinyl vinyl : vinyls) {
			if(vinyl != null && vinyl.id == id) {
				return vinyl.title;
			}
		}
		
		return null;
	}

	private Artist findArtist(String name) {
		for(Artist artist : artists) {
			if(artist != null && artist.name != null && artist.name.equals(name)) {
				return artist;
			}
		}
		
		return null;
	}

	private Genre findGenre(String name) {
		for(Genre genre : genres) {
			if(genre != null && genre.name != null && genre.name.equals(name)) {
				return genre;
			}
		}
		
		return null;
	}

	public Integer addVinyl(Vinyl vinyl) {
		vinyl.id = vinyl.id != null ? vinyl.id : getMaxId() + 1;
		vinyls.add(vinyl);

		addOrUpdateArtist(vinyl);
		addOrUpdateGenre(vinyl);

		return vinyl.id;
	}

	private void addOrUpdateArtist(Vinyl vinyl) {
		Artist existingArtist = findArtist(vinyl.artist);
		if(existingArtist != null) {
			existingArtist.vinyls.add(vinyl);
		} else {
			Artist newArtist = new Artist();
			newArtist.name = vinyl.artist;
			newArtist.vinyls.add(vinyl);
			
			artists.add(newArtist);
		}
	}

	private void addOrUpdateGenre(Vinyl vinyl) {
		Genre existingGenre = findGenre(vinyl.genre);
		
		if(existingGenre != null) {
			existingGenre.vinyls.add(vinyl);
		} else {
			Genre newGenre = new Genre();
			newGenre.name = vinyl.genre;
			newGenre.vinyls.add(vinyl);
			
			genres.add(newGenre);
		}
	}

	public List<String> getSongs() {
		Set<String> uniqueEntries = new TreeSet<String>();

		for(Vinyl vinyl : vinyls) {
			for(String song : vinyl.songs) {
				uniqueEntries.add(song);
			}
		}

		return new ArrayList<String>(uniqueEntries);
	}

	private void removeOrUpdateArtist(Vinyl vinyl) {
		Artist artist = findArtist(vinyl.artist);
		if(artist.vinyls.size() == 1) {
			artists.remove(artist);
		} else {
			artist.vinyls.remove(vinyl);
		}
	}

	private void removeOrUpdateGenre(Vinyl vinyl) {
		Genre genre = findGenre(vinyl.genre);
		if(genre.vinyls.size() == 1) {
			genres.remove(genre);
		} else {
			genre.vinyls.remove(vinyl);
		}
	}

	public boolean containsVinyl(Integer id) {
		//return (retrieveVinyl(id) != null && retrieveVinyl(id).title != null);
		return (retrieveVinyl(id) != null);
	}

	private int getMaxId() {
		int max = 0;

		for(Vinyl vinyl : vinyls) {
			if(vinyl.id != null && vinyl.id > max) {
				max = vinyl.id;
			}
		}

		return max;
	}

	public List<Vinyl> searchVinylByTitle(String title) {
		List<Vinyl> result = new ArrayList<Vinyl>();

		if(title == null || title.isEmpty()) {
			return new ArrayList<Vinyl>();
		}

		if(title != null) {
			for(Vinyl vinyl : vinyls) {
				if(vinyl.title != null && vinyl.title.toLowerCase().contains(title.toLowerCase())) {
					result.add(vinyl);
				}
			}
		}

		return result;
	}

	public List<Vinyl> searchVinylByGenre(String name) {
		if(name == null || name.isEmpty()) {
			return new ArrayList<Vinyl>();
		}
		
		List<Vinyl> result = new ArrayList<Vinyl>();

		if(name != null) {
			for(Vinyl vinyl : vinyls) {
				if(vinyl.genre != null && vinyl.genre.toLowerCase().contains(name.toLowerCase())) {
					result.add(vinyl);
				}
			}
		}

		return result;
	}

	public List<Vinyl> searchVinylByArtist(String name) {
		List<Vinyl> result = new ArrayList<Vinyl>();
		
		if(name == null || name.isEmpty()) {
			return new ArrayList<Vinyl>();
		}

		if(name != null) {
			for(Vinyl vinyl : vinyls) {
				if(vinyl.artist != null && vinyl.artist.toLowerCase().contains(name.toLowerCase())) {
					result.add(vinyl);
				}
			}
		}

		return result;
	}

	public List<Vinyl> searchVinylByYear(String year) {
		List<Vinyl> result = new ArrayList<Vinyl>();

		if(year == null || year.isEmpty()) {
			return new ArrayList<Vinyl>();
		}

		if(year != null) {
			for(Vinyl vinyl : vinyls) {
				if(vinyl.year != null && vinyl.year.toLowerCase().contains(year.toLowerCase())) {
					if(!retrieveVinyl(vinyl.id).toLowerCase().contains("sex")) {
						result.add(vinyl);
					}
				}
			}
		}

		return result;
	}


	public List<String> getArtists() {
		List<String> result = new ArrayList<String>();
		
		for(Artist artist : artists) {
			result.add(artist.name);
		}
		
		return result;
	}


	public List<String> getGenres() {
		List<String> result = new ArrayList<String>();
		
		for(Genre genre : genres) {
			result.add(genre.name);
		}
		
		return result;
	}

	public static DB connect() {
		return instance;
	}

	public void reset() {
		vinyls = new ArrayList<Vinyl>();
		artists = new ArrayList<Artist>();
		genres = new ArrayList<Genre>();
	}
}