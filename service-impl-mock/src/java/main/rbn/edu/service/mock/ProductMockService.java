package rbn.edu.service.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import rbn.edu.model.Product;
import rbn.edu.service.IProductService;

@Component
public class ProductMockService implements IProductService {

	private static List<Product> products = new ArrayList<Product>();

	public ProductMockService() {
		products.add(new Product((long) 1, "Product1", new BigDecimal(11)));
		products.add(new Product((long) 2, "Product2", new BigDecimal(22)));
		products.add(new Product((long) 3, "Product3", new BigDecimal(33)));
	}

	public void add(Product product) {
		products.add(product);
	}

	public void update(Product product) {
		Product productToUpdate = getProductById(product.getId());
		products.remove(productToUpdate);
		products.add(product);
	}

	public List<Product> getAll() {
		System.out.println("PASSOU");
		return products;
	}

	public Product getById(long id) {
		return getProductById(id);
	}

	public void remove(long id) {
		products.remove(getProductById(id));
	}

	private Product getProductById(final long id) {
		return products.stream().filter(p -> p.getId() == id).collect(Collectors.toList()).get(0);
	}

}
