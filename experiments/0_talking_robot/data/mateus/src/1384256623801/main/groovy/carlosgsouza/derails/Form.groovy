package carlosgsouza.derails;

class Form {
	
	public String title
	public Map<String, String> fields = [:]
	public Collection<String> fieldName = []
	
	public Form(String title, String... fieldName) {
		this.title = title
		this.fieldName = fieldName
	}
}
