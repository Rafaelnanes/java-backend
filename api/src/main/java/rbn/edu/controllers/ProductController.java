package rbn.edu.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rbn.edu.entity.Product;

@RestController
@RequestMapping("/product")
public class ProductController {

	private static List<Product> products = new ArrayList<Product>();

	public ProductController() {
		products.add(new Product(1, "Product1", 11));
		products.add(new Product(2, "Product2", 22));
		products.add(new Product(3, "Product3", 33));
	}

	@RequestMapping(method = RequestMethod.POST)
	public Product send(@RequestBody Product product) {
		products.add(product);
		return product;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Product> get() {
		return products;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public List<Product> delete(@PathVariable("id") int id) {
		products.remove(getProductById(id));
		return products;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Product getById(@PathVariable("id") int id) {
		return getProductById(id);
	}

	private Product getProductById(int id) {
		return products.stream().filter(p -> p.getId() == id).collect(Collectors.toList()).get(0);
	}

}
