package rbn.edu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rbn.edu.config.ProjectConstants;
import rbn.edu.config.exceptions.BusinessException;
import rbn.edu.dao.IProductDAO;
import rbn.edu.enums.ProductTypeEnum;
import rbn.edu.model.FilterDTO;
import rbn.edu.model.Product;
import rbn.edu.model.ResponseServer;
import rbn.edu.service.IProductService;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductDAO productDAO;

    @Autowired
    private Environment env;

    @Transactional
    public void add(Product t) throws BusinessException {
	if (productDAO.getByName(t.getName()) != null) {
	    throw new BusinessException(env.getProperty(ProjectConstants.PRODUCT_ALREADY_EXISTS));
	}
	productDAO.add(t);
    }

    @Transactional
    public void update(Product t) throws BusinessException {
	Product productFromDB = productDAO.getByName(t.getName());
	if (productFromDB != null && productFromDB.equals(t)) {
	    throw new BusinessException(env.getProperty(ProjectConstants.PRODUCT_ALREADY_EXISTS));
	}
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

    public List<ProductTypeEnum> getAllProductTypes() {
	List<ProductTypeEnum> list = new ArrayList<ProductTypeEnum>();
	for (ProductTypeEnum type : ProductTypeEnum.values()) {
	    list.add(type);
	}
	return list;
    }

    public ResponseServer<Product> getAll(FilterDTO<Product> dto) {
	return productDAO.getAll(dto);
    }

}
