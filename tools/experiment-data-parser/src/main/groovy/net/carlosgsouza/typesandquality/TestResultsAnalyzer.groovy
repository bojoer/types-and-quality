package net.carlosgsouza.typesandquality

import groovy.io.FileType;

class TestResultsAnalyzer {

	public TestResultsAnalyzer() {
		
	}
	
	def run(analysisFolderPath) {
		File analysisFolder = new File(analysisFolderPath)
		File dataFolder = new File(analysisFolder, "data")
		File resultFolder = new File(analysisFolder, "result")
		
		if(resultFolder.exists()) {
			resultFolder.delete()
		}
		
		dataFolder.eachDir { subjectFolder ->
			File eventsFile = new File(subjectFolder, "events.txt")
		
			EventsHandler eventsHandler = new EventsHandler()
			eventsHandler.parse(eventsFile)
			
			println eventsHandler.duration
		}
	}
	
	public static void main(args) {
		new TestResultsAnalyzer(args[0]).run()
	}

}
