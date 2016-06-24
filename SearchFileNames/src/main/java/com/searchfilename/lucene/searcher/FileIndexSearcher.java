package com.searchfilename.lucene.searcher;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.searchfilename.lucene.util.SearcherConstants;

public class FileIndexSearcher {

	IndexSearcher indexSearcher;
	QueryParser queryParser;
	Query query;

	public FileIndexSearcher(String indexDirectoryPath) throws IOException {
		Directory indexDirectory = FSDirectory.open(Paths.get(indexDirectoryPath));
		IndexReader reader = DirectoryReader.open(indexDirectory);
		indexSearcher = new IndexSearcher(reader);
		queryParser = new QueryParser(SearcherConstants.FILE_NAME, new StandardAnalyzer());
	}

	public TopDocs search(String searchQuery) throws IOException {
		try {
			query = queryParser.parse(searchQuery);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return indexSearcher.search(query, SearcherConstants.MAX_SEARCH);
	}

	public Document getDocument(ScoreDoc scoreDoc) throws CorruptIndexException, IOException {
		return indexSearcher.doc(scoreDoc.doc);
	}
}