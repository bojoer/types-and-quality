package net.carlosgsouza.typesandquality

class EventsHandler {
	
	def duration = 0
	def relativeTimes = [:]

	public TestResultsFileParser() {
		
	}
	
	def parse(File eventsFile) {
		parse(eventsFile.readLines())
	}
	
	def parse(List events) {
		if(events.size() == 0) {
			return
		}
		
		def time_event = []
		events.each {
			def s = it.split("\t")
			time_event << [event:s[0], time:s[1].toLong()] 
		}
		
		def start = time_event[0].time
		def pausedTime = 0
		
		time_event.size().times { n ->
			def time_n = time_event[n].time
			def event_n = time_event[n].event
			
			if(event_n == "RUN" || event_n == "TEST") {
				relativeTimes[time_n.toString()] = (long)(time_n - start - pausedTime)/1000L
			} else if(n > 0 && event_n == "RESUME" && time_event[n-1].event == "PAUSE") {
				pausedTime += time_n - time_event[n-1].time
			}
		}
		
		def finish = time_event.last()?.time  
		duration = (long)(finish - start - pausedTime)/1000L
	}

}
