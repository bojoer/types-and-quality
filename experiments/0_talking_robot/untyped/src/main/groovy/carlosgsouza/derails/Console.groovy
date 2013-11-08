package carlosgsouza.derails

class Console {

	public render(view) {
		view.items.each {
			println it
		}
	}
	
	public apply(form) {
		println form.title
		form.fieldName.each { name ->
			form.fields[name] = readText(name)
		}
	}
	
	public readText(field) {
		def br = new BufferedReader(new InputStreamReader(System.in))
		print "$field:\t"
		
		br.readLine()
	}
}
