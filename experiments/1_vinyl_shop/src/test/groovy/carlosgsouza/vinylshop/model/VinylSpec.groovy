package carlosgsouza.vinylshop.model

import spock.lang.Specification

class VinylSpec extends Specification {
	
	def "should validate a valid vinyl"() {
		given:
		def vinyl = new Vinyl(id:1, artist:"A", title:"A", songs:["A1", "A2", "A3"], year:"A", genre:"A")
		
		when:
		def valid = vinyl.valid
		
		then:
		valid == true
	}
	
	def "should not validate an invalid vinyl"(artist, title, songs, year, genre) {
		given:
		def vinyl = new Vinyl(artist:artist, title:title, songs:songs, year:year, genre:genre)
		
		when:
		def valid = vinyl.valid
		
		then:
		valid == false
		
		where:
		artist	| title	| songs			| year	| genre
		null	| null	| null			| null	| null
		""		| "t"	| ["s"]			| "y"	| "g"
		null	| "t"	| ["s"]			| "y"	| "g"
		"a"		| ""	| ["s"]			| "y"	| "g"
		"a"		| null	| ["s"]			| "y"	| "g"
		"a"		| "t"	| [""]			| "y"	| "g"
		"a"		| "t"	| []			| "y"	| "g"
		"a"		| "t"	| null			| "y"	| "g"
		"a"		| "t"	| ["", null]	| "y"	| "g"
		"a"		| "t"	| ["s"]			| ""	| "g"
		"a"		| "t"	| ["s"]			| null	| "g"
		"a"		| "t"	| ["s"]			| "y"	| ""
		"a"		| "t"	| ["s"]			| "y"	| null
		""		| null	| []			| "y"	| "g"
	}
	
}