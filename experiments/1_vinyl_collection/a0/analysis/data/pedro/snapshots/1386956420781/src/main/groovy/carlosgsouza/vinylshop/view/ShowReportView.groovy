package carlosgsouza.vinylshop.view;

import carlosgsouza.derails.View;
import carlosgsouza.vinylshop.model.Report;

class ShowReportView extends View {

	public ShowReportView(name, report) {
		items.add(name + " Report");
		items.add(report);
	}

}
