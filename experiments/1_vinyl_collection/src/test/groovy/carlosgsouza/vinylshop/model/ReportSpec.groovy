package carlosgsouza.vinylshop.model

import spock.lang.Specification
import spock.lang.Unroll

class ReportSpec extends Specification {
	
	@Unroll
	def "should return a string representation of the report where each data field is in one line"(data, string) {
		given:
		def report = new Report(data:data)
		
		expect:
		report.toString() == string
		
		where:
		data						| string
		["name":"value"]			| "name: value"
		["n1":"v1", "n2":"v2"]		| "n1: v1\nn2: v2"
		["n1":"v1", "n2":""]		| "n1: v1\nn2: "
		[:]							| ""
		null						| ""
	}
	
	def "should have a map with empty data as soon as it is created"() {
		when:
		def report = new Report()
		
		then:
		report.data == [:]
	}
}