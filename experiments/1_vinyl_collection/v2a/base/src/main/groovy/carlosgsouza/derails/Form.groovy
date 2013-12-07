package carlosgsouza.derails;

import groovy.transform.ToString;

@ToString
class Form {
	
	String title
	Map<String, String> fields = [:]
	Collection<String> fieldName = []
}
