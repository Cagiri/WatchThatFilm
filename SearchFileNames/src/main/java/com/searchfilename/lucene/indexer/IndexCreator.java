package com.searchfilename.lucene.indexer;

import java.io.FileFilter;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import com.searchfilename.lucene.filter.TxtFileFilter;
import com.searchfilename.lucene.searcher.FileIndexSearcher;
import com.searchfilename.lucene.util.SearcherConstants;

public class IndexCreator {
	String indexDir = System.getProperty("user.dir")+"\\LuceneFiles";
	String dataDir = System.getProperty("user.dir")+"\\LuceneFiles";
	FileIndexer indexer;
	FileIndexSearcher searcher;

	public static void main(String[] args) {
		IndexCreator tester;
		try {
			tester = new IndexCreator();
			tester.createIndex();
			tester.search("text1");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void createIndex() throws IOException {
		indexer = new FileIndexer(indexDir);
		long startTime = System.currentTimeMillis();
		int numIndexed = indexer.createIndex(dataDir, new FileFilter[] { new TxtFileFilter() });
		long endTime = System.currentTimeMillis();
		indexer.close();
		System.out.println(numIndexed + " File indexed, time taken: " + (endTime - startTime) + " ms");
	}

	private void search(String searchQuery) throws IOException, ParseException {
		searcher = new FileIndexSearcher(indexDir);
		long startTime = System.currentTimeMillis();
		TopDocs hits = searcher.search(searchQuery);
		long endTime = System.currentTimeMillis();

		System.out.println(hits.totalHits + " documents found. Time :" + (endTime - startTime));
		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			System.out.println("File: " + doc.get(SearcherConstants.FILE_PATH));
		}
	}
}
