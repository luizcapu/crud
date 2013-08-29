package com.crud.dao;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;

public class SolrInterface {
	
    private static String url = "http://localhost:8983/solr/crud1/";
    private static HttpSolrServer solrCore;	
	
	public SolrInterface(){
		solrCore = new HttpSolrServer(url);
	}
	
	public QueryResponse execQuery(SolrQuery query) throws SQLException,
    SolrServerException, IOException{
		
		QueryResponse rsp = solrCore.query( query );
		
		return rsp;
	}
	
	public boolean doPost(SolrInputDocument doc) throws SQLException,
            SolrServerException, IOException{		
		
		solrCore.add(doc);
		solrCore.commit();
		
		return true;
	}
	
	public boolean doPostBean(Object obj) throws SQLException,
    	SolrServerException, IOException{		

		solrCore.addBean(obj);
		solrCore.commit();
		
		return true;
	}
	
	public boolean deleteById(String id) throws SQLException,
	SolrServerException, IOException{		

	solrCore.deleteById(id);
	solrCore.commit();
	
	return true;
}	
	
}
