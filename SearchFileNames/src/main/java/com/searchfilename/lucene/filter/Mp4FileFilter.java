package com.searchfilename.lucene.filter;

import java.io.File;
import java.io.FileFilter;

public class Mp4FileFilter implements FileFilter{

	   @Override
	   public boolean accept(File pathname) {
	      return pathname.getName().toLowerCase().endsWith(".mp4");
	   }

}
