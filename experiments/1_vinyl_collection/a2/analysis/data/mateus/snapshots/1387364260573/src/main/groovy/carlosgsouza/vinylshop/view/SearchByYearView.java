package carlosgsouza.vinylshop.view;

import java.util.List;

import carlosgsouza.derails.View;
import carlosgsouza.vinylshop.model.Vinyl;

class SearchByYearView extends View {

	public SearchByYearView(String query,List<Vinyl> vinylList) {
		items.add("Listing " + vinylList.size() + " with year matching '" + query + "'");
		items.addAll(vinylList);
	}

}
