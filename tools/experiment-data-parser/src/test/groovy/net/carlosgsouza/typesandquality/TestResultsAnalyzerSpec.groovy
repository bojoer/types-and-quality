package net.carlosgsouza.typesandquality

import spock.lang.Specification;

class TestResultsAnalyzerSpec extends Specification {

	def "should analyze the results of all subjects"() {
		when:
		new TestResultsAnalyzer().run("src/test/analysis")
		
		then:
		true
	}
}
