package carlosgsouza.vinylshop.model

class Artist {
	String name = ""
	List<Vinyl> vinyls = []
	
	@Override
	public String toString() {
		"$name, Vinyls:${vinyls*.title}"
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((vinyls == null) ? 0 : vinyls.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this.is(obj))
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