package carlosgsouza.vinylshop.model;

import java.util.ArrayList;
import java.util.List;

public class Artist {
	public String name = "";
	public List<Vinyl> vinyls = new ArrayList<Vinyl> ();
	
	public Artist() {
	}
	
	@Override
	public String toString() {
		List<String> vinylNames = new ArrayList<String>();
		
		for(Vinyl vinyl : vinyls) {
			vinylNames.add(vinyl.title);
		}
		
		return name + ", Vinyls:" + vinylNames;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (vinyls == null) {
			if (other.vinyls != null)
				return false;
		} else if (!vinyls.equals(other.vinyls))
			return false;
		return true;
	}
}