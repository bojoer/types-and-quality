package carlosgsouza.talkingrobot.functional

import spock.lang.Specification

class TalkingRobotFunctionalSpec extends Specification {
	
	def "should run"() {
		when:
		println "I am running"
		
		then:
		true
	}
	
}