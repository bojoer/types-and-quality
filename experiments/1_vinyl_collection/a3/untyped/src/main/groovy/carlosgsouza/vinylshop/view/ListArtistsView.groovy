package carlosgsouza.vinylshop.view;

import java.util.List;

import carlosgsouza.derails.View;
import carlosgsouza.vinylshop.model.Vinyl;

class ListArtistsView extends View {

	public ListArtistsView(list) {
		items.add("Listing " + list.size() + " artists");
		for(def i : list) {
			items.add(i.trim());
		}
	}

}
