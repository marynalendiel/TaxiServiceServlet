package com.taxiservice.model.dao;

import com.taxiservice.model.DBManager;
import com.taxiservice.model.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;

import static com.taxiservice.model.dao.SqlQuery.*;

/**
 * Data access object for User entity.
 *
 * @author Maryna Lendiel
 */
public class UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class);

    /**
     * Method that inserts new user into database.
     *
     * @param user user that is inserted into DB.
     * @return true if success.
     */
    public boolean insertUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhoneNumber());
            preparedStatement.setBoolean(7, user.getDiscount());

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            DBManager.getInstance().commitAndClose(connection);
        }
        catch (SQLException e) {
            LOGGER.error("Cannot insert new user.", e);
            DBManager.getInstance().rollbackAndClose(connection);
            return false;
        }
        finally {
            DBManager.getInstance().close(preparedStatement);
            DBManager.getInstance().close(resultSet);
        }
        return true;
    }

    /**
     * Method that helps to get user by its login.
     *
     * @param userLogin login of user that we want to get.
     * @return User object
     */
    public User getUser(String userLogin) {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_USER);
            preparedStatement.setString(1, userLogin);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user =User.createUser();

                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setFirstName(resultSet.getString(4));
                user.setLastName(resultSet.getString(5));
                user.setEmail(resultSet.getString(6));
                user.setPhoneNumber(resultSet.getString(7));
                user.setRole(resultSet.getBoolean(8));
                user.setDiscount(resultSet.getBoolean(9));
                user.setCreateTime(resultSet.getDate(10));
            }
            DBManager.getInstance().commitAndClose(connection);
        }
        catch (SQLException e) {
            LOGGER.error("Cannot insert new user.", e);
            DBManager.getInstance().rollbackAndClose(connection);
        }
        finally {
            DBManager.getInstance().close(preparedStatement);
            DBManager.getInstance().close(resultSet);
        }

        return user;
    }

    /**
     * Method updates user's discount after order.
     *
     * @param user user which discount is updating.
     */
    public void updateUserDiscount(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_USER_DISCOUNT);

            preparedStatement.setInt(1, Boolean.compare(user.getDiscount(),false));
            preparedStatement.setInt(2, user.getId());

            preparedStatement.executeUpdate();
            DBManager.getInstance().commitAndClose(connection);
        }
        catch (SQLException e) {
            LOGGER.error("Cannot update user's discount.", e);
            DBManager.getInstance().rollbackAndClose(connection);
        }
        finally {
            DBManager.getInstance().close(preparedStatement);
        }
    }
}
