package carlosgsouza.vinylshop.model

import spock.lang.Specification
import spock.lang.Unroll

class SummarySpec extends Specification {
	
	def "should return a beautiful representation of the object"() {
		when:
		def summary = new Summary(vinylCount:10, artistCount:5, songCount:23, genreCount:6)
		
		then:
		summary.toString() ==	"Vinyls:  $summary.vinylCount\n" +
								"Artists: $summary.artistCount\n" +
								"Songs:   $summary.songCount\n" +
								"Genres:  $summary.genreCount"
	}
}