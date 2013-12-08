package carlosgsouza.vinylshop.view;

import java.util.List;

import carlosgsouza.derails.View;
import carlosgsouza.vinylshop.model.Vinyl;

class SearchByArtistView extends View {

	public SearchByArtistView(query, vinylList) {
		items.add("Listing " + vinylList.size() + " with artist matching '" + query + "'");
		items.addAll(vinylList);
	}

}
