package carlosgsouza.vinylshop.model;

public class Summary {
	public int vinylCount;
	public int artistCount;
	public int songCount;
	public int genreCount;
	
	@Override
	public String toString() {
		"Vinyls:  " + vinylCount +
			"\nArtists: " + artistCount +
			"\nSongs:   " + songCount +
			"\nGenres:  " + genreCount;
	}
	

	@Override
	public boolean equals(Object obj) {
		def other = obj;
		if (artistCount != other.artistCount)
			return false;
		if (genreCount != other.genreCount)
			return false;
		if (songCount != other.songCount)
			return false;
		if (vinylCount != other.vinylCount)
			return false;
		return true;
	}
}