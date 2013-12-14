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
		events																										| duration	| relativeTimes
		["START	100000", "FINISH	200000"]																		| 100		| [:]
		["START	100000", "RUN	105000", "FINISH	200000"]														| 100		| ["105000":5]
		["START	100000", "RUN	105000", "RUN	110000", "FINISH	200000"]										| 100		| ["105000":5, "110000":10]
		["START	100000", "RUN	105000", "PAUSE	110000", "RESUME	115000", "FINISH	200000"]					| 95		| ["105000":5]
		["START	100000", "RUN	105000", "PAUSE	110000", "RESUME	115000", "RUN	120000", "FINISH	200000"]	| 95		| ["105000":5, "120000":15]
		["START	100000", "TEST	105000", "PAUSE	110000", "RESUME	115000", "RUN	120000", "FINISH	200000"]	| 95		| ["105000":5, "120000":15]
	}
	
	def "should determine the relative time and duration for each event even when the events list is inconsistent"() {
		when:
		handler.parse(events)
		
		then:
		handler.relativeTimes == relativeTimes
		handler.duration == duration
		
		where:
		events																										| duration	| relativeTimes
		// no start
		["FINISH	200000"]																						| 0			| [:]
		["RUN	105000", "FINISH	200000"]																		| 95		| ["105000":0]
		// late start
		["RUN	105000", "START	150000", "FINISH	200000"]														| 95		| ["105000":0]
		// events after finish
		["START	100000", "RUN	105000", "RUN	110000", "FINISH	200000", "RUN	205000"]						| 105		| ["105000":5, "110000":10, "205000":105]
		// pause/resume without the other
		["START	100000", "PAUSE	105000", "RUN	110000", "FINISH	200000"]										| 100		| ["110000":10]
		["RESUME	100000", "START	100000", "PAUSE	105000", "RUN	110000", "FINISH	200000"]					| 100		| ["110000":10]
		["START	100000", "RESUME	105000", "RUN	110000", "FINISH	200000"]									| 100		| ["110000":10]
		["START	100000", "PAUSE	105000", "RUN	110000", "RESUME	115000", "FINISH	200000"]					| 100		| ["110000":10]
		["START	100000", "PAUSE	105000", "PAUSE	110000", "RESUME	115000", "RUN	120000", "FINISH	200000"]	| 95		| ["120000":15]
		
	}
	
	def "should rounddown the relative duration"() {
		given:
		def events = ["START	100003", "RUN	105091", "FINISH	200010"]
		
		when:
		handler.parse(events)
		
		then:
		handler.relativeTimes == ["105091":5]
		handler.duration == 100
	}
}
