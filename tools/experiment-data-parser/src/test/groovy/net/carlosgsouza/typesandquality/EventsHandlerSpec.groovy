package net.carlosgsouza.typesandquality

import spock.lang.Specification
import spock.lang.Unroll

class EventsHandlerSpec extends Specification {

	EventsHandler handler
	
	def setup() {
		handler = new EventsHandler()
	}
	
	def "should determine the relative time and duration for each event"() {
		when:
		handler.parse(events)
		
		then:
		handler.relativeTimes == relativeTimes
		handler.duration == duration
		
		where:
		events																							| duration	| relativeTimes
		["START	100", "FINISH	200"]																	| 100		| [:]
		["START	100", "RUN	105", "FINISH	200"]														| 100		| ["105":5]
		["START	100", "RUN	105", "RUN	110", "FINISH	200"]											| 100		| ["105":5, "110":10]
		["START	100", "RUN	105", "PAUSE	110", "RESUME	115", "FINISH	200"]						| 95		| ["105":5]
		["START	100", "RUN	105", "PAUSE	110", "RESUME	115", "RUN	120", "FINISH	200"]			| 95		| ["105":5, "120":15]
		["START	100", "TEST	105", "PAUSE	110", "RESUME	115", "RUN	120", "FINISH	200"]			| 95		| ["105":5, "120":15]
	}
	
	def "should determine the relative time and duration for each event even when the events list is inconsistent"() {
		when:
		handler.parse(events)
		
		then:
		handler.relativeTimes == relativeTimes
		handler.duration == duration
		
		where:
		events																							| duration	| relativeTimes
		// no start
		["FINISH	200"]																				| 0			| [:]
		["RUN	105", "FINISH	200"]																	| 95		| ["105":0]
		// late start
		["RUN	105", "START	150", "FINISH	200"]													| 95		| ["105":0]
		// events after finish
		["START	100", "RUN	105", "RUN	110", "FINISH	200", "RUN	205"]								| 105		| ["105":5, "110":10, "205":105]
		// pause/resume without the other
		["START	100", "PAUSE	105", "RUN	110", "FINISH	200"]										| 100		| ["110":10]
		["RESUME	100", "START	100", "PAUSE	105", "RUN	110", "FINISH	200"]					| 100		| ["110":10]
		["START	100", "RESUME	105", "RUN	110", "FINISH	200"]										| 100		| ["110":10]
		["START	100", "PAUSE	105", "RUN	110", "RESUME	115", "FINISH	200"]						| 100		| ["110":10]
		["START	100", "PAUSE	105", "PAUSE	110", "RESUME	115", "RUN	120", "FINISH	200"]		| 95		| ["120":15]
		
	}
}
