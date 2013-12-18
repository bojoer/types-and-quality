package carlosgsouza.vinylshop.view;

import java.util.List;

import carlosgsouza.derails.View;
import carlosgsouza.vinylshop.model.Vinyl;

class SearchByTitleView extends View {

	public SearchByTitleView(String query, List<Vinyl> vinylList) {
		items.add("Listing " + vinylList.size() + " with title matching '" + query + "'");
		items.addAll(vinylList);
	}

}
