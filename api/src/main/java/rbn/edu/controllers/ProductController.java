package rbn.edu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rbn.edu.exceptions.BusinessException;
import rbn.edu.model.Product;
import rbn.edu.service.IProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping(method = RequestMethod.POST)
    public void send(@RequestBody Product product) throws BusinessException {
	productService.add(product);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody Product product) throws BusinessException {
	productService.update(product);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> get() {
	return productService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") long id) throws BusinessException {
	productService.remove(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getById(@PathVariable("id") long id) {
	return productService.getById(id);
    }

}
