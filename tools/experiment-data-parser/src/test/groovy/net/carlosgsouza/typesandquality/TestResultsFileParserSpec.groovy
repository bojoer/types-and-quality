package net.carlosgsouza.typesandquality

import spock.lang.Specification

class TestResultsFileParserSpec extends Specification {

	TestResultsFileParser parser 

	def setup() {
		parser = new TestResultsFileParser()
	}

	def "should extract the number of passed and failed tests from an xml"() {
		given:
		def testResultXml = """
<testsuite name="carlosgsouza.vinylshop.functional.v1.GenreFunctionalSpec" tests="6" failures="0" errors="0" timestamp="2013-12-14T16:22:54" hostname="localhost" time="0.575">
  <properties/>
  <testcase name="should search for vinyls given the genre" classname="carlosgsouza.vinylshop.functional.v1.GenreFunctionalSpec" time="0.535"/>
  <testcase name="should ignore the case when searching for vinyls given the genre" classname="carlosgsouza.vinylshop.functional.v1.GenreFunctionalSpec" time="0.004"/>
  <testcase name="should match partially when searching for vinyls given the genre" classname="carlosgsouza.vinylshop.functional.v1.GenreFunctionalSpec" time="0.005"/>
  <testcase name="should show no results if the genre is not provided for the genre search" classname="carlosgsouza.vinylshop.functional.v1.GenreFunctionalSpec" time="0.005"/>
  <testcase name="should show no results if there are no vinyls with the given genre" classname="carlosgsouza.vinylshop.functional.v1.GenreFunctionalSpec" time="0.013"/>
  <testcase name="should list all genres" classname="carlosgsouza.vinylshop.functional.v1.GenreFunctionalSpec" time="0.01"/>
  <system-out><![CDATA[]]></system-out>
  <system-err><![CDATA[]]></system-err>
</testsuite>
"""
		when:
		def result = parser.parse(testResultXml)
		
		then:
		result.passed == 6
		result.failed == 0
	}
	
	def "should calculate the number of passed and failed tests based on the values of the attributes tests, failures and errors"() {
		when:
		def result = parser.parse(xml)
		
		then:
		result.passed == passed
		result.failed == failed
		
		where:
		passed	| failed	| xml
		0		| 0			| '<testsuite tests="0" failures="0" errors="0" />'
		6		| 0			| '<testsuite tests="6" failures="0" errors="0" />'
		0		| 6			| '<testsuite tests="6" failures="6" errors="0" />'
		0		| 6			| '<testsuite tests="6" failures="0" errors="6" />'
		4		| 2			| '<testsuite tests="6" failures="2" errors="0" />'
		4		| 2			| '<testsuite tests="6" failures="0" errors="2" />'
		1		| 5			| '<testsuite tests="6" failures="2" errors="3" />'
	}
}
