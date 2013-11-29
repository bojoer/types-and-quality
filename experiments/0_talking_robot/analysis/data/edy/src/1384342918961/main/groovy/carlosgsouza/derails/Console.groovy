package carlosgsouza.derails

class Console {

	public render(View view) {
		view.items.each {
			println it
		}
	}
	
	public apply(Form form) {
		println form.title
		form.fieldName.each { name ->
			form.fields[name] = readText(name)
		}
	}
	
	public String readText(field) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
		print "$field:\t"
		
		br.readLine()
	}
}
