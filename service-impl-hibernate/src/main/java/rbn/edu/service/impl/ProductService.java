package rbn.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rbn.edu.dao.impl.ProductDAO;
import rbn.edu.model.Product;
import rbn.edu.service.IProductService;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductDAO productDAO;

    @Transactional
    public void add(Product t) {
	productDAO.add(t);
    }

    @Transactional
    public void update(Product t) {
	productDAO.update(t);
    }

    public List<Product> getAll() {
	return productDAO.getAll();
    }

    public Product getById(long id) {
	return productDAO.getById(id);
    }

    @Transactional
    public void remove(long id) {
	productDAO.remove(id);
    }

}
