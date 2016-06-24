package com.webinfocollector.infoCollector.impl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.webinfocollector.infoCollector.InfoCollector;
import com.webinfocollector.infoCollector.model.MovieInfoModel;

public class ImdbInfoCollectorImpl extends InfoCollector {

	private final String filmSearchUrl = "http://www.imdb.com/find?ref_=nv_sr_fn&q=%s&s=all";
	private final String mainUrl = "http://www.imdb.com";
	private final String searchText = "table.findList > tbody > tr > td > a[href]";

	private ImdbInfoCollectorImpl() {
		super.setFilmSearchUrl(filmSearchUrl);
		super.setMainUrl(mainUrl);
		super.setSearchText(searchText);
		setCrawlNeed(true);
	}

	public void webCrawlForMoviePage(String movieName) {
		setFilmUrl(String.format(filmSearchUrl, movieName));
		Document doc = getContext();

		Element element = doc.select(getSearchText()).first();

		String url = element.attr("href");
		
		setFilmUrl(getMainUrl() + url);
		System.out.println(getFilmUrl());

	}

	@Override
	public MovieInfoModel collectInfoFromSite(Document doc) {
		MovieInfoModel mim = new MovieInfoModel();

		
		
		// collecting values from main website

		return mim;
	}

}
