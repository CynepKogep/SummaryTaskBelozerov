package ua.kharkov.khpi.belozerov.db.entity;

public class PaysBeans {

	// table pays
	private Long   id;                //1 (pays.id)
	private Long   numbers;           //2 (pays.numbers)
	private String datas;             //3 (pays.datas)
	private Long   sums;              //4 (pays.sums)
	private Long   userId;            //5 (pays.user_id)
	private Long   creditAccountId;   //6 (pays.credit_account_id)
	private Long   statusPayId;       //7 (pays.status_pay_id)
	// table credit_account 
	private String creditAccountName; //8 (credit_account.name)
	// table status_pay
	private String nameStatusPay;     //9 (status_pay.name_status_pay)
	// table users
	private String firstName;         //10 (users.first_name)
	private String lastName;          //11 (users.last_name)
	private String firstNameRu;       //12 (users.first_name_ru)
	private String lastNameRu;        //13 (users.last_name_ru)
	
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
	public String getDatas() {
		return datas;
	}
	public void setDatas(String datas) {
		this.datas = datas;
	}
	public Long getSums() {
		return sums;
	}
	public void setSums(Long sums) {
		this.sums = sums;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCreditAccountId() {
		return creditAccountId;
	}
	public void setCreditAccountId(Long creditAccountId) {
		this.creditAccountId = creditAccountId;
	}
	public Long getStatusPayId() {
		return statusPayId;
	}
	public void setStatusPayId(Long statusPayId) {
		this.statusPayId = statusPayId;
	}
	public String getCreditAccountName() {
		return creditAccountName;
	}
	public void setCreditAccountName(String creditAccountName) {
		this.creditAccountName = creditAccountName;
	}
	public String getNameStatusPay() {
		return nameStatusPay;
	}
	public void setNameStatusPay(String nameStatusPay) {
		this.nameStatusPay = nameStatusPay;
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
	@Override
	public String toString() {
		return "PaysBeans [id=" + id + ", numbers=" + numbers + ", datas=" + datas + ", sums=" + sums + ", userId="
				+ userId + ", creditAccountId=" + creditAccountId + ", statusPayId=" + statusPayId
				+ ", creditAccountName=" + creditAccountName + ", nameStatusPay=" + nameStatusPay + ", firstName="
				+ firstName + ", lastName=" + lastName + ", firstNameRu=" + firstNameRu + ", lastNameRu=" + lastNameRu
				+ "]";
	}

	
}
