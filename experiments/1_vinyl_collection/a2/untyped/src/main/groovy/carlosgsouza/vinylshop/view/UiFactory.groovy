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
	public listArtists(artists){
		return new ListArtistsView(artists);
	}
	
	public listGenres(genres){
		return new ListGenresView(genres);
	}
	
	public listSongs(songs){
		return new ListSongsView(songs);
	}
	
	public listVinyls(vinyls){
		return new ListVinylsView(vinyls);
	}
	
	public searchByArtist(query, vinyls){
		return new SearchByArtistView(query, vinyls);
	}
	
	public searchByGenre(query, vinyls){
		return new SearchByGenreView(query, vinyls);
	}
	
	public searchByTitle(query, vinyls){
		return new SearchByTitleView(query, vinyls);
	}
	
	public searchByYear(query, vinyls){
		return new SearchByYearView(query, vinyls);
	}
	
	public showReport(type, report){
		return new ShowReportView(type, report);
	}
	
	public showSummary(summary){
		return new ShowSummaryView(summary);
	}
	
	public showVinyl(createdVinyl){
		return new ShowVinylView(createdVinyl);
	}
	
	public vinylForm() {
		return new VinylForm();
	}
}
