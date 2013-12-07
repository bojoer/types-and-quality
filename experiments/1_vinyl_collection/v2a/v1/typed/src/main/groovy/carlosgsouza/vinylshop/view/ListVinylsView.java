package carlosgsouza.vinylshop.view;

import java.util.List;

import carlosgsouza.derails.View;
import carlosgsouza.vinylshop.model.Vinyl;

class ListVinylsView extends View {

	public ListVinylsView(List<Vinyl> vinylList) {
		items.add("Listing $vinylList.size items");
		items.addAll(vinylList);
	}

}
