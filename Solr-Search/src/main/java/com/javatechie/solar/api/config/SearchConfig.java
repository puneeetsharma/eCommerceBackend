package com.javatechie.solar.api.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@EnableSolrRepositories(
        basePackages = "com.example.Search.repository",
        namedQueriesLocation = "classpath:application.properties")
@ComponentScan
public class SearchConfig {

    @Bean
    public SolrClient solrClient() {
        HttpSolrClient solr = new HttpSolrClient.Builder("http://localhost:8983/solr/searchProducts").build();
        solr.setParser(new XMLResponseParser());
        return solr;
    }


    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
        return new SolrTemplate(client);
    }
}
