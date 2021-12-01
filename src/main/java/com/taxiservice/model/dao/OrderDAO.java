package com.taxiservice.model.dao;

import com.taxiservice.model.DBManager;
import com.taxiservice.model.entity.Order;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.taxiservice.model.dao.SqlQuery.*;

/**
 * Data access object for Order entity.
 *
 * @author Maryna Lendiel
 */
public class OrderDAO {
    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class);

    /**
     * Method that allows to insert a new order into a database.
     */
    public void insertOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement
                    (INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, order.getStartPoint());
            preparedStatement.setString(2, order.getFinishPoint());
            preparedStatement.setDouble(3, order.getDistance());
            preparedStatement.setDouble(4, order.getPrice());
            preparedStatement.setInt(5, order.getNumberOfPassengers());
            preparedStatement.setInt(6, order.getUserId());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getInt(1));
            }

            DBManager.getInstance().commitAndClose(connection);

            insertCarsToOrder(order);
        } catch (SQLException e) {
            LOGGER.error("Cannot insert order", e);
            DBManager.getInstance().rollbackAndClose(connection);
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
    }

    private void insertCarsToOrder(Order order) {
        List<Integer> carsId = order.getCarIdList();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_CARS_TO_ORDER);
            for (Integer carId : carsId) {

                preparedStatement.setInt(1, order.getId());
                preparedStatement.setInt(2, carId);

                //add query to package
                preparedStatement.addBatch();
            }
            //execute package
            preparedStatement.executeBatch();
            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot insert cars to order", e);

        }
        finally {
            DBManager.getInstance().close(preparedStatement);
        }
    }

    /**
     * Method that allows to get all orders from a database.
     */
    public List<Order> getOrders() {
        List<Order> orderList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_ORDERS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                orderList.add(createOrderFromResSet(resultSet));
            }

            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot get orders", e);
            DBManager.getInstance().rollbackAndClose(connection);
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
        return orderList;
    }

    /**
     * Method that allows to get orders created my specific user.
     *
     * @param userId identifier of user.
     * @return list of orders.
     */
    public List<Order> getUserOrders(int userId) {
        List<Order> ordersList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_USER_ORDERS);
            preparedStatement.setInt(1, userId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ordersList.add(createOrderFromResSet(resultSet));
            }

            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot get user's orders", e);
            DBManager.getInstance().rollbackAndClose(connection);
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
        return ordersList;
    }

    /**
     * Method that allows to get order created at specific date.
     *
     * @param date date parameter which is used to select orders.
     * @return list of orders.
     */
    public List<Order> getOrdersByDate(String date) {
        List<Order> ordersList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_ORDERS_BY_DATE);

            preparedStatement.setDate(1, Date.valueOf(date));

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ordersList.add(createOrderFromResSet(resultSet));
            }

            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot get orders by date", e);
            DBManager.getInstance().rollbackAndClose(connection);
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
        return ordersList;
    }

    /**
     * Method that creates order object from result set.
     *
     * @param resultSet contains information about order from executed query.
     * @return Order object.
     * @throws SQLException if error.
     */
    private Order createOrderFromResSet(ResultSet resultSet) throws SQLException {
        Order order = Order.createOrder();

        order.setId(resultSet.getInt(1));
        order.setStartPoint(resultSet.getString(2));
        order.setFinishPoint(resultSet.getString(3));
        order.setDistance(resultSet.getDouble(4));
        order.setPrice(resultSet.getDouble(5));
        order.setNumberOfPassengers(resultSet.getInt(6));
        order.setCreateTime(resultSet.getDate(7));
        order.setUserId(resultSet.getInt(8));

        getOrderCars(order);

        return order;
    }

    /**
     * Method that adds cars (its identifiers) to order.
     *
     * @param order order object that has cars.
     */
    private void getOrderCars(Order order) {
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_ORDER_CARS);

            preparedStatement.setInt(1, order.getId());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                order.setCarId(resultSet.getInt(1));
            }

            DBManager.getInstance().commitAndClose(connection);
        }
        catch (SQLException e) {
            LOGGER.error("Cannot get order cars", e);
        }
        finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
    }

    /**
     * Method that helps to get all cars that is used in specific order.
     *
     * @param orderId order identifier that helps to find cars.
     * @return list of cars of order.
     */
    public List<Integer> getCarIdFromOrder(int orderId) {
        List<Integer> carIdList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_CAR_ID_FROM_ORDER);
            preparedStatement.setInt(1, orderId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                carIdList.add(resultSet.getInt(1));
            }

            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot get orders", e);
            DBManager.getInstance().rollbackAndClose(connection);
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
        return carIdList;
    }
}
