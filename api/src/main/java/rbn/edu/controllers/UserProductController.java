package rbn.edu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rbn.edu.config.ProjectConstants;
import rbn.edu.config.exceptions.BusinessException;
import rbn.edu.model.UserProduct;
import rbn.edu.service.IUserProductService;

@RestController
@RequestMapping("/userproduct")
public class UserProductController {

    @Autowired
    private IUserProductService userProductService;

    @Autowired
    private Environment env;

    @RequestMapping(method = RequestMethod.POST)
    public void send(@RequestBody List<UserProduct> userProductList) throws BusinessException {
	userProductService.save(userProductList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<UserProduct> get(@PathVariable("id") Long id) throws BusinessException {
	if (id == null) {
	    throw new BusinessException(env.getProperty(ProjectConstants.ID_NULL));
	}
	return userProductService.getByUserId(id);
    }

}
