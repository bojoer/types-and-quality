package carlosgsouza.derails;

import groovy.transform.ToString;

@ToString
class Form {
	
	def title
	def fields = [:]
	def fieldName = []
	
	public Form(title, ... fieldName) {
		this.title = title
		this.fieldName = fieldName
	}
}
