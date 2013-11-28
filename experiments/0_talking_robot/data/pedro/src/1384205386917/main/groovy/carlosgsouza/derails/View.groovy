package carlosgsouza.derails

import groovy.transform.ToString;

@ToString
class View {
	def items
	
	public View(items) {
		this.items = (items instanceof Collection) ? items : [items]
	}
}
