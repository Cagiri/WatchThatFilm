package com.webinfocollector.util;

import com.webinfocollector.infoCollector.impl.ImdbInfoCollectorImpl;
import com.webinfocollector.infoCollector.impl.SinemalarInfoCollectorImpl;

public enum CollectorListEnum {

//	IMDB(ImdbInfoCollectorImpl.class.getCanonicalName()),
	SINEMALAR(SinemalarInfoCollectorImpl.class.getCanonicalName());
	
	String className;
	private CollectorListEnum(String c) {
		className = c;
	}
	
	public String getClassName(){
		return className;
	}
	
}
