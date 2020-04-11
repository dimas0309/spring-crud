package com.dagtech.springcrud.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dagtech.springcrud.dao.UserDao;
import com.dagtech.springcrud.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	public UserDao userDao;

	@Override
	@Transactional
	public List<User> getUsers() {
		
		// services to retrieve a users created list
		return userDao.getUsers();
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		
		// service to save an user
		userDao.saveUser(user);
	}

	@Override
	@Transactional
	public User getUser(int id) {
		 
		// service to get an user
		// use in UserController.class to update it
		return userDao.getUser(id);
	}

	@Override
	@Transactional
	public void deleteUser(int id) {
		
		// service for delete an user
		userDao.deleteUser(id);
	}

	@Override
	@Transactional
	public List<User> searchUsers(String searchUname) {
		
		// service for search an user
		return userDao.searchUsers(searchUname);
	}

}
