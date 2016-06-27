package com.webinfocollector.infoCollector.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
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

		Document d = Jsoup.parse("");
		// collecting values from main website
		@SuppressWarnings("unused")
		Elements e = doc.select("span#ratingCount");
		mim.setMovieName(doc.select("title").text());
		mim.setDescripton(doc.select("meta[name=\"description\"]").attr("content"));
		mim.setDirector(getCasts(doc.select("div.detail-film-info > div:contains(Yönetmen) > a")));
		mim.setCasts(getCasts(doc.select("div.detail-film-info > div:contains(Oyuncular) > a")));
		mim.setGenres(getGenre(doc));
		mim.setRate(doc.select("div.detail-film-info > div.rating > span").get(1).text());
		mim.setReleaseYear(doc.select("div.vizyon-tarih > span").text());
		mim.setVoteCount("");

		return mim;
	}

	private String[] getGenre(Document doc) {
		String genre = doc.select("div.detail-film-info > div.infolabel").get(1).text();
		genre = genre.substring(genre.indexOf(":")+1, genre.length()).trim();
		return genre.split(",");
	}
	
	private String[] getCasts(Elements es) {

		List<String> casts = new ArrayList<>();
		for (Element e : es) {
			if (Util.isValidString(e.text().trim())) {
				casts.add(e.text().trim());
			}
		}

		return casts.toArray(new String[0]);
	}
}
