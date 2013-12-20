package carlosgsouza.vinylshop.controller

class SongUtils {

	public static instance = new SongUtils()
	
	private SongUtils() {}
	
	private songCount(vinyls) {
		def result = 0;
		
		for(def vinyl : vinyls) {
			result += vinyl.songs.size();
		}
		
		return String.valueOf(result);
	}
	
	private valid() {
		return instance != null
	}
}
