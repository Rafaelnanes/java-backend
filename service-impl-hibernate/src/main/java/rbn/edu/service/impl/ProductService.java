package rbn.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rbn.edu.dao.impl.ProductDAO;
import rbn.edu.model.Product;
import rbn.edu.service.IProductService;

@Service
public class ProductService implements IProductService {
	
	@Autowired
	private ProductDAO productDAO;

	public Product add(Product t) {
		return productDAO.add(t);
	}

	public Product update(Product t) {
		return productDAO.update(t);
	}

	public List<Product> getAll() {
		return productDAO.getAll();
	}

	public Product getById(long id) {
		return productDAO.getById(id);
	}

	public void remove(long id) {
		productDAO.remove(id);
	}

}
