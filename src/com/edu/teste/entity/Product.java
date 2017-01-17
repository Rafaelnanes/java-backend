package com.edu.teste.entity;

public class Product {

    private long id;
    private String name;
    private double value;

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

}
