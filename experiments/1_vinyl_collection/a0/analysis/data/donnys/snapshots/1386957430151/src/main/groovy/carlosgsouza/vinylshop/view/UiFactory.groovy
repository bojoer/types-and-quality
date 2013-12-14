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

	public deleteVinyl(){
		return new DeleteVinylView();
	}
	public listArtists(List<String> artists){
		return new ListArtistsView(artists);
	}
	
	public listGenres(List<String> genres){
		return new ListGenresView(genres);
	}
	
	public listSongs(List<String> songs){
		return new ListSongsView(songs);
	}
	
	public listVinyls(List<Vinyl> vinyls){
		return new ListVinylsView(vinyls);
	}
	
	public searchByArtist(String query, List<Vinyl> vinyls){
		return new SearchByArtistView(query, vinyls);
	}
	
	public searchByGenre(String query, List<Vinyl> vinyls){
		return new SearchByGenreView(query, vinyls);
	}
	
	public searchByTitle(String query, List<Vinyl> vinyls){
		return new SearchByTitleView(query, vinyls);
	}
	
	public searchByYear(String query, List<Vinyl> vinyls){
		return new SearchByYearView(query, vinyls);
	}
	
	public showReport(String type, Report report){
		return new ShowReportView(type, report);
	}
	
	public showSummary(Summary summary){
		return new ShowSummaryView(summary);
	}
	
	public showVinyl(Vinyl creetedVinyl){
		return new ShowVinylView(createdVinyl);
	}
	
	public vinylForm() {
		return new VinylForm();
	}
}
