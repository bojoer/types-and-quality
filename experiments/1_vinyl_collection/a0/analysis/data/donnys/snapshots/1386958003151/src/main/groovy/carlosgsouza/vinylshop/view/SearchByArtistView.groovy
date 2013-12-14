package carlosgsouza.vinylshop.view;

import java.util.List;

import carlosgsouza.derails.View;
import carlosgsouza.vinylshop.model.Vinyl;

class SearchByArtistView extends View {

	public SearchByArtistView(query, vinylList) {
		def vinilCount = vinylList.size();
		items.add("Listing " + vinylCount + " with artist matching '" + query + "'");
		items.addAll(vinylList);
	}

}
