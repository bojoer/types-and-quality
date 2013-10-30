package carlosgsouza.vinylshop.view

import java.util.List

import carlosgsouza.vinylshop.model.Vinyl

public class VinylView {
	
	public void renderList(List<Vinyl> vinylList) {
		vinylList.each {
			println it
		}
	}
}
