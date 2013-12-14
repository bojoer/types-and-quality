package carlosgsouza.vinylshop.view;

import java.util.List;

import carlosgsouza.derails.View;
import carlosgsouza.vinylshop.model.Vinyl;

class SearchByArtistView extends View {

	public SearchByArtistView(query, vinylList) {
		def vinylCount = vinylList.size();
		items.add("Listing " + vinylCount + " with artist matching '" + query + "'");
		items.addAll(vinylList);
	}

}
