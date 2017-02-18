package rbn.edu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rbn.edu.config.exceptions.BusinessException;
import rbn.edu.enums.ProductTypeEnum;
import rbn.edu.model.Product;
import rbn.edu.model.api.FilterDTO;
import rbn.edu.model.api.ResponseServer;
import rbn.edu.service.IProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public void send(@RequestBody Product product) throws BusinessException {
	productService.add(product);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody Product product) throws BusinessException {
	productService.update(product);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_VISITOR')")
    @RequestMapping(method = RequestMethod.GET)
    public List<Product> get() {
	return productService.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ResponseServer<Product> getByQuery(@RequestBody FilterDTO<Product> dto) throws BusinessException {
	return productService.getAll(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_VISITOR')")
    @RequestMapping(value = "/productTypes", method = RequestMethod.GET)
    public List<ProductTypeEnum> getProductTypes() {
	return productService.getAllProductTypes();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") long id) throws BusinessException {
	productService.remove(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getById(@PathVariable("id") long id) {
	return productService.getById(id);
    }

}
