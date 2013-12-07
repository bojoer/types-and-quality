package carlosgsouza.vinylshop.view

import carlosgsouza.derails.View
import carlosgsouza.vinylshop.model.Summary

class ShowSummaryView extends View {

	public ShowSummaryView(Summary summary) {
		items << "Collection Summary"
		items << summary
	}

}
