package com.edu.teste.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edu.teste.entity.Product;

@RestController
@RequestMapping("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @RequestMapping(method = RequestMethod.POST)
    public Product send(@RequestBody Product product) {
	System.out.println(product.toString());
	return product;
    }

}
