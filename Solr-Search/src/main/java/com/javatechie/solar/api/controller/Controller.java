package com.javatechie.solar.api.controller;

import java.util.ArrayList;
import java.util.List;

import com.javatechie.solar.api.model.ProductEnity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.solar.api.repository.ProductRepository;

@RestController
@RequestMapping("/productSearch")
public class Controller {

	@Autowired
	ProductRepository repository;

	@GetMapping("/getAllProducts")
	public List<ProductEnity> getEmployees() {

		ArrayList<ProductEnity>   productsArrayList=new ArrayList<ProductEnity>();
		Iterable<ProductEnity> productIterator=repository.findAll();
		for(ProductEnity product:productIterator){
			productsArrayList.add(product);
		}
		return productsArrayList;
	}

	@GetMapping("/getItemsByName/{name}")
	public List<ProductEnity> getEmployeeByName(@PathVariable String name) {
		String searchValue=name+"~";
		System.out.println(searchValue);
		return repository.findByProductName(searchValue);
	}

}
