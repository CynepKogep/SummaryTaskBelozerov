package ua.kharkov.khpi.belozerov.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import ua.kharkov.khpi.belozerov.db.entity.CardBeans;

public class CardBeansDao 
{
    private static final String SQL__FIND_CARDS_ALL =
    		"SELECT credit_account.id, credit_account.numbers, credit_account.name, credit_account.balance, " +  
    	    "credit_account.accesses_accounts_id, credit_account.user_id, " + 
    	    "users.first_name, users.last_name, users.first_name_ru, users.last_name_ru," + 
    	    "accesses_accounts.name_accesses_accounts, unlock_request.name_unlock_request "+              		
    	    "FROM credit_account, users, accesses_accounts, unlock_request " +  
    	    "WHERE credit_account.user_id = users.id AND credit_account.accesses_accounts_id = accesses_accounts.id " +
    	    "AND credit_account.unlock_request_id = unlock_request.id;";  
	
    private static class CardBeansMapper implements EntityMapper<CardBeans> 
    {
        @Override
        public CardBeans mapRow(ResultSet rs) 
        {
            try 
            {
            	CardBeans card = new CardBeans();
                
            	// table credit_account
            	card.setId(rs.getLong(Fields.ENTITY__ID));
            	card.setNumbers(rs.getLong(Fields.CARDS__NUMBERS));
            	card.setName(rs.getString(Fields.CARDS__NAME));
            	card.setBalance(rs.getLong(Fields.CARDS__BALANCE));
            	card.setAccessesAccountsId(rs.getLong(Fields.CARDS__ACCESSES_ACCOUNT_ID));
            	card.setUserId(rs.getLong(Fields.CARDS__USER_ID));
            	// table users
            	card.setFirstName(rs.getString(Fields.USER__FIRST_NAME));
            	card.setLastName(rs.getString(Fields.USER__LAST_NAME));
            	card.setFirstNameRu(rs.getString(Fields.USER__FIRST_NAME_RU));
            	card.setLastNameRu(rs.getString(Fields.USER__LAST_NAME_RU));
                // table accesses_accounts
            	card.setAccessesAccounts(rs.getString(Fields.ACCESSES_ACCOUNTS_NAME));
            	// table ock_request
            	card.setNameUnlockRequest(rs.getString(Fields.UNLOCK_REQUEST_NAME));
                return card;
            } 
            catch (SQLException e) 
            {
                throw new IllegalStateException(e);
            }
        }
    }

    public List<CardBeans> getCards() 
    {
        List<CardBeans> CardsList = new ArrayList<CardBeans>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            CardBeansMapper mapper = new CardBeansMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_CARDS_ALL);
            while (rs.next())
            	CardsList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return CardsList;
    }
    
    // --------------------------------------------------------------------
    public void updateCardsAccesses(String id_card, String accesses_card) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            updateCardsAccesses(con, id_card, accesses_card);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }
    
    private void updateCardsAccesses(Connection con, String id_card, String accesses_card) throws SQLException {
        
    	String sql_requst = "UPDATE credit_account SET accesses_accounts_id=" + accesses_card + " WHERE id=" + id_card + ";";
    	Statement stmt = con.createStatement(); 
    	stmt.execute(sql_requst);		
    	stmt.close();
    }
 // --------------------------------------------------------------------
    public void updateCardsUnlockRequest(String id_card, String unlock_request_card) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            updateCardsUnlockRequest(con, id_card, unlock_request_card);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }
    
    private void updateCardsUnlockRequest(Connection con, String id_card, String unlock_request_card) throws SQLException {
        
    	String sql_requst = "UPDATE credit_account SET unlock_request_id=" + unlock_request_card + " WHERE id=" + id_card + ";";
    	Statement stmt = con.createStatement(); 
    	stmt.execute(sql_requst);		
    	stmt.close();
    }
 // --------------------------------------------------------------------
    public List<CardBeans> getCardsClientSortingNumber(Long user_id) 
    {
 
        String SQL__FIND_CARDS_CLIENT_SORTING_NUMBER   =  		
        		"SELECT credit_account.id, credit_account.numbers, credit_account.name, credit_account.balance, " +  
        	    "credit_account.accesses_accounts_id, credit_account.user_id, " + 
        	    "users.first_name, users.last_name, users.first_name_ru, users.last_name_ru," + 
        	    "accesses_accounts.name_accesses_accounts, unlock_request.name_unlock_request "+              		
        	    "FROM credit_account, users, accesses_accounts, unlock_request " +  
        	    "WHERE users.id = " + user_id +  " AND credit_account.user_id = users.id AND credit_account.accesses_accounts_id = accesses_accounts.id " +
        	    "AND credit_account.unlock_request_id = unlock_request.id ORDER BY credit_account.numbers;";  
    	
    	List<CardBeans> CardsList = new ArrayList<CardBeans>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            CardBeansMapper mapper = new CardBeansMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_CARDS_CLIENT_SORTING_NUMBER);
            while (rs.next())
            	CardsList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return CardsList;
    }
    // --------------------------------------------------------------------
    public List<CardBeans> getCardsClientSortingName(Long user_id) 
    {
 
        String SQL__FIND_CARDS_CLIENT_SORTING_NUMBER   =  		
        		"SELECT credit_account.id, credit_account.numbers, credit_account.name, credit_account.balance, " +  
        	    "credit_account.accesses_accounts_id, credit_account.user_id, " + 
        	    "users.first_name, users.last_name, users.first_name_ru, users.last_name_ru," + 
        	    "accesses_accounts.name_accesses_accounts, unlock_request.name_unlock_request "+              		
        	    "FROM credit_account, users, accesses_accounts, unlock_request " +  
        	    "WHERE users.id = " + user_id +  " AND credit_account.user_id = users.id AND credit_account.accesses_accounts_id = accesses_accounts.id " +
        	    "AND credit_account.unlock_request_id = unlock_request.id ORDER BY credit_account.name;";  
    	
    	List<CardBeans> CardsList = new ArrayList<CardBeans>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            CardBeansMapper mapper = new CardBeansMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_CARDS_CLIENT_SORTING_NUMBER);
            while (rs.next())
            	CardsList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return CardsList;
    }
    // --------------------------------------------------------------------
    public List<CardBeans> getCardsClientSortingBalance(Long user_id) 
    {
 
        String SQL__FIND_CARDS_CLIENT_SORTING_NUMBER   =  		
        		"SELECT credit_account.id, credit_account.numbers, credit_account.name, credit_account.balance, " +  
        	    "credit_account.accesses_accounts_id, credit_account.user_id, " + 
        	    "users.first_name, users.last_name, users.first_name_ru, users.last_name_ru," + 
        	    "accesses_accounts.name_accesses_accounts, unlock_request.name_unlock_request "+              		
        	    "FROM credit_account, users, accesses_accounts, unlock_request " +  
        	    "WHERE users.id = " + user_id +  " AND credit_account.user_id = users.id AND credit_account.accesses_accounts_id = accesses_accounts.id " +
        	    "AND credit_account.unlock_request_id = unlock_request.id ORDER BY credit_account.balance;";  
    	
    	List<CardBeans> CardsList = new ArrayList<CardBeans>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            CardBeansMapper mapper = new CardBeansMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_CARDS_CLIENT_SORTING_NUMBER);
            while (rs.next())
            	CardsList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return CardsList;
    }
    // -----------------------------------------------------------------------------------
    public List<CardBeans> getCardsClientSortingNumber(Long user_id, String credit_account_id) 
    {
 
        String SQL__FIND_CARDS_CLIENT_SORTING_NUMBER   =  		
        		"SELECT credit_account.id, credit_account.numbers, credit_account.name, credit_account.balance, " +  
        	    "credit_account.accesses_accounts_id, credit_account.user_id, " + 
        	    "users.first_name, users.last_name, users.first_name_ru, users.last_name_ru," + 
        	    "accesses_accounts.name_accesses_accounts, unlock_request.name_unlock_request "+              		
        	    "FROM credit_account, users, accesses_accounts, unlock_request " +  
        	    "WHERE users.id = " + user_id + " AND credit_account.id = " + credit_account_id + " AND credit_account.user_id = users.id AND credit_account.accesses_accounts_id = accesses_accounts.id " +
        	    "AND credit_account.unlock_request_id = unlock_request.id ORDER BY credit_account.numbers;";  
    	
    	List<CardBeans> CardsList = new ArrayList<CardBeans>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            CardBeansMapper mapper = new CardBeansMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_CARDS_CLIENT_SORTING_NUMBER);
            while (rs.next())
            	CardsList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return CardsList;
    }
    
    // --------------------------------------------------------------------
    public void updateCardsBalance(Long id_card, Long balance) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            updateCardsBalance(con, id_card, balance);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }
    
    private void updateCardsBalance(Connection con, Long id_card, Long balance) throws SQLException {
        
    	String sql_requst = "UPDATE credit_account SET balance=" + balance + " WHERE id=" + id_card + ";";
    	Statement stmt = con.createStatement(); 
    	stmt.execute(sql_requst);		
    	stmt.close();
    }
 // --------------------------------------------------------------------
    public List<CardBeans> getCardsClientForCreate(Long user_id) 
    {
        String SQL__FIND_CARDS_CLIENT   =  		
        		"SELECT credit_account.id, credit_account.numbers, credit_account.name, credit_account.balance, " +  
        	    "credit_account.accesses_accounts_id, credit_account.user_id, " + 
        	    "users.first_name, users.last_name, users.first_name_ru, users.last_name_ru," + 
        	    "accesses_accounts.name_accesses_accounts, unlock_request.name_unlock_request "+              		
        	    "FROM credit_account, users, accesses_accounts, unlock_request " +  
        	    "WHERE users.id = " + user_id +  " AND credit_account.user_id = users.id AND credit_account.accesses_accounts_id = accesses_accounts.id " +
        	    "AND credit_account.unlock_request_id = unlock_request.id AND credit_account.accesses_accounts_id = 0; ";  
    	
    	List<CardBeans> CardsList = new ArrayList<CardBeans>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            CardBeansMapper mapper = new CardBeansMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_CARDS_CLIENT);
            while (rs.next())
            	CardsList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return CardsList;
    }

    // -----------------------------------------------------------------------------------
    public void insertCard(String number_card, String name_card, String balance_card, String users_to_set) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            insertCard(con, number_card, name_card, balance_card, users_to_set);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }
    
    private void insertCard(Connection con, String number_card, String name_card, String balance_card, String users_to_set) throws SQLException {
        String sql_requst =  "INSERT INTO credit_account VALUES(DEFAULT," + number_card + ",'" + name_card +
    	"', " +  balance_card + ", 0, " + users_to_set + ", 0);";
    	Statement stmt = con.createStatement(); 
    	stmt.executeUpdate(sql_requst);		
    	stmt.close();
    }
 // --------------------------------------------------------------------

    
    
}
