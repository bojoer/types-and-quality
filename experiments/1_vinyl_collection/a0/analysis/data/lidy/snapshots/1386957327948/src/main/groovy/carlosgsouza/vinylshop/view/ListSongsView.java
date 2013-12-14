package carlosgsouza.vinylshop.view;

import java.util.List;

import carlosgsouza.derails.View;
import carlosgsouza.vinylshop.model.Vinyl;

class ListSongsView extends View {

	public ListSongsView(List<String> list) {
		items.add("Listing " + list.size() + " songs");
		items.addAll(list);
	}

}
