package carlosgsouza.vinylshop.view;

import java.util.List;

import carlosgsouza.derails.View;
import carlosgsouza.vinylshop.model.Vinyl;

class SearchByGenreView extends View {

	public SearchByGenreView(String query, List<Vinyl> vinylList) {
		items.add("Listing " + vinylList.size() + " with genre matching '" + query + "'");
		for(Vinyl v : vinylList) {
			if(v.isValid()) {
				items.add(v);
			}
		}
	}

}
