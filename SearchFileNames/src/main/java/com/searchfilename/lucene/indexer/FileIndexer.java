package com.searchfilename.lucene.indexer;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.searchfilename.lucene.util.SearcherConstants;

public class FileIndexer {

	private IndexWriter writer;

	public FileIndexer(String indexDirectoryPath) throws IOException {
		// this directory will contain the indexes
		Directory indexDirectory = FSDirectory.open(Paths.get(indexDirectoryPath));

		IndexWriterConfig iwc = new IndexWriterConfig(new StandardAnalyzer());

		// create the indexer
		writer = new IndexWriter(indexDirectory, iwc);
	}

	public void close() throws CorruptIndexException, IOException {
		writer.close();
	}

	private Document getDocument(File file) throws IOException {
		Document document = new Document();

		// index file name
		Field fileNameField = new StringField(SearcherConstants.FILE_NAME, file.getName().substring(0, file.getName().indexOf(".")), Field.Store.YES);
		// index file path
		Field filePathField = new StringField(SearcherConstants.FILE_PATH, file.getCanonicalPath(), Field.Store.YES);

		document.add(fileNameField);
		document.add(filePathField);

		return document;
	}

	public int createIndex(String dataDirPath, FileFilter[] filter) throws IOException {
		// get all files in the data directory
		File[] files = new File(dataDirPath).listFiles();

		for (File file : files) {
			if (!file.isDirectory() && !file.isHidden() && file.exists() && file.canRead()) {
				boolean control = false;
				for (FileFilter flt : filter) {
					if (flt.accept(file)) {
						control = true;
						continue;
					}
				}
				if (control) {
					indexFile(file);
				}
			}
		}
		return writer.numDocs();
	}

	private void indexFile(File file) throws IOException {
		System.out.println("Indexing " + file.getCanonicalPath());
		Document document = getDocument(file);
		writer.addDocument(document);
	}

}