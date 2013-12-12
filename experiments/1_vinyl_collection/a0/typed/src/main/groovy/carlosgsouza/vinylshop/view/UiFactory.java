package carlosgsouza.vinylshop.view;

import java.util.List;

import carlosgsouza.derails.Form;
import carlosgsouza.derails.View;
import carlosgsouza.vinylshop.model.Report;
import carlosgsouza.vinylshop.model.Summary;
import carlosgsouza.vinylshop.model.Vinyl;

public class UiFactory {

	public UiFactory() {
	}

	public View deleteVinyl(){
		return new DeleteVinylView();
	}
	public View listArtists(List<String> artists){
		return new ListArtistsView(artists);
	}
	
	public View listGenres(List<String> genres){
		return new ListGenresView(genres);
	}
	
	public View listSongs(List<String> songs){
		return new ListSongsView(songs);
	}
	
	public View listVinyls(List<Vinyl> vinyls){
		return new ListVinylsView(vinyls);
	}
	
	public View searchByArtist(String query, List<Vinyl> vinyls){
		return new SearchByArtistView(query, vinyls);
	}
	
	public View searchByGenre(String query, List<Vinyl> vinyls){
		return new SearchByGenreView(query, vinyls);
	}
	
	public View searchByTitle(String query, List<Vinyl> vinyls){
		return new SearchByTitleView(query, vinyls);
	}
	
	public View searchByYear(String query, List<Vinyl> vinyls){
		return new SearchByYearView(query, vinyls);
	}
	
	public View showReport(String type, Report report){
		return new ShowReportView(type, report);
	}
	
	public View showSummary(Summary summary){
		return new ShowSummaryView(summary);
	}
	
	public View showVinyl(Vinyl creetedVinyl){
		if(creetedVinyl.isValid()) {
			return new ShowVinylView(creetedVinyl);
		} else {
			return new ShowVinylView(createdVinyl);
		}
	}
	
	public Form vinylForm() {
		return new VinylForm();
	}
}
