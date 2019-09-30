package com.zensar.services;

import java.util.List;

import com.zensar.entities.User;
import com.zensar.exceptions.ServiceException;

/**
 * @author Mokshi Vyas
 * @version1.0
 * @creation_date 21/sep/2019 5.57pm
 * @modification_date 21/sep/2019 5.57pm
 * @copyright Zensar Technologies, India. All rights reserved
 * @description It is business service interface. used in bussiness layer
 * 				
 * 				
 */
public interface UserService {

	void addUser(User user) throws ServiceException;
	void updateUser(User user) throws ServiceException;
	void removeUser(User user) throws ServiceException;
	User findUserByUserName(String Username) throws ServiceException;
	List<User> findAllUsers() throws ServiceException;
	
	
	boolean validateUser(User user) throws ServiceException;
}
