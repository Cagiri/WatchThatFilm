package com.webinfocollector.main;

import java.util.ArrayList;
import java.util.List;

import com.webinfocollector.infoCollector.factory.InfoCollectorFactory;
import com.webinfocollector.util.CollectorListEnum;

public class TestCollector {
	
	public static void main(String[] args) {
		List<String> movieInfos = new ArrayList<>();

		for (CollectorListEnum cList : CollectorListEnum.values()) {
			movieInfos.add(InfoCollectorFactory.getInfoCollector(cList.getClassName()).collect("spiderman"));
		}
		
		/*for (String movieJson : movieInfos) {
			System.out.println(movieJson);
		}*/	
	}
}