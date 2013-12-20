package carlosgsouza.vinylshop.model;

public class Summary {
	public vinylCount;
	public artistCount;
	public songCount;
	public genreCount;
	
	@Override
	public String toString() {
		"Vinyls:  " + vinylCount +
			"\nArtists: " + artistCount +
			"\nSongs:   " + songCount +
			"\nGenres:  " + genreCount;
	}
	

	@Override
	public boolean equals(Object obj) {
		return this.toString() == obj?.toString()
	}
}