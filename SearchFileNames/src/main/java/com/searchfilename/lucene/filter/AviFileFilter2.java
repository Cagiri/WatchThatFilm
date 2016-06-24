package com.searchfilename.lucene.filter;

import java.io.File;
import java.io.FileFilter;

public class AviFileFilter2 implements FileFilter{

	   @Override
	   public boolean accept(File pathname) {
	      return pathname.getName().toLowerCase().endsWith(".avi");
	   } 

}
