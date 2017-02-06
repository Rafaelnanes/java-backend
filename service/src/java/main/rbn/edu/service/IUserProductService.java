package rbn.edu.service;

import java.io.Serializable;
import java.util.List;

import rbn.edu.model.UserProduct;

public interface IUserProductService extends Serializable {

    void save(List<UserProduct> list);

    void clear(long userId);

    List<UserProduct> getByUser(long userId);

}
