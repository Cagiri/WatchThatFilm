package com.webinfocollector.infoCollector.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.webinfocollector.infoCollector.InfoCollector;
import com.webinfocollector.infoCollector.model.MovieInfoModel;
import com.webinfocollector.util.Util;

public class ImdbInfoCollectorImpl extends InfoCollector {

	private final String filmSearchUrl = "http://www.imdb.com/find?ref_=nv_sr_fn&q=%s&s=all";
	private final String mainUrl = "http://www.imdb.com";
	private final String searchText = "table.findList > tbody > tr > td > a[href]";

	private ImdbInfoCollectorImpl() {
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
		mim.setMovieName(doc.select("title").text());
		mim.setDescripton(doc.select("div.summary_text[itemprop=\"description\"]").text());
		mim.setDirector(getCasts(doc.select("div.credit_summary_item > span[itemprop=\"director\"] a > span.itemprop")));
		mim.setCasts(getCasts(doc.select("div.credit_summary_item > span[itemprop=\"actors\"] a > span.itemprop")));
		mim.setGenres(getGenre(doc.select("div.see-more[itemprop=\"genre\"] > a[href]")));
		mim.setRate(doc.select("div.imdbRating > div.ratingValue > strong > span[itemprop=\"ratingValue\"]").text());
		mim.setVoteCount(doc.select("a > span.small[itemprop=\"ratingCount\"]").text());
		mim.setReleaseYear(doc.select("div.subtext > a[title=\"See more release dates\"]").text());

		return mim;
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

	private String[] getGenre(Elements es) {
		List<String> genre = new ArrayList<>();
		for (Element e : es) {
			if (Util.isValidString(e.text().trim())) {
				genre.add(e.text().trim());
			}
		}

		return genre.toArray(new String[0]);
	}
}
