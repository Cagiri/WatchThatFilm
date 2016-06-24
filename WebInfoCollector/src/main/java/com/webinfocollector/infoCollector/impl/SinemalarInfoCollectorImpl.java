package com.webinfocollector.infoCollector.impl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.webinfocollector.infoCollector.InfoCollector;
import com.webinfocollector.infoCollector.model.MovieInfoModel;
import com.webinfocollector.util.Util;

public class SinemalarInfoCollectorImpl extends InfoCollector {

	private final String filmSearchUrl = "http://www.sinemalar.com/ara/?type=movie&page=1&q=%s";
	private final String mainUrl = "http://www.sinemalar.com/";
	private final String searchText = "div.grid8";

	private SinemalarInfoCollectorImpl() {
		super.setFilmSearchUrl(filmSearchUrl);
		super.setMainUrl(mainUrl);
		super.setSearchText(searchText);
		setCrawlNeed(true);
	}

	public void webCrawlForMoviePage(String movieName) {
		setFilmUrl(String.format(filmSearchUrl, movieName));
		Document doc = getContext();

		Elements elements = doc.select(getSearchText());

		double controlDist = 0.0d;
		for (Element element : elements) {
			String controlName = element.select("small").text();
			double dist = Util.similarity(controlName, movieName);
			if (controlDist < dist) {
				controlDist = dist;
				String url = element.select("div.grid2 > a[href]").attr("href");
				setFilmUrl(url);
			}
		}
		
		System.out.println(getFilmUrl());
	}

	@Override
	public MovieInfoModel collectInfoFromSite(Document doc) {
		MovieInfoModel mim = new MovieInfoModel();
		

		// collecting values from main website

		return mim;
	}
}
