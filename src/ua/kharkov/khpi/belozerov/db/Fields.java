package ua.kharkov.khpi.belozerov.db;

/**
 * Holder for fields names of DB tables and beans.
 * 
 * @author D.Kolesnikov
 * 
 */
public final class Fields {
	
	// entities
	public static final String ENTITY__ID = "id";                          //1     // pays 1 
	
	// user
	public static final String USER__LOGIN = "login";                      //2
	public static final String USER__PASSWORD = "password";                //3
	public static final String USER__FIRST_NAME = "first_name";            //4     // pays 10
	public static final String USER__LAST_NAME = "last_name";              //5     // pays 11
	public static final String USER__FIRST_NAME_RU = "first_name_ru";      //6     // pays 12 
	public static final String USER__LAST_NAME_RU = "last_name_ru";        //7     // pays 13 
	public static final String USER__ROLE_ID = "role_id";                  //8
	public static final String ACCESSES_USERS_ID = "accesses_users_id";    //9
	
	public static final String ROLES_NAME = "name_roles";                  //10
	public static final String ACCESSES_USERS_NAME = "name_accesses_users";//11
	
	
	public static final String CARDS__NUMBERS = "numbers";
	public static final String CARDS__NAME = "name";                               // pays 8  
	public static final String CARDS__BALANCE = "balance";
	public static final String CARDS__ACCESSES_ACCOUNT_ID = "accesses_accounts_id";
	public static final String CARDS__USER_ID = "user_id";
	
	public static final String ACCESSES_ACCOUNTS_NAME = "name_accesses_accounts";
	public static final String UNLOCK_REQUEST_NAME = "name_unlock_request";
	
	// pays
	public static final String PAYS__NUMBERS = "numbers";                     //2
	public static final String PAYS__DATAS = "datas";                         //3
	public static final String PAYS__SUMS = "sums";                           //4
	public static final String PAYS__USER_ID = "user_id";                     //5 
	public static final String PAYS__CREDIT_ACCOUNT_ID = "credit_account_id"; //6
	public static final String PAYS__STATUS_PAY_ID = "status_pay_id";         //7
	
	public static final String PAYS__NAME_STATUS_PAY = "name_status_pay";     //9
	
	
	
	
	
	
	
	
	
	
			
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static final String ORDER__BILL = "bill";
	public static final String ORDER__USER_ID = "user_id";
	public static final String ORDER__STATUS_ID= "status_id";

	public static final String CATEGORY__NAME = "name";
	
	public static final String MENU_ITEM__PRICE = "price";
	public static final String MENU_ITEM__NAME = "name";
	public static final String MENU_ITEM__CATEGORY_ID = "category_id";	

	// beans
	public static final String USER_ORDER_BEAN__ORDER_ID = "id";	
	public static final String USER_ORDER_BEAN__USER_FIRST_NAME = "first_name";	
	public static final String USER_ORDER_BEAN__USER_LAST_NAME = "last_name";	
	public static final String USER_ORDER_BEAN__ORDER_BILL = "bill";	
	public static final String USER_ORDER_BEAN__STATUS_NAME = "name";

	
	
	
	
	// ---------------------------------------------------------
	public static final String USER__LOCALE_NAME = "locale_name";
	
	
}