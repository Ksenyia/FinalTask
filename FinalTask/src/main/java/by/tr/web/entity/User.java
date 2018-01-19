package by.tr.web.entity;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String login;
	private String password; 
	private String email;
	private boolean adminFlag;
	private boolean accessFlag;
	private int status;
	private String surname;
	private String name;
	
	public User() {
		super();
	}
	public User(int id, String login, String email, int status, boolean adminFlag, boolean accessFlag) {
		this.setId(id);
		this.setLogin(login);
		this.setEmail(email);
		this.setStatus(status);
		this.setAdminFlag(adminFlag);
		this.setAccessFlag(accessFlag);
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAdminFlag() {
		return adminFlag;
	}
	public void setAdminFlag(boolean adminFlag) {
		this.adminFlag = adminFlag;
	}
	public boolean isAccessFlag() {
		return accessFlag;
	}
	public void setAccessFlag(boolean accessFlag) {
		this.accessFlag = accessFlag;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", email=" + email + ", adminFlag="
				+ adminFlag + ", accessFlag=" + accessFlag + ", status=" + status + ", surname=" + surname + ", name="
				+ name + "]";
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (accessFlag ? 1231 : 1237);
		result = prime * result + (adminFlag ? 1231 : 1237);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + status;
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (accessFlag != other.accessFlag)
			return false;
		if (adminFlag != other.adminFlag)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (status != other.status)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
	
}
