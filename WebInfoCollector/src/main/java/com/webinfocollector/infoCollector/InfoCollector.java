package com.webinfocollector.infoCollector;

import java.io.IOException;
import java.util.ResourceBundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webinfocollector.infoCollector.model.MovieInfoModel;
import com.webinfocollector.util.InfoCollectorConstants;
import com.webinfocollector.util.Util;

public abstract class InfoCollector {

	private static String proxyIp;
	private static int port = 80;
	private static ResourceBundle rb=null; 

	static {
		if(rb==null) {
			rb = ResourceBundle.getBundle("com.webinfocollector.properties.collector");
		}
		loadProperties();
	}
	
	public abstract MovieInfoModel collectInfoFromSite(Document doc);
	protected abstract String getFilmUrl();

	public String collect(String movieName) {
		
		return createJSONfromObj(collectInfoFromSite(getContext()));
	}
	
	public String createJSONfromObj(MovieInfoModel mim) {
		Gson gson = new GsonBuilder()
	             .disableHtmlEscaping()
	             .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
	             .setPrettyPrinting()
	             .serializeNulls()
	             .create();
		
		return gson.toJson(mim);
	}

	protected Document getContext() {
		
		Document doc = null;
		try {
			if (Util.isValidString(proxyIp)) {
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
}
