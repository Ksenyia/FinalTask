package by.tr.web.service;

import java.util.HashMap;
import java.util.List;

import by.tr.web.dao.UsersDAO;
import by.tr.web.entity.User;

public class UsersService {
	
	public List<User> getUsers() {
        UsersDAO usersDAO =  new UsersDAO();
        List<User> users =  usersDAO.getUsers();
        return users;
	}
	
	public boolean updateStatuses(HashMap<Integer, Integer> statuses){
		UsersDAO usersDAO =  new UsersDAO();
		if(usersDAO.updateStatuses(statuses)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean updateAccesses(HashMap<Integer,  Boolean> accesses){
		UsersDAO usersDAO =  new UsersDAO();
		if(usersDAO.updateAccesses(accesses)){
			return true;
		}
		else{
			return false;
		}
	}

}
