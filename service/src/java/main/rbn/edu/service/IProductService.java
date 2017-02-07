package rbn.edu.service;

import java.util.List;

import rbn.edu.enums.ProductTypeEnum;
import rbn.edu.model.Product;

public interface IProductService extends IGenericService<Product> {

    List<ProductTypeEnum> getAllProductTypes();

}
