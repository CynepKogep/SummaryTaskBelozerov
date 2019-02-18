package ua.kharkov.khpi.belozerov.db.entity;

public class CardBeans 
{
	// table credit_account
	private Long   id;
	private Long   numbers; 
	private String name;
	private Long   balance;
	private Long   accessesAccountsId;
	private Long   userId;  
	// table users
	private String firstName;
	private String lastName;
	private String firstNameRu;
	private String lastNameRu;
	// table accesses_accounts
	private String accessesAccounts;
	// table lock_request
	private String nameUnlockRequest;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNumbers() {
		return numbers;
	}
	public void setNumbers(Long numbers) {
		this.numbers = numbers;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public Long getAccessesAccountsId() {
		return accessesAccountsId;
	}
	public void setAccessesAccountsId(Long accessesAccountsId) {
		this.accessesAccountsId = accessesAccountsId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getAccessesAccounts() {
		return accessesAccounts;
	}
	public void setAccessesAccounts(String accessesAccounts) {
		this.accessesAccounts = accessesAccounts;
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
//	@Override
//	public String toString() {
//		return "CardBeans [id=" + id + ", numbers=" + numbers + ", name=" + name + ", balance=" + balance
//				+ ", accessesAccountsId=" + accessesAccountsId + ", userId=" + userId + ", accessesAccounts="
//				+ accessesAccounts + ", firstName=" + firstName + ", lastName=" + lastName + ", firstNameRu="
//				+ firstNameRu + ", lastNameRu=" + lastNameRu + "]";
//	}
	public String getNameUnlockRequest() {
		return nameUnlockRequest;
	}
	public void setNameUnlockRequest(String nameUnlockRequest) {
		this.nameUnlockRequest = nameUnlockRequest;
	}
	
	@Override
	public String toString() {
		return "CardBeans [id=" + id + ", numbers=" + numbers + ", name=" + name + ", balance=" + balance
				+ ", accessesAccountsId=" + accessesAccountsId + ", userId=" + userId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", firstNameRu=" + firstNameRu + ", lastNameRu=" + lastNameRu
				+ ", accessesAccounts=" + accessesAccounts + ", nameUnlockRequest=" + nameUnlockRequest + "]";
	}
	

	
}
