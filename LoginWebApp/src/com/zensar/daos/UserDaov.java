package com.zensar.daos;

import java.sql.SQLException;
import java.util.List;

import com.zensar.entities.User;

/**
 * @author Prince tiwari
 * @version1.0
 * @creation_date 21/sep/2019 5.30pm
 * @modification_date 21/sep/2019 5.30pm
 * @copyright Zensar Technologies, India. All rights reserved
 * @description It is data acess object interface
 * 				
 * 				
 */
public interface UserDaov {
void insert(User user) throws SQLException;
void update(User user)  throws SQLException;
void delete(User user)  throws SQLException ;
User getByUsername(String username)  throws SQLException;
List<User> getAll() throws SQLException;

}
