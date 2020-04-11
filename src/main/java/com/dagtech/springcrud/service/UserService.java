package com.dagtech.springcrud.service;

import java.util.List;

import com.dagtech.springcrud.entity.User;

public interface UserService {
	
	public List <User> getUsers();

	public void saveUser(User user);

	public User getUser(int id);

	public void deleteUser(int id);

	public List<User> searchUsers(String searchUname);

}
