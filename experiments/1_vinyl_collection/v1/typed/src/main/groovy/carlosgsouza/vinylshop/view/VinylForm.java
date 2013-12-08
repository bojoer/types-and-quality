package carlosgsouza.vinylshop.view;

import carlosgsouza.derails.Form;

public class VinylForm extends Form {
	
	public VinylForm() {
		this.title = "Please enter the vinyl details below";
		
		this.fieldName.add("Artist");
		this.fieldName.add("Title");
		this.fieldName.add("Songs");
		this.fieldName.add("Year");
		this.fieldName.add("Genre");
	}
}
