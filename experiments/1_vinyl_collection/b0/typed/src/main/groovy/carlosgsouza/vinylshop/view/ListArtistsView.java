package carlosgsouza.vinylshop.view;

import java.util.List;

import carlosgsouza.derails.View;

class ListArtistsView extends View {

	public ListArtistsView(List<String> list) {
		items.add("Listing " + list.size() + " artists");
		items.addAll(list);
	}

}
