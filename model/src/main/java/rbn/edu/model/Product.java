package rbn.edu.model;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = -7842596050912714624L;

	private long id;
	private String name;
	private double value;

	public Product(){}
	
	public Product(long id, String name, double value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", value=" + value + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == other.id)
			return true;
		return false;
	}
	
	

}