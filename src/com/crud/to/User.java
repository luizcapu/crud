package com.crud.to;

import org.apache.solr.client.solrj.beans.Field;

public class User {
	@Field
	public
    String id;

    @Field
    public
    String nome;

    @Field
    public
    String apelido;
    
    @Field
    public
    String[] interesses;
}
