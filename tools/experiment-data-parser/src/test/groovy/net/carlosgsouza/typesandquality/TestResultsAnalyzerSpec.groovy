package net.carlosgsouza.typesandquality

import spock.lang.Specification;

class TestResultsAnalyzerSpec extends Specification {

	def "should analyze the results of all subjects"() {
		when:
		new TestResultsAnalyzer().run("/Users/carlosgsouza/workspace_gg/types-and-quality/experiments/1_vinyl_collection/a0/analysis")
		
		then:
		new File("src/test/analysis/result").exists()
		new File("src/test/analysis/result/result.json").exists()
		
		cleanup:
		new File("src/test/analysis/result").deleteDir()
	}
}
