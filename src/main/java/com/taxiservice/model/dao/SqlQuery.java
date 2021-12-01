package com.taxiservice.model.dao;

public class SqlQuery {
    /**
     * SQL queries for UserDAO.
     *
     */
    public static final String INSERT_USER = "INSERT INTO user VALUES" +
            "(DEFAULT, ?, ?, ?, ?, ?, ?, DEFAULT, ?, DEFAULT)";
    public static final String GET_USER =
            "SELECT * FROM user WHERE login = ?";
    public static final String UPDATE_USER_DISCOUNT =
            "UPDATE user SET discount = ? WHERE id = ?";

    /**
     * SQL queries for CarDAO.
     *
     */
    public static final String GET_CARS = "SELECT * FROM car";
    public static final String GET_CAR_DESCRIPTION = "SELECT car_description FROM car_has_language " +
            "WHERE car_id = ? AND language_id = " +
            "(SELECT id from language WHERE language_code = ?)";
    public static final String GET_CAR = "SELECT * FROM car WHERE id = ?";
    public static final String GET_CARS_BY_NUMBER_OF_SEATS = "SELECT * FROM car WHERE car.status = 'to order' AND number_of_seats >= ?";
    public static final String GET_CARS_BY_CATEGORY = "SELECT * FROM car WHERE car.status = 'to order' AND category = ? LIMIT 3";
    public static final String UPDATE_CAR_STATUS = "UPDATE car SET car.status = ? WHERE car.id = ?";

    /**
     *SQL queries for OrderDAO.
     */
    public static final String INSERT_ORDER =
            "INSERT INTO taxiservice.order VALUES(DEFAULT, ?, ?, ?, ?, ?, DEFAULT, ?)";
    public static final String INSERT_CARS_TO_ORDER =
            "INSERT INTO order_has_car VALUES (?, ?)";
    public static final String GET_ORDERS = "SELECT * FROM taxiservice.order";
    public static final String GET_USER_ORDERS = "SELECT * FROM taxiservice.order WHERE user_id = ?";
    public static final String GET_ORDERS_BY_DATE = "SELECT * FROM taxiservice.order WHERE create_time >= ?";
    public static final String GET_ORDER_CARS = "SELECT car_id FROM order_has_car WHERE order_id = ?";
    public static final String GET_CAR_ID_FROM_ORDER = "SELECT car_id FROM order_has_car WHERE order_id = ?";

    private SqlQuery() { throw new IllegalStateException("SqlQuery class");}
}
