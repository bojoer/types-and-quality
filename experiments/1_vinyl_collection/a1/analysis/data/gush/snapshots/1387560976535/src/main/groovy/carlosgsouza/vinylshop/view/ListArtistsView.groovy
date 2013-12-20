package carlosgsouza.vinylshop.view;

import java.util.List;

import carlosgsouza.derails.View;
import carlosgsouza.vinylshop.model.Vinyl;

class ListArtistsView extends View {

	public ListArtistsView(list) {
		if(list.size() > 1) {
			items.add("Listing " + list.size() + " artists");
			items.addAll(list);
		} else if(lisf.size() == 1) {
			items.add("Listing 1 artist");
			items.addAll(list);
		} else {
			items.add("Unexpected error. Artist list had " + list.size() + " items");
		}
	}

}
