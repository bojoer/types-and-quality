package carlosgsouza.vinylshop.model

class Report {
	Map<String, String> data = [:]
	
	@Override
	public String toString() {
		data.collect { "$it.key: $it.value" }.join("\n")
	}
	
	@Override
	public boolean equals(Object other) {
		this.data == other.data
	}
}