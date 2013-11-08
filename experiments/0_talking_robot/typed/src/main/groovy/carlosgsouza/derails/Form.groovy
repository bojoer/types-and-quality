package carlosgsouza.derails;

class Form {
	
	String title
	Map<String, String> fields = [:]
	Collection<String> fieldName = []
	
	public Form(String title, String... fieldName) {
		this.title = title
		this.fieldName = fieldName
	}
}
