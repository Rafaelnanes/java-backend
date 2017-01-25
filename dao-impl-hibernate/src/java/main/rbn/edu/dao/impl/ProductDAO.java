package rbn.edu.dao.impl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rbn.edu.dao.IProductDAO;
import rbn.edu.model.Product;

@Repository
@Transactional
public class ProductDAO extends GenericDAO<Product> implements IProductDAO{

	@Override
	protected Class<Product> getPersistenceClass() {
		return Product.class;
	}


}

