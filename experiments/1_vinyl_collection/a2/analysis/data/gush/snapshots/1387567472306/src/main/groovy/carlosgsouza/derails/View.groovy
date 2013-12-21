package carlosgsouza.derails

class View {
	public List<Object> items
	
	public View() {
		items = []
	}
	
	public View(items) {
		this.items = (items instanceof Collection) ? items : [items]
	}
	
	@Override
	public String toString() {
		"View: ${items.toString()}"
	}
}
