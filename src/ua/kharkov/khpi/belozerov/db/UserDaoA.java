package ua.kharkov.khpi.belozerov.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import ua.kharkov.khpi.belozerov.db.OrderDao.OrderMapper;
import ua.kharkov.khpi.belozerov.db.entity.Order;
import ua.kharkov.khpi.belozerov.db.entity.UserA;

/**
 * Data access object for User entity.
 */
public class UserDaoA {

    private static final String SQL__FIND_USER_BY_LOGIN =
            "SELECT * FROM users WHERE login=?";

    private static final String SQL__FIND_USER_ALL =
            "SELECT * FROM users";
    
    private static final String SQL__FIND_USER_BY_ID =
            "SELECT * FROM users WHERE id=?";

    private static final String SQL_UPDATE_USER =
            "UPDATE users SET password=?, first_name=?, last_name=?, first_name_ru=?, last_name_ru=?"+
                    "	WHERE id=?";

    /**
     * Returns a user with the given identifier.
     *
     * @param id
     *            User identifier.
     * @return User entity.
     */
    public UserA findUser(Long id) {
        UserA user = null;
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
    public UserA findUserByLogin(String login) {
        UserA user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(SQL__FIND_USER_BY_LOGIN);
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

    public List<UserA> findUserAll() {
        List<UserA> usersAList = new ArrayList<UserA>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_USER_ALL);
            while (rs.next())
            	usersAList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return usersAList;
    }
    

    public List<UserA> getUsers() {
        List<UserA> usersAList = new ArrayList<UserA>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_USER_ALL);
            while (rs.next())
            	usersAList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return usersAList;
    }

    
    
    
    
    /**
     * Update user.
     *
     * @param user
     *            user to update.
     */
    public void updateUser(UserA user) {
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
    public void updateUser(Connection con, UserA user) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_USER);
        int k = 1;
        pstmt.setString(k++, user.getPassword());
        pstmt.setString(k++, user.getFirstName());
        pstmt.setString(k++, user.getLastName());
        pstmt.setString(k++, user.getFirstNameRu());
        pstmt.setString(k++, user.getLastNameRu());
        pstmt.setLong(k, user.getId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    /**
     * Extracts a user from the result set row.
     */
    private static class UserMapper implements EntityMapper<UserA> {

        @Override
        public UserA mapRow(ResultSet rs) {
            try {
                UserA user = new UserA();
                user.setId(rs.getLong(Fields.ENTITY__ID));                      //1
                user.setLogin(rs.getString(Fields.USER__LOGIN));                //2
                user.setPassword(rs.getString(Fields.USER__PASSWORD));          //3
                user.setFirstName(rs.getString(Fields.USER__FIRST_NAME));       //4
                user.setLastName(rs.getString(Fields.USER__LAST_NAME));         //5
                user.setFirstNameRu(rs.getString(Fields.USER__FIRST_NAME_RU));  //6
                user.setLastNameRu(rs.getString(Fields.USER__LAST_NAME_RU));    //7
                user.setRoleId(rs.getInt(Fields.USER__ROLE_ID));                //8
                user.setAccessesUsersId(rs.getInt(Fields.ACCESSES_USERS_ID));   //9
                return user;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
