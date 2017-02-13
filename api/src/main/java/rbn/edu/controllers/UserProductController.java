package rbn.edu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rbn.edu.config.exceptions.BusinessException;
import rbn.edu.model.UserProduct;
import rbn.edu.service.IUserProductService;

@RestController
@RequestMapping("/userproduct")
public class UserProductController {

    @Autowired
    private IUserProductService userProductService;

    @RequestMapping(method = RequestMethod.POST)
    public void send(@RequestBody List<UserProduct> userProductList) throws BusinessException {
	userProductService.save(userProductList);
    }

}
