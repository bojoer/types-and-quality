package carlosgsouza.vinylshop.model

class Report {
	Map<String, String> data = [:]
	
	@Override
	public String toString() {
		data.collect { "$it.key: $it.value" }.join("\n")
	}
}