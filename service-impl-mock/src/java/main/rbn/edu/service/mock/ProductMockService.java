package rbn.edu.service.mock;

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
		products.add(new Product(1, "Product1", 11));
		products.add(new Product(2, "Product2", 22));
		products.add(new Product(3, "Product3", 33));
	}

	public Product add(Product product) {
		products.add(product);
		return product;
	}

	public Product update(Product product) {
		Product productToUpdate = getProductById(product.getId());
		products.remove(productToUpdate);
		products.add(product);
		return product;
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
