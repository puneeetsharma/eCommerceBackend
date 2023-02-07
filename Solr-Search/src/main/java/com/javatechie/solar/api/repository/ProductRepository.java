package com.javatechie.solar.api.repository;

import com.javatechie.solar.api.model.ProductEnity;
import org.springframework.data.solr.repository.SolrCrudRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ProductRepository extends SolrCrudRepository<ProductEnity, String>{

	//@Query("select p from ProductEntity p where p.productName like ?")
	List<ProductEnity> findByProductName(String name);



}
