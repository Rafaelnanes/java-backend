package rbn.edu.service;

import java.util.List;

public interface IGenericService<T> {

	T add(T t);

	T update(T t);

	List<T> getAll();

	T getById(long id);

	void remove(long id);

}
