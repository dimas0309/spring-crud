package com.dagtech.springcrud.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dagtech.springcrud.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> getUsers() {
		
		// method to get an user list thought hibernate from database
        Session currentSession = sessionFactory.getCurrentSession();

		Query<User> theQuery =
				   currentSession.createQuery("from User order by lastName",
								                    User.class);
		
		List <User> users = theQuery.getResultList();
		
		return users;
	}

	@Override
	public void saveUser(User user) {
		
		// method to save an user thought hibernate from database
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(user);

	}

	@Override
	public User getUser(int id) {
		
		// method to get an user thought hibernate from database
		Session currentSession = sessionFactory.getCurrentSession();

		User user = currentSession.get(User.class, id);

		return user;
	}

	@Override
	public void deleteUser(int id) {
		
		// method to delete a user thought hibernate from database
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery =
				currentSession.createQuery("delete from User "
						+ "where id=:id");
		theQuery.setParameter("id", id);

		theQuery.executeUpdate();

	}

	@Override
	public List<User> searchUsers(String searchUname) {
		
		// method to search an user thought hibernate from database
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = null;

		if (searchUname != null && searchUname.trim().length() > 0) {

			theQuery = currentSession.createQuery(
					"from User where lower(firstName) like :name or "
					+ "lower(lastName) like :name", User.class);
			theQuery.setParameter("name", "%" + searchUname.toLowerCase()
			                      + "%");
		}
		else {

			theQuery = currentSession.createQuery("from User", User.class);
		}

		List <User> users = theQuery.getResultList();

		return users;
	}

}
