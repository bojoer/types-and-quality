package net.carlosgsouza.typesandquality

class TestResultsFileParser {

	public TestResultsFileParser() {
		
	}
	
	def parse(File resultFile) {
		parse(resultFile.text)
	}
	
	def parse(String result) {
		def xml = new XmlParser().parseText(result)
		
		def tests = xml.@tests[0].toInteger()
		def failures = xml.@failures[0].toInteger()
		def errors = xml.@errors[0].toInteger()
		
		return [passed:tests-failures-errors, failed:failures+errors]
	}

}
