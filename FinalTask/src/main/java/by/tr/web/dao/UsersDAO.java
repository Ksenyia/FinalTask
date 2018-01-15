package by.tr.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.tr.web.entity.User;

public class UsersDAO {
	public static List<User> getUsers() throws SQLException{
		Connection con = MySQLConnector.connect();
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    List<User> users = new ArrayList<User>();
	    preparedStatement = con.prepareStatement("SELECT * FROM users");
	   // preparedStatement.setString(1, "\"ru\"");
	   // preparedStatement.setString(2, "\"ru\"");
	    rs = preparedStatement.executeQuery();
	    
		while(rs.next()) {
			User user = new User();
			System.out.println(rs.getString("login"));
			int id =rs.getInt("id_users");
			String email = rs.getString("email");
			String login = rs.getString("login");
			boolean accessFlag;
			System.out.println(rs.getByte("access") + " "+ rs.getByte("admin_flag"));
			if(rs.getByte("access")==1){
				accessFlag = true;
			}
			else{
				accessFlag = false;
			}
			boolean adminFlag;
			if(rs.getByte("admin_flag")==1){
				adminFlag = true;
			}
			else{
				adminFlag = false;
			}
			int status = rs.getInt("status");
			user.setAccessFlag(accessFlag);
			user.setAdminFlag(adminFlag);
			user.setId(id);
			user.setEmail(email);
			user.setLogin(login);
			user.setStatus(status);
			users.add(user);
		}
		rs.close();
		return users;	
	}
}
