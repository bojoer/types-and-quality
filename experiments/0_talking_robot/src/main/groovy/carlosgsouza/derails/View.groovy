package carlosgsouza.derails

class View {
	List<Object> items
	
	public View(items) {
		this.items = (items instanceof Collection) ? items : [items]
	}
}
