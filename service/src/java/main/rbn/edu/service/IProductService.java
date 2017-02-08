package rbn.edu.service;

import java.util.List;

import rbn.edu.enums.ProductTypeEnum;
import rbn.edu.model.FilterDTO;
import rbn.edu.model.Product;
import rbn.edu.model.ResponseServer;

public interface IProductService extends IGenericService<Product> {

    List<ProductTypeEnum> getAllProductTypes();

    ResponseServer<Product> getAll(FilterDTO<Product> dto);

}
