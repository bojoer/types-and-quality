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
	
	def "should consider two summary to be equal if their fields have the same values"() {
		when:
		def summary1 = new Summary(vinylCount:7, artistCount: 6, songCount:18, genreCount:4)
		def summary2 = new Summary(vinylCount:7, artistCount: 6, songCount:18, genreCount:4)
		
		then:
		summary1 == summary2
	}
}