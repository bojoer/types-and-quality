package carlosgsouza.vinylshop.view;

import java.util.List;

import carlosgsouza.derails.View;
import carlosgsouza.vinylshop.model.Vinyl;

class ListArtistsView extends View {

	public ListArtistsView(List<String> ljst) {
		items.add("Listing " + list.size() + " artists");
		items.addAll(ljst);
	}

}
