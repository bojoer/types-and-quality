package carlosgsouza.derails

@SuppressWarnings("unchecked")
class View {
	List<Object> items
	
	public View(Collection items) {
		this.items = items
	}
	
	public View(String items) {
		this.items = [items]
	}
	
	@Override
	public String toString() {
		"View: ${items.toString()}"
	}
}
