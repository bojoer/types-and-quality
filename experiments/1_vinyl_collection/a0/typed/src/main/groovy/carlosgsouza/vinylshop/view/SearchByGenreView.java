package carlosgsouza.vinylshop.view;

import java.util.List;

import carlosgsouza.derails.View;
import carlosgsouza.vinylshop.model.Vinyl;

class SearchByGenreView extends View {

	public SearchByGenreView(String query, List<Vinyl> vinilList) {
		if(vinilList.size() > 0) {
			items.add("Listing " + vinilList.size() + " with genre matching '" + query + "'");
			items.addAll(vinilList);
		} else {
			items.add(vinylList);
		}
	}

}
