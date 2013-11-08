package carlosgsouza.vinylshop.functional

import spock.lang.Specification

class VinylFunctionalSpec extends Specification {
	
	def "should run"() {
		when:
		println "I am running"
		
		then:
		true
	}
	
}