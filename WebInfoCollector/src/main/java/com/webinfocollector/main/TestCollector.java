package com.webinfocollector.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.webinfocollector.infoCollector.impl.InfoCollectorFactory;
import com.webinfocollector.util.CollectorListEnum;

public class TestCollector {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		List<String> movieInfos = new ArrayList<>();

		for (CollectorListEnum cList : CollectorListEnum.values()) {
			movieInfos.add(InfoCollectorFactory.getInfoCollector(cList.getClassName()).collect("Spiderman"));
		}
		
		for (String movieJson : movieInfos) {
			System.out.println(movieJson);
		}
	}
}

//getting info
//
//Elements newsHeadlines = doc.select("title");
//System.out.println(element.text());