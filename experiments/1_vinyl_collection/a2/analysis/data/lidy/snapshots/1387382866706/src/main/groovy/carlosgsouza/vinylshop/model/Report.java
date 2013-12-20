package carlosgsouza.vinylshop.model;

import java.util.*;

public class Report {
	public Map<String, String> data = new HashMap<String, String>();
	
	@Override
	public String toString() {
		if(data == null || data.size() == 0) {
			return "";
		}

		List<String> items = new ArrayList<String>();

		for(String key : data.keySet()) {
			items.add(key + ": " + data.get(key));
		}

		String result = items.get(0);
		for(int i = 1; i < items.size(); i++) {
			result += "\n";
			result += items.get(i);
		}

		return result;
	}
	
	@Override
	public boolean equals(Object other) {
		Report otherReport = (Report) other;
		return this.data.equals(otherReport.data);
	}
}