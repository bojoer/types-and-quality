package carlosgsouza.vinylshop.view;

import java.util.List;

import carlosgsouza.derails.View;
import carlosgsouza.vinylshop.model.Vinyl;

class SearchByGenreView extends View {

	public SearchByGenreView(query, vinylList) {
		items.add("Listing " + vinylList.size() + " with genre matching '" + query + "'");
		for(def v : vinylList) {
			if(v.isValid()) {
				items.addl(v);
			}
		}
	}

}
