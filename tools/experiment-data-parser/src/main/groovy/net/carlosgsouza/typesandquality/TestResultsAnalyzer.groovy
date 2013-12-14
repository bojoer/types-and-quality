package net.carlosgsouza.typesandquality

import groovy.json.JsonBuilder
import groovy.json.JsonOutput

class TestResultsAnalyzer {

	public TestResultsAnalyzer() {
		
	}
	
	def run(analysisFolderPath) {
		File analysisFolder = new File(analysisFolderPath)
		File dataFolder = new File(analysisFolder, "data")
		File resultFolder = new File(analysisFolder, "result")
		
		if(resultFolder.exists()) {
			resultFolder.deleteDir()
		}
		resultFolder.mkdirs()
		
		def result = [:]
		
		dataFolder.eachDir { subjectFolder ->
			def subject = subjectFolder.name
			
			result[subject] = [:]
			
			File eventsFile = new File(subjectFolder, "events.txt")
			Events events = new Events(eventsFile)
			
			events.relativeTimes.each { timestamp, time ->
				def testResultsFolder = new File(subjectFolder, "snapshots/$timestamp/build/test-results")
				
				if(testResultsFolder.exists()) {
					def v1broken = 0
					def v2passed = 0
					
					testResultsFolder.eachFile { testResultsFile ->
						if(!testResultsFile.name.endsWith("xml")) {
							return
						}
						
						def testResults = new TestResultsFileParser().parse(testResultsFile)
						  
						if(testResultsFile.name.contains("v1")) {
							v1broken += testResults.failed	
						} else {
							v2passed += testResults.passed
						}
					}
					
					result[subject][time] = [v1broken:v1broken, v2passed:v2passed]
					
				} else {
					result[subject][time] = "Compilation Error"
				}
			}
			
		}
		
		def charts = [v1:[:], v2:[:]]
		result.each { subject, time_results ->
			
			charts.v1[subject] = [:]
			charts.v2[subject] = [:]
			
			time_results.each { time, results ->
				if(results == "Compilation Error") {
					charts.v1[subject][time] = -1
					charts.v2[subject][time] = -1
				}
				else {
					charts.v1[subject][time] = results.v1broken
					charts.v2[subject][time] = results.v2passed
				}
					
			}
		}
		charts.each { chartName, subject_data ->
			subject_data.each { subject, data ->
				def chartFile = new File(resultFolder, "chart_${chartName}_${subject}.txt")
				chartFile << subject + "\n"
				
				data.each { time, tests ->
					chartFile << "$time\t$tests\n"
				}
			}
			
		}
		
		new File(resultFolder, "result.json") << JsonOutput.prettyPrint(new JsonBuilder(result).toString())
	}
	
	public static void main(args) {
		new TestResultsAnalyzer(args[0]).run()
	}

}
