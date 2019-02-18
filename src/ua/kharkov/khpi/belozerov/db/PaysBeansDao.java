package ua.kharkov.khpi.belozerov.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ua.kharkov.khpi.belozerov.db.entity.PaysBeans;

public class PaysBeansDao {
	
    private static final String SQL__FIND_PAYS_ALL =
    		"SELECT credit_account.id, credit_account.numbers, credit_account.name, credit_account.balance, " +  
    	    "credit_account.accesses_accounts_id, credit_account.user_id, " + 
    	    "users.first_name, users.last_name, users.first_name_ru, users.last_name_ru," + 
    	    "accesses_accounts.name_accesses_accounts, unlock_request.name_unlock_request "+              		
    	    "FROM credit_account, users, accesses_accounts, unlock_request " +  
    	    "WHERE credit_account.user_id = users.id AND credit_account.accesses_accounts_id = accesses_accounts.id " +
    	    "AND credit_account.unlock_request_id = unlock_request.id;";  
	
	
	
    private static class PaysBeansMapper implements EntityMapper<PaysBeans> 
    {
        @Override
        public PaysBeans mapRow(ResultSet rs) 
        {
            try 
            {
            	PaysBeans pays = new PaysBeans();
                
            	// table credit_account
            	pays.setId(rs.getLong(Fields.ENTITY__ID));                                 // 1
            	pays.setNumbers(rs.getLong(Fields.PAYS__NUMBERS));                         // 2
            	pays.setDatas(rs.getString(Fields.PAYS__DATAS));                           // 3 
            	pays.setSums(rs.getLong(Fields.PAYS__SUMS));                               // 4 
            	pays.setUserId(rs.getLong(Fields.PAYS__USER_ID));                          // 5
            	pays.setCreditAccountId(rs.getLong(Fields.PAYS__CREDIT_ACCOUNT_ID));       // 6   
            	pays.setStatusPayId(rs.getLong(Fields.PAYS__STATUS_PAY_ID));               // 7
            	// table credit_account
            	pays.setCreditAccountName(rs.getString(Fields.CARDS__NAME));               // 8
            	// table status_pay
            	pays.setNameStatusPay(rs.getString(Fields.PAYS__NAME_STATUS_PAY));         // 9
            	// table users                
            	pays.setFirstName(rs.getString(Fields.USER__FIRST_NAME));                  // 10                  
            	pays.setLastName(rs.getString(Fields.USER__LAST_NAME));                    // 11
            	pays.setFirstNameRu(rs.getString(Fields.USER__FIRST_NAME_RU));             // 12
            	pays.setLastNameRu(rs.getString(Fields.USER__LAST_NAME_RU));               // 131
                return pays;
            } 
            catch (SQLException e) 
            {
                throw new IllegalStateException(e);
            }
        }
    }
    
    
    public List<PaysBeans> getPays(Long user_id) 
    {
        String SQL__FIND_CARDS_CLIENT_SORTING_NUMBER   =  		
        		//      1        2             3           4 
        		"SELECT pays.id, pays.numbers, pays.datas, pays.sums, " +
        		// 5           6   		
        	    "pays.user_id, pays.credit_account_id, " + 
        	    // 7                  8
        	    "pays.status_pay_id, credit_account.name, "
        	    //  9                            10                11               12                   13              
        	    + " status_pay.name_status_pay , users.first_name, users.last_name, users.first_name_ru, users.last_name_ru " + 
        	    "FROM pays, credit_account, status_pay, users " +  
        	    "WHERE users.id = " + user_id +  " AND pays.credit_account_id = credit_account.id AND pays.user_id = users.id " +
        	    "AND pays.status_pay_id = status_pay.id ORDER BY pays.numbers;";  
    	
    	List<PaysBeans> PaysList = new ArrayList<PaysBeans>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PaysBeansMapper mapper = new PaysBeansMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_CARDS_CLIENT_SORTING_NUMBER);
            while (rs.next())
            	PaysList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return PaysList;
    }
    
    public List<PaysBeans> getPaysSortDate(Long user_id) 
    {
        String SQL__FIND_CARDS_CLIENT_SORTING   =  		
        		//      1        2             3           4 
        		"SELECT pays.id, pays.numbers, pays.datas, pays.sums, " +
        		// 5           6   		
        	    "pays.user_id, pays.credit_account_id, " + 
        	    // 7                  8
        	    "pays.status_pay_id, credit_account.name, "
        	    //  9                            10                11               12                   13              
        	    + " status_pay.name_status_pay , users.first_name, users.last_name, users.first_name_ru, users.last_name_ru " + 
        	    "FROM pays, credit_account, status_pay, users " +  
        	    "WHERE users.id = " + user_id +  " AND pays.credit_account_id = credit_account.id AND pays.user_id = users.id " +
        	    "AND pays.status_pay_id = status_pay.id ORDER BY pays.datas;";  
    	
    	List<PaysBeans> PaysList = new ArrayList<PaysBeans>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PaysBeansMapper mapper = new PaysBeansMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_CARDS_CLIENT_SORTING);
            while (rs.next())
            	PaysList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return PaysList;
    }

    public List<PaysBeans> getPaysSortDateDesc(Long user_id) 
    {
        String SQL__FIND_CARDS_CLIENT_SORTING   =  		
        		//      1        2             3           4 
        		"SELECT pays.id, pays.numbers, pays.datas, pays.sums, " +
        		// 5           6   		
        	    "pays.user_id, pays.credit_account_id, " + 
        	    // 7                  8
        	    "pays.status_pay_id, credit_account.name, "
        	    //  9                            10                11               12                   13              
        	    + " status_pay.name_status_pay , users.first_name, users.last_name, users.first_name_ru, users.last_name_ru " + 
        	    "FROM pays, credit_account, status_pay, users " +  
        	    "WHERE users.id = " + user_id +  " AND pays.credit_account_id = credit_account.id AND pays.user_id = users.id " +
        	    "AND pays.status_pay_id = status_pay.id ORDER BY pays.datas DESC;";  
    	
    	List<PaysBeans> PaysList = new ArrayList<PaysBeans>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PaysBeansMapper mapper = new PaysBeansMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_CARDS_CLIENT_SORTING);
            while (rs.next())
            	PaysList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return PaysList;
    }
    
    // --------------------------------------------------------------------
    public void updatePaysStatusPay(Long id_pays, String status_pay_id) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            updateCardsBalance(con, id_pays, status_pay_id);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }
    
    private void updateCardsBalance(Connection con, Long id_pays, String status_pay_id) throws SQLException {
        
    	String sql_requst = "UPDATE pays SET status_pay_id=" + status_pay_id + " WHERE id=" + id_pays + ";";
    	Statement stmt = con.createStatement(); 
    	stmt.execute(sql_requst);		
    	stmt.close();
    }
 // --------------------------------------------------------------------

    public void insertPay(String number_pay, String sum_pay, String user_id, String card_to_set) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            insertPay(con, number_pay, sum_pay, user_id, card_to_set);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }
    
    private void insertPay(Connection con, String number_pay, String sum_pay, String user_id, String card_to_set) throws SQLException {
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String dateWithoutTime =  year + "-" + month + "-" + day; 

        String sql_requst =  "INSERT INTO pays VALUES(DEFAULT," + number_pay + ",'" + dateWithoutTime +
    	"', " +  sum_pay + ", " + user_id + ", " + card_to_set + ", 0);";
    	Statement stmt = con.createStatement(); 
    	stmt.executeUpdate(sql_requst);		
    	stmt.close();
    }
 // --------------------------------------------------------------------
}
