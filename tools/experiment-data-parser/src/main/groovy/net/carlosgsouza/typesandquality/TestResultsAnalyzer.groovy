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
			
			result[subject] = [history:[:], duration:0]
			
			File eventsFile = new File(subjectFolder, "events.txt")
			Events events = new Events(eventsFile)
			
			result[subject].duration = events.duration
			
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
					
					result[subject].history[time] = [v1broken:v1broken, v2passed:v2passed]
				} else {
					result[subject].history[time] = "Compilation Error"
				}
			}
			
		}
		
		def time_tests_charts = [v1:[:], v2:[:]]
		result.each { subject, time_results ->
			
			time_tests_charts.v1[subject] = [:]
			time_tests_charts.v2[subject] = [:]
			
			time_results.history.each { time, results ->
				if(results == "Compilation Error") {
					time_tests_charts.v1[subject][time] = -1
					time_tests_charts.v2[subject][time] = -1
				}
				else {
					time_tests_charts.v1[subject][time] = results.v1broken
					time_tests_charts.v2[subject][time] = results.v2passed
				}
					
			}
		}
		time_tests_charts.each { chartName, subject_data ->
			subject_data.each { subject, data ->
				def chartFile = new File(resultFolder, "chart_${chartName}_${subject}.txt")
				chartFile << "$subject\t$subject" + "\n"
				
				data.each { time, tests ->
					chartFile << "$time\t$tests\n"
				}
			}
		}
		
		def duration_chart = new File(resultFolder, "chart_duration.txt")
		result.each { subject, time_results ->
			duration_chart << "$subject\t${time_results.duration}\n"
		}
		
		
		new File(resultFolder, "result.json") << JsonOutput.prettyPrint(new JsonBuilder(result).toString())
	}
	
	public static void main(args) {
		new TestResultsAnalyzer(args[0]).run()
	}

}
