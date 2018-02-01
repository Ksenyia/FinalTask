package by.tr.web.service;

import java.util.HashMap;
import java.util.List;

import by.tr.web.dao.UsersDAO;
import by.tr.web.entity.User;

public class UsersService {
	
	public List<User> getUsers(int pageNumber) {
        UsersDAO usersDAO =  new UsersDAO();
		int number = 0;
		if(pageNumber==0){
			number = 0;
		}
		else{
			number = (pageNumber-1)*5;
		}
        List<User> users =  usersDAO.getUsers(number);
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

	public int getPageCount() {
		UsersDAO usersDAO = new UsersDAO();
		int entryCount = usersDAO.getEntryCount();
		double convertIntToDouble = 1.0;
		int pageCount = (int) Math.ceil( entryCount* convertIntToDouble/5);
		return pageCount;
	}

}
