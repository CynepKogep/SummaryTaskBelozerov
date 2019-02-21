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


import ua.kharkov.khpi.belozerov.db.entity.User;
import ua.kharkov.khpi.belozerov.db.entity.UserFull;
import ua.kharkov.khpi.belozerov.db.entity.UserFullWithBlockCard;


/**
 * Data access object for User entity.
 */
public class UserFullInfoDao {

    private static final String SQL__FIND_USER_ALL =
            "SELECT users.id, users.login, users.password, users.first_name, " 
          + "users.last_name, users.first_name_ru, users.last_name_ru, users.role_id,"
          + " users.accesses_users_id, roles.name_roles, accesses_users.name_accesses_users "             		
          + "FROM users, roles, accesses_users " 
          + "WHERE users.role_id = roles.id AND users.accesses_users_id = accesses_users.id;";
    
    private static final String SQL__FIND_USER_ALL_BLOCK_COUNT =
    "SELECT users.id, users.login, users.password, users.first_name, " +  
    " users.last_name, users.first_name_ru, users.last_name_ru, users.role_id, " +
    " users.accesses_users_id, roles.name_roles, accesses_users.name_accesses_users, " + 
    "( SELECT COUNT(1) FROM credit_account CA WHERE users.id = CA.user_id AND accesses_accounts_id = 1 ) blockCount " +  
    "FROM users, roles, accesses_users " + 
    " WHERE users.role_id = roles.id AND users.accesses_users_id = accesses_users.id; " ;
 
    private static final String SQL_UPDATE_USER_ACCESSES_USERS =
            "UPDATE users SET accesses_users_id=? "+
                    " WHERE id=?";
    
//    private static final String SQL_UPDATE_USER_ACCESSES_USERS =
//            "UPDATE users SET accesses_users_id=? "+
//                    " WHERE id=?";
    
    
	private static final String SQL__FIND_USER_BY_LOGIN =
            // "SELECT * FROM users WHERE login=?";
    "SELECT users.id, users.login, users.password, users.first_name, " 
  + "users.last_name, users.first_name_ru, users.last_name_ru, users.role_id,"
  + " users.accesses_users_id, roles.name_roles, accesses_users.name_accesses_users "             		
  + "FROM users, roles, accesses_users " 
  + "WHERE login=? AND users.role_id = roles.id AND users.accesses_users_id = accesses_users.id;";
	private static final String SQL__FIND_USER_BY_LOGIN__BLOCK_COUNT =
            // "SELECT * FROM users WHERE login=?";
    "SELECT users.id, users.login, users.password, users.first_name, " 
  + "users.last_name, users.first_name_ru, users.last_name_ru, users.role_id,"
  + " users.accesses_users_id, roles.name_roles, accesses_users.name_accesses_users " 
  + "( SELECT COUNT(1) FROM credit_account CA WHERE users.id = CA.user_id AND accesses_accounts_id = 1 ) blockCount "
  + "FROM users, roles, accesses_users " 
  + "WHERE login=? AND users.role_id = roles.id AND users.accesses_users_id = accesses_users.id;";
	
	
	
    private static final String SQL__FIND_USER_BY_ID =
            "SELECT * FROM users, roles  WHERE id=?";

    
    private static final String SQL_UPDATE_USER =
            "UPDATE users SET password=?, first_name=?, last_name=?, locale_name=?"+
                    "	WHERE id=?";

    /**
     * Returns a user with the given identifier.
     *
     * @param id
     *            User identifier.
     * @return User entity.
     */
    public UserFull findUser(Long id) {
    	UserFull user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(SQL__FIND_USER_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return user;
    }


    
    /**
     * Returns a user with the given login.
     *
     * @param login
     *            User login.
     * @return User entity.
     */
    public UserFull findUserByLogin(String login) {
    	UserFull user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(SQL__FIND_USER_BY_LOGIN);
            //pstmt = con.prepareStatement(SQL__FIND_USER_BY_LOGIN__BLOCK_COUNT);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return user;
    }

    /**
     * Update user.
     *
     * @param user
     *            user to update.
     */
    public void updateUser(User user) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            updateUser(con, user);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    // //////////////////////////////////////////////////////////
    // Entity access methods (for transactions)
    // //////////////////////////////////////////////////////////

    /**
     * Update user.
     *
     * @param user
     *            user to update.
     * @throws SQLException
     */
    public void updateUser(Connection con, User user) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_USER);
        int k = 1;
        pstmt.setString(k++, user.getPassword());
        pstmt.setString(k++, user.getFirstName());
        pstmt.setString(k++, user.getLastName());
        pstmt.setString(k++, user.getLocaleName());
        pstmt.setLong(k, user.getId());
        pstmt.executeUpdate();
        pstmt.close();
    }



    /**
     * Extracts a user from the result set row.
     */
    private static class UserMapper implements EntityMapper<UserFull> 
    {
        @Override
        public UserFull mapRow(ResultSet rs) 
        {
            try 
            {
            	UserFull user = new UserFull();
                
            	user.setId(rs.getLong(Fields.ENTITY__ID));
                user.setLogin(rs.getString(Fields.USER__LOGIN));
                user.setPassword(rs.getString(Fields.USER__PASSWORD));
                user.setFirstName(rs.getString(Fields.USER__FIRST_NAME));
                user.setLastName(rs.getString(Fields.USER__LAST_NAME));
                user.setFirstNameRu(rs.getString(Fields.USER__FIRST_NAME_RU));
                user.setLastNameRu(rs.getString(Fields.USER__LAST_NAME_RU));
                user.setRoleId(rs.getInt(Fields.USER__ROLE_ID));
                user.setAccessesUsersId(rs.getInt(Fields.ACCESSES_USERS_ID));
                // Fields.ROLES_NAME = Fields.ACCESSES_USERS_NAME
                user.setRoles(rs.getString(Fields.ROLES_NAME));
                user.setAccessesUsers(rs.getString(Fields.ACCESSES_USERS_NAME));
                // user.setCountBlockCard(rs.getInt(11));
                return user;
            } 
            catch (SQLException e) 
            {
                throw new IllegalStateException(e);
            }
        }
    }
    
    private static class UserMapperWithBlockCard implements EntityMapper<UserFullWithBlockCard> 
    {
        @Override
        public UserFullWithBlockCard mapRow(ResultSet rs) 
        {
            try 
            {
            	UserFullWithBlockCard user = new UserFullWithBlockCard();
                
            	user.setId(rs.getLong(Fields.ENTITY__ID));
                user.setLogin(rs.getString(Fields.USER__LOGIN));
                user.setPassword(rs.getString(Fields.USER__PASSWORD));
                user.setFirstName(rs.getString(Fields.USER__FIRST_NAME));
                user.setLastName(rs.getString(Fields.USER__LAST_NAME));
                user.setFirstNameRu(rs.getString(Fields.USER__FIRST_NAME_RU));
                user.setLastNameRu(rs.getString(Fields.USER__LAST_NAME_RU));
                user.setRoleId(rs.getInt(Fields.USER__ROLE_ID));
                user.setAccessesUsersId(rs.getInt(Fields.ACCESSES_USERS_ID));
                // Fields.ROLES_NAME = Fields.ACCESSES_USERS_NAME
                user.setRoles(rs.getString(Fields.ROLES_NAME));
                user.setAccessesUsers(rs.getString(Fields.ACCESSES_USERS_NAME));
                user.setCountBlockCard(rs.getInt(Fields.ACCESSES_BLOCK_COUNT));
                return user;
            } 
            catch (SQLException e) 
            {
                throw new IllegalStateException(e);
            }
        }
    }
    
    
    public List<UserFullWithBlockCard> getUsersWithBlockCard() 
    {
        List<UserFullWithBlockCard> UserFullList = new ArrayList<UserFullWithBlockCard>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapperWithBlockCard mapper = new UserMapperWithBlockCard();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_USER_ALL_BLOCK_COUNT);
            // rs = stmt.executeQuery(SQL__FIND_USER_ALL_BLOCK_COUNT);
            while (rs.next())
//            	  System.out.println(rs.getString(1));
//                  System.out.println(rs.getString(2));
//                  System.out.println(rs.getString(3));
//                  System.out.println(rs.getString(4));
//                  System.out.println(rs.getString(5));
//                  System.out.println(rs.getString(6));
//                  System.out.println(rs.getString(7));
//                  System.out.println(rs.getString(8));
//                  System.out.println(rs.getString(9));
//                  System.out.println(rs.getString(10));
//                  System.out.println(rs.getString(11));
            
            	 UserFullList.add(mapper.mapRow(rs));
            
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return UserFullList;
    }
    
    public List<UserFull> getUsers() 
    {
        List<UserFull> UserFullList = new ArrayList<UserFull>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_USER_ALL);
            // rs = stmt.executeQuery(SQL__FIND_USER_ALL_BLOCK_COUNT);
            while (rs.next())
//            	  System.out.println(rs.getString(1));
//                  System.out.println(rs.getString(2));
//                  System.out.println(rs.getString(3));
//                  System.out.println(rs.getString(4));
//                  System.out.println(rs.getString(5));
//                  System.out.println(rs.getString(6));
//                  System.out.println(rs.getString(7));
//                  System.out.println(rs.getString(8));
//                  System.out.println(rs.getString(9));
//                  System.out.println(rs.getString(10));
//                  System.out.println(rs.getString(11));
            
            	 UserFullList.add(mapper.mapRow(rs));
            
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return UserFullList;
    }   
    
    
    
    
    
    
    
    public int getBlockCard(int id_user) 
    {
    	String a = "" + id_user;
    	String requst = 
        		"SELECT count(credit_account.accesses_accounts_id)" + 
        		"FROM users, credit_account " +
        		"WHERE users.id = " +  a  + " AND users.id = credit_account.user_id AND credit_account.accesses_accounts_id = 1;";
        String count = "";		
    	
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(requst);
            rs.next();
            count += rs.getString(1);
           System.out.printf("count = " + count);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return  Integer.parseInt(count);
    }

    
    
    
    
    public void updateUserAccessesUsers(String id_user, String accesses_users) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            updateUserAccessesUsersForPreparedStatement(con, id_user, accesses_users);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }
    
    private void updateUserAccessesUsersForPreparedStatement(Connection con, String id_user, String accesses_users) throws SQLException {
        
    	String sql_requst = "UPDATE users SET accesses_users_id=" + accesses_users + " WHERE id=" + id_user + ";";
    	Statement stmt = con.createStatement(); 
    	stmt.execute(sql_requst);		
    	stmt.close();
    	
//        PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_USER_ACCESSES_USERS);
//        pstmt.setString(1, accesses_users);
//        pstmt.setString(2, id_user);
//        pstmt.executeUpdate();
//        pstmt.close();
    }
    
    // --------------------------------------------------------------------

    public void insertUser(String login, String password, String first_name, String last_name, String first_name_ru, String last_name_ru) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            insertUser(con, login, password, first_name, last_name, first_name_ru, last_name_ru);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }
    
    private void insertUser(Connection con, String login, String password, String first_name, String last_name, String first_name_ru, String last_name_ru) throws SQLException {
        String sql_requst =  "INSERT INTO users VALUES(DEFAULT, '" + login + "','" + password +
    	"', '" +  first_name + "', '" + last_name + "', '" + first_name_ru + "', '"+ last_name_ru +
    	"', 1, 0);";
    	Statement stmt = con.createStatement(); 
    	stmt.executeUpdate(sql_requst);		
    	stmt.close();
    }
 // --------------------------------------------------------------------
    
    
    
}

