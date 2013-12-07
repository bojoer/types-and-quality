package carlosgsouza.vinylshop.view

import carlosgsouza.derails.Form
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.model.Report
import carlosgsouza.vinylshop.model.Summary
import carlosgsouza.vinylshop.model.Vinyl



public class VinylForm extends Form {
	
	public VinylForm() {
		title = "Please enter the vinyl details below";
		fieldName = ["Artist", "Title", "Songs", "Year", "Genre"]
	}
}
