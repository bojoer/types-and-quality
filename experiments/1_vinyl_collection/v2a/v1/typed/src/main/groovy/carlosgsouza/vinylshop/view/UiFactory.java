package carlosgsouza.vinylshop.view

import carlosgsouza.derails.Form
import carlosgsouza.derails.View
import carlosgsouza.vinylshop.model.Artist
import carlosgsouza.vinylshop.model.Genre
import carlosgsouza.vinylshop.model.Report
import carlosgsouza.vinylshop.model.Summary
import carlosgsouza.vinylshop.model.Vinyl

class UiFactory {

	public UiFactory() {
	}

	public View deleteVinyl(){
		new DeleteVinylView()
	}
	public View listArtists(List<String> artists){
		new ListArtistsView(artists)
	}
	
	public View listGenres(List<String> genres){
		new ListGenresView(genres)
	}
	
	public View listSongs(List<String> songs){
		new ListSongsView(songs)
	}
	
	public View listVinyls(List<Vinyl> vinyls){
		new ListVinylsView(vinyls)
	}
	
	public View searchByArtist(String query, List<Vinyl> vinyls){
		new SearchByArtistView(query, vinyls)
	}
	
	public View searchByGenre(String query, List<Vinyl> vinyls){
		new SearchByGenreView(query, vinyls)
	}
	
	public View searchByTitle(String query, List<Vinyl> vinyls){
		new SearchByTitleView(query, vinyls)
	}
	
	public View searchByYear(String query, List<Vinyl> vinyls){
		new SearchByYearView(query, vinyls)
	}
	
	public View showReport(String type, Report report){
		new ShowReportView(type, report)
	}
	
	public View showSummary(Summary summary){
		new ShowSummaryView(summary)
	}
	
	public View showVinyl(Vinyl createdVinyl){
		new ShowVinylView(createdVinyl)
	}
	
	public Form vinylForm() {
		new VinylForm()
	}
}
