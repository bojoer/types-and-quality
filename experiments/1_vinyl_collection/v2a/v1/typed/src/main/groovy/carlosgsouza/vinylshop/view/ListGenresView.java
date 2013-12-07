package carlosgsouza.vinylshop.view;

import java.util.List;

import carlosgsouza.derails.View;
import carlosgsouza.vinylshop.model.Vinyl;

class ListGenresView extends View {

	public ListGenresView(List<String> list) {
		items.add("Listing $list.size genres");
		items.addAll(list);
	}

}
