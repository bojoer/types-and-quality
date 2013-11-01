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
}