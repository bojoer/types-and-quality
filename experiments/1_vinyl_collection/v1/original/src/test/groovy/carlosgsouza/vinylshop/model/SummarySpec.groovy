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
		expect:
		equals == summary1.equals(summary2)
		
		where:
		equals	| summary1																| summary2
		true	| new Summary(vinylCount:7, artistCount: 6, songCount:1, genreCount:4)	| new Summary(vinylCount:7, artistCount: 6, songCount:1, genreCount:4)
		false	| new Summary(vinylCount:0, artistCount: 6, songCount:1, genreCount:4)	| new Summary(vinylCount:7, artistCount: 6, songCount:1, genreCount:4)
		false	| new Summary(vinylCount:7, artistCount: 0, songCount:1, genreCount:4)	| new Summary(vinylCount:7, artistCount: 6, songCount:1, genreCount:4)
		false	| new Summary(vinylCount:7, artistCount: 6, songCount:0, genreCount:4)	| new Summary(vinylCount:7, artistCount: 6, songCount:1, genreCount:4)
		false	| new Summary(vinylCount:7, artistCount: 6, songCount:1, genreCount:0)	| new Summary(vinylCount:7, artistCount: 6, songCount:1, genreCount:4)
		false	| new Summary(vinylCount:0, artistCount: 0, songCount:0, genreCount:0)	| new Summary(vinylCount:7, artistCount: 6, songCount:1, genreCount:4)
	}
}