package com.zensar.daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * @author Mokshi Vyas
 * @version1.0
 * @creation_date 21/sep/2019 5.36pm
 * @modification_date 23/sep/2019 10.22am
 * @copyright Zensar Technologies, India. All rights reserved
 * @description It is data acess object interface implementor class
 * used in persistence layer
 * 				
 * 				
 */
import java.util.List;

import com.zensar.entities.User;

public class UserDaoImpl implements UserDaov {

	private Connection connection;

	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(User user)  throws SQLException {
	
		String sql = "INSERT INTO USER_LOGIN(?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		int insertCount = pstmt.executeUpdate();
		if(insertCount>0) {
			System.out.println("New user inserted");
		}
		else
		{
			System.out.println("New user cannot be inserted");
		}
	}

	@Override
	public void update(User user)  throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "UPDATE USER_LOGIN SET PASSWORD=? WHERE USERNAME=?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, user.getPassword());
		pstmt.setString(2, user.getUsername());
		int updateCount = pstmt.executeUpdate();
		if(updateCount>0)
		{
			System.out.println("Updated succesfully");
		}else
		{
			System.out.println("User record not  Update");
		}
				

	}

	@Override
	public void delete(User user)  throws SQLException {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM UDER_LOGIN WHERE USERNAME=?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		int deleteCount = pstmt.executeUpdate();
		if(deleteCount>0)
		{
			System.out.println("Deleted  succesfully");
		}else
		{
			System.out.println("User record notbe deleted");
		}
			
	}

	@Override
	public User getByUsername(String username)  throws SQLException {
		String sql = "SELECT USERNAME,PASSWORD FROM USER_LOGIN WHERE USERNAME=?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, username);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			User user = new User();
			user.setUsername(rs.getString(1));
			user.setPassword(rs.getString(2));
			return user;
		}
		else
		return null;
	}

	@Override
	public List<User> getAll() throws SQLException {
		// TODO Auto-generated method stub
		String sql= "SELECT USERNAME,PASSWORD FROM USER_LOGIN";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<User> users = new ArrayList<User>();
		while(rs.next()) {
			User user = new User();
			user.setUsername(rs.getString(1));
			user.setPassword(rs.getString(2));
			users.add(user);
			
		}
		
		return users;
	}

}
