package com.dagtech.springcrud.dao;

import java.util.List;

import com.dagtech.springcrud.entity.User;

public interface UserDao {
	
	public List<User> getUsers();
	
	public void saveUser(User user);
	
	public User getUser(int id);
	
	public void deleteUser(int id);
	
	public List<User> searchUsers(String searchUname);

}
