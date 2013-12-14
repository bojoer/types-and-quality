package carlosgsouza.vinylshop.view;

import java.util.List;

import carlosgsouza.derails.View;
import carlosgsouza.vinylshop.model.Vinyl;

class SearchByArtistView extends View {

	public SearchByArtistView(String query, List<Vinyl> vinylList) {
		//int vinilCount = vinylList.size();
		items.add("Listing " + inylList.size() + " with artist matching '" + query + "'");
		items.addAll(vinylList);
	}

}
