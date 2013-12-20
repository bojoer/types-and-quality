package carlosgsouza.vinylshop;

import java.util.ArrayList;
import java.util.List;

import carlosgsouza.derails.App;
import carlosgsouza.derails.Form;
import carlosgsouza.derails.View;
import carlosgsouza.vinylshop.controller.ArtistController;
import carlosgsouza.vinylshop.controller.GenreController;
import carlosgsouza.vinylshop.controller.ReportController;
import carlosgsouza.vinylshop.controller.SongController;
import carlosgsouza.vinylshop.controller.SummaryController;
import carlosgsouza.vinylshop.controller.VinylController;
import carlosgsouza.vinylshop.controller.YearController;
import carlosgsouza.vinylshop.database.DB;
import carlosgsouza.vinylshop.model.Report;
import carlosgsouza.vinylshop.model.Summary;
import carlosgsouza.vinylshop.model.Vinyl;
import carlosgsouza.vinylshop.view.UiFactory;

public class VinylCollectionApp extends App {
	
	static app = new VinylCollectionApp();
	
	public vinylController = new VinylController();
	public artistController = new ArtistController();
	public yearController = new YearController();
	public genreController = new GenreController();
	public songController = new SongController();
	public summaryController = new SummaryController();
	public reportController = new ReportController();
	
	def db = DB.connect();
	
	private c = 0;
	
	private uiFactory = new UiFactory();

	public preloadedVinyls = new ArrayList<Vinyl>();
	
	VinylCollectionApp() {
		super("DJ PopCorn - Amazing Vinyl Collection");
		
		preloadedVinyls.add(new Vinyl("Lana Del Rey", "Born to Die", listOf("Off to Races", "Radio", "Carmen"), "2012", "Pop"));
		preloadedVinyls.add(new Vinyl("Bruno Mars", "Unorthodox Jukebox", listOf("Gorilla", "Treasure", "Young Girls"), "2012", "Pop"));
		preloadedVinyls.add(new Vinyl("Pearl Jam", "Lightning Bolt", listOf("Getaway", "Mind Your Manners", "Young Sirens"), "2013", "Rock"));
		preloadedVinyls.add(new Vinyl("Angra", "Temple of Shadows", listOf("Deus Le Volt!", "Waiting Silence"), "2004", "Metal"));
		preloadedVinyls.add(new Vinyl("Luan Santana", "Quando Chega a Noite", listOf("Te vivo", "Quimica do Amor"), "2010", "Rock"));
		preloadedVinyls.add(new Vinyl("Coldplay", "Parachutes", listOf("Don't Panic", "Shiver", "Spies"), "2000", "Alternative"));
		preloadedVinyls.add(new Vinyl("Pearl Jam", "Backspacer", listOf("Just Breathe", "Supersonic"), "2009", "Rock"));
	}
	
	private listOf(...items) {
		def result = new ArrayList<String>();
		
		for(def item : items) {
			result.add(item);
		}
		
		return result;
	}
	
	public routeRequest(controller, action, parameter) {
		if(controller.equals("vinyl")) {
			//The id of the element
			def id;
			
			switch(action) {
				
				case "create":
					def form = uiFactory.vinylForm();
					console.apply(form);
					id = vinylController.create(form.fields);
					def createdVinyl = vinylController.get(id);
					
					console.render(uiFactory.showVinyl(createdVinyl));
					return;
				
				case "delete":
					id = Integer.valueOf(parameter);
					vinylController.delete(id);
					
					console.render(uiFactory.deleteVinyl());
					return;
				
				case "list":
					def vinyls = vinylController.list();
					console.render(uiFactory.listVinyls(vinyls));
					return;
					
				case "show":
					if(controller == null) {
						return;
					}
					id = Integer.valueOf(parameter);
					def vinyl = vinylController.get(id);
					
					console.render(uiFactory.showVinyl(vinyl));
					return;
					
				case "search":
					def result = vinylController.search(parameter);
					console.render(uiFactory.searchByTitle(parameter, result));
					return;
			}
		} else if(controller.equals("artist")) {
			def artists;
			
			switch(action) {
				case "list":
					artists = artistController.list();
					console.render(uiFactory.listArtists(artists));
					return;
				case "search":
					def vinyls = artistController.search(parameter);
					console.render(uiFactory.searchByArtist(parameter, vinyls));
					return;
			}
		} else if(controller.equals("song")) {
			switch(action) {
				case "list":
					def result = songController.list();
					console.render(uiFactory.listSongs(result));
					return;
			}
		} else if(controller.equals("year")) {
			switch(action) {
				case "search":
					def result = yearController.search(parameter);
					console.render(uiFactory.searchByYear(parameter, result));
					return;
			}
		} else if(controller.equals("genre")) {
			switch(action) {
				case "list":
					def result = genreController.list();
					console.render(uiFactory.listGenres(result));
					return;
				case "search":
					def vinyls = genreController.search(parameter);
					console.render(uiFactory.searchByGenre(parameter, vinyls));
					return;
			}
		} else if(controller.equals("summary")) {
			switch(action) {
				case "show":
					def summary = summaryController.show();
					console.render(uiFactory.showSummary(summary));
					return;
			}
		} else if(controller.equals("report")) {
			def report;
			
			switch(action) {
				case "genre":
					report = reportController.genre();
					console.render(uiFactory.showReport("Genre", report));
					return;
				
				case "artist":
					report = reportController.artist();
					console.render(uiFactory.showReport("Artist", report));
					return;
			}
		}
		
		console.render(new View("command not found"));
	}
	
	public bootstrap() {
		db.reset();
		
		for(def vinyl : preloadedVinyls) {
			vinylController.create(vinyl);
		}
	}
	
	public static main(args) {
		app.run();
	}
}