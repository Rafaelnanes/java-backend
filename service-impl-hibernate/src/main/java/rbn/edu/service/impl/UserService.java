package rbn.edu.service.impl;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import rbn.edu.dao.IUserDAO;
import rbn.edu.dao.IUserLevelDAO;
import rbn.edu.exceptions.BusinessException;
import rbn.edu.model.User;
import rbn.edu.model.UserLevel;
import rbn.edu.service.IUserService;

@Service
public class UserService implements IUserService {

    private static final String USER_LOGIN_ALREADY_EXISTS = "User login already exists.";

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private IUserLevelDAO userLevelDAO;

    public boolean isUserLogged() {
	String login = "anonymousUser";
	SecurityContext context = SecurityContextHolder.getContext();
	Authentication authentication = context.getAuthentication();
	if (authentication != null) {
	    try {
		login = (String) authentication.getPrincipal();
	    } catch (ClassCastException e) {
		login = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal())
			.getUsername();
	    }
	}
	return login.equals("anonymousUser") ? false : true;
    }

    public User getUserLogged() {
	User user = null;
	String login = "";
	if (isUserLogged()) {
	    SecurityContext context = SecurityContextHolder.getContext();
	    Authentication authentication = context.getAuthentication();
	    try {
		login = (String) authentication.getPrincipal();
	    } catch (ClassCastException e) {
		login = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal())
			.getUsername();
	    }
	    user = getUserByLogin(login);
	}
	return user;
    }

    @Transactional
    public void add(User t) throws BusinessException {
	try {
	    User userFromDB = userDAO.findUserByLogin(t.getLogin());
	    if (userFromDB != null) {
		throw new BusinessException(USER_LOGIN_ALREADY_EXISTS);
	    }
	    t.setPassword(new BCryptPasswordEncoder().encode(t.getPassword()));
	    Set<UserLevel> userLevels = t.getUserLevels();
	    userDAO.add(t);
	    for (UserLevel level : userLevels) {
		userLevelDAO.add(level);
	    }

	} catch (Exception e) {
	    throw new BusinessException(e.getMessage());
	}
    }

    @Transactional
    public void update(User t) throws BusinessException {
	try {
	    User userFromDB = userDAO.findUserByLogin(t.getLogin());
	    if (userFromDB != null && userFromDB.getId().intValue() != t.getId().intValue()) {
		throw new BusinessException(USER_LOGIN_ALREADY_EXISTS);
	    }
	    t.setPassword(new BCryptPasswordEncoder().encode(t.getPassword()));
	    removeAllUserLevelsByUserId(t.getId());
	    Set<UserLevel> userLevels = t.getUserLevels();
	    updateTransactional(t);
	    for (UserLevel level : userLevels) {
		userLevelDAO.add(level);
	    }
	} catch (Exception e) {
	    throw new BusinessException(e.getMessage());
	}
    }

    @Transactional
    private void updateTransactional(User t) {
	userDAO.update(t);
    }

    @Transactional
    private void removeAllUserLevelsByUserId(long id) {
	userLevelDAO.removeAllByUserId(id);
    }

    @Transactional
    public List<User> getAll() {
	return userDAO.getAll();
    }

    @Transactional
    public User getById(long id) {
	return userDAO.getById(id);
    }

    @Transactional
    public void remove(long id) throws BusinessException {
	try {
	    removeAllUserLevelsByUserId(id);
	    userDAO.remove(id);
	} catch (Exception e) {
	    throw new BusinessException(e.getMessage());
	}
    }

    public UserLevel getUserLevelById(long id) {
	return userLevelDAO.getById(id);
    }

    @Transactional
    private User getUserByLogin(String login) {
	return userDAO.findUserByLogin(login);
    }

}
