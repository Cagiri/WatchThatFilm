package com.webinfocollector.infoCollector.impl;

import org.jsoup.nodes.Document;

import com.webinfocollector.infoCollector.InfoCollector;
import com.webinfocollector.infoCollector.model.MovieInfoModel;

public class ImdbInfoCollectorImpl extends InfoCollector{

	static ImdbInfoCollectorImpl imp;
	
	private ImdbInfoCollectorImpl() {
	}
	
	@Override
	protected String getFilmUrl() {

		return "http://www.imdb.com/title/tt0803096/";
	}

	@Override
	public MovieInfoModel collectInfoFromSite(Document doc) {
		MovieInfoModel mim = new MovieInfoModel();
		
		
		
		
		return mim;
	}
	
	
}

