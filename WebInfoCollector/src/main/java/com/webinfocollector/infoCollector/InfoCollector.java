package com.webinfocollector.infoCollector;

import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webinfocollector.infoCollector.model.MovieInfoModel;
import com.webinfocollector.util.InfoCollectorConstants;
import com.webinfocollector.util.Util;

public abstract class InfoCollector {

	private static String proxyIp;
	private static int port = 80;
	private static ResourceBundle rb = null;
	private static boolean isCrawlNeed = false;
	private String filmUrl;
	private String mainUrl;
	private String searchText;

	static {
		if (rb == null) {
			rb = ResourceBundle.getBundle("com.webinfocollector.properties.collector");
		}
		loadProperties();
	}

	public abstract MovieInfoModel collectInfoFromSite(Document doc);

	public String collect(String movieName) {
		if (isCrawlNeed) {
			webCrawlForMoviePage(movieName);
			if (!Util.isValidString(getFilmUrl())) {
				return "";
			}
		}

		return createJSONfromObj(collectInfoFromSite(getContext()));
	}

	public abstract void webCrawlForMoviePage(String movieName);

	protected String createJSONfromObj(MovieInfoModel mim) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting()
				.serializeNulls().create();

		return gson.toJson(mim);
	}

	@SuppressWarnings("unused")
	protected Document getContext() {

		Document doc = null;
		try {
			if (Util.isValidString(proxyIp)) {

				// TODO : Get dynamicaly generated html response with htmlunit...
				/*
				 * WebClient w = new
				 * WebClient(BrowserVersion.INTERNET_EXPLORER,proxyIp,port);
				 * Page p = w.getPage(getFilmUrl()); doc =
				 * Jsoup.parse(p.getWebResponse().getContentAsString());
				 */
				doc = Jsoup.connect(getFilmUrl()).proxy(proxyIp, port).timeout(3000).post();
			} else {
				doc = Jsoup.connect(getFilmUrl()).timeout(3000).post();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return doc;
	}

	public void setProxyInfo(String proxy, int prt) {
		proxyIp = proxy;
		port = prt;
	}

	private static void loadProperties() {
		String prt = rb.containsKey(InfoCollectorConstants.RESOURCE_PORT_TEXT.toLowerCase()) ? rb.getString("port") : "";
		String proxy = rb.containsKey(InfoCollectorConstants.RESOURCE_PROXY_TEXT.toLowerCase()) ? rb.getString("proxyip") : "";
		if (Util.isValidString(proxy)) {
			proxyIp = proxy;
		}
		if (Util.isValidString(prt)) {
			port = Integer.valueOf(prt);
		}
	}

	public String getMainUrl() {
		return mainUrl;
	}

	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getFilmUrl() {
		return filmUrl;
	}

	public void setFilmUrl(String filmUrl) {
		this.filmUrl = filmUrl;
	}

	public static void setCrawlNeed(boolean isCrawlNeed) {
		InfoCollector.isCrawlNeed = isCrawlNeed;
	}
}
