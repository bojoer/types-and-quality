package carlosgsouza.vinylshop.model

class Summary {
	int vinylCount
	int artistCount
	int songCount
	int genreCount
	
	@Override
	public String toString() {
		"Vinyls:  $vinylCount\n" +
		"Artists: $artistCount\n" +
		"Songs:   $songCount\n" +
		"Genres:  $genreCount"
	}
	

	@Override
	public boolean equals(Object obj) {
		Summary other = (Summary) obj
		if (artistCount != other.artistCount)
			return false
		if (genreCount != other.genreCount)
			return false
		if (songCount != other.songCount)
			return false
		if (vinylCount != other.vinylCount)
			return false
		return true
	}
	
	
}