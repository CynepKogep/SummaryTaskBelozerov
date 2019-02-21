package ua.kharkov.khpi.belozerov.db.entity;

public class UserFullWithBlockCard extends Entity {

	private static final long serialVersionUID = -6889036256149495888L;

	// private int    id; // Entity
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String firstNameRu;
	private String lastNameRu;
	private int    roleId;
	private int    accessesUsersId;
	private String roles;	
	private String accessesUsers;
	private int    countBlockCard; 
	
	
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstNameRu() {
		return firstNameRu;
	}

	public void setFirstNameRu(String firstNameRu) {
		this.firstNameRu = firstNameRu;
	}

	public String getLastNameRu() {
		return lastNameRu;
	}

	public void setLastNameRu(String lastNameRu) {
		this.lastNameRu = lastNameRu;
	}
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "User [id =" + getId() + ", login=" + login + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", firstNameRu=" + firstNameRu + ", lastNameRu=" + lastNameRu
				+", accessesUsersId=" + accessesUsersId + ", roleId=" + roleId 
				+ ", roles=" + roles +  ", accessesUsers=" + accessesUsers +   "]";
	}

	public int getAccessesUsersId() {
		return accessesUsersId;
	}

	public void setAccessesUsersId(int accessesUsersId) {
		this.accessesUsersId = accessesUsersId;
	}


	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getAccessesUsers() {
		return accessesUsers;
	}

	public void setAccessesUsers(String accessesUsers) {
		this.accessesUsers = accessesUsers;
	}

	public int getCountBlockCard() {
		return countBlockCard;
	}

	public void setCountBlockCard(int countBlockCard) {
		this.countBlockCard = countBlockCard;
	}

}
