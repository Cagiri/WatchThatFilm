package com.searchfilename.lucene.filter;

import java.io.File;
import java.io.FileFilter;

public class TxtFileFilter implements FileFilter{

	   //For Subtitles...
	   @Override
	   public boolean accept(File pathname) {
	      return pathname.getName().toLowerCase().endsWith(".txt");
	   }

}
