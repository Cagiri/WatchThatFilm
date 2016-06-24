package com.webinfocollector.infoCollector.impl;

import org.jsoup.nodes.Document;

import com.webinfocollector.infoCollector.InfoCollector;
import com.webinfocollector.infoCollector.model.MovieInfoModel;

public class SinemalarInfoCollectorImpl extends InfoCollector {

	private SinemalarInfoCollectorImpl() {
		//
	}

	@Override
	protected String getFilmUrl() {
		
		return "http://www.sinemalar.com/film/5199/warcraft";
	}

	@Override
	public MovieInfoModel collectInfoFromSite(Document doc) {
		MovieInfoModel mim = new MovieInfoModel();
		
		return mim;
	}
}
