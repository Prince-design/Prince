package com.zensar.services;

import java.sql.SQLException;
import java.util.List;

import com.zensar.daos.UserDaov;
import com.zensar.entities.User;
import com.zensar.exceptions.ServiceException;

public class UserServiceImpl implements UserService {
	private UserDaov userDao;
	public void  setUserDao(UserDaov userDao) {
		this.userDao = userDao;
	}

	@Override
	public void addUser(User user) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			userDao.insert(user);
			
		}catch (SQLException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void updateUser(User user) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			userDao.update(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void removeUser(User user) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			userDao.delete(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public User findUserByUserName(String username) throws ServiceException {
		// TODO Auto-generated method stub
		User user;
		try {
			user = userDao.getByUsername(username);
			return user;
		} catch (SQLException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<User> findAllUsers() throws ServiceException {
		try {
			return userDao.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean validateUser(User user) throws ServiceException {
		// TODO Auto-generated method stub
		User dbUser = findUserByUserName(user.getUsername());
		if(dbUser!= null && dbUser.getPassword().equals(user.getPassword()))
			return true;
		else
			return false;
	}

}
