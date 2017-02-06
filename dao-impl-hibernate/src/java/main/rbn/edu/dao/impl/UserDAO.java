package rbn.edu.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import rbn.edu.dao.IUserDAO;
import rbn.edu.model.User;

@Repository
public class UserDAO extends GenericDAO<User> implements IUserDAO {

    @Override
    protected Class<User> getPersistenceClass() {
	return User.class;
    }

    public User findUserByLogin(String login) {
	Criteria criteria = getSession().createCriteria(getPersistenceClass());
	criteria.add(Restrictions.eq("login", login));
	return (User) criteria.uniqueResult();
    }

}
