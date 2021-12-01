package com.taxiservice.model.dao;

import com.taxiservice.model.DBManager;
import com.taxiservice.model.entity.Car;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.taxiservice.model.dao.SqlQuery.*;

/**
 * Data access object for Car entity.
 *
 * @author Maryna Lendiel
 */
public class CarDAO {
    private static final Logger LOGGER = Logger.getLogger(CarDAO.class);

    /**
     * Method that allows to get all cars from database.
     *
     * @return list of cars.
     */
    public List<Car> getCars(String language) {
        List<Car> carList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_CARS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Car car = createCarFromResSet(resultSet);
                car.setDescription(getCarDescription(car, language));
                carList.add(car);
            }
            DBManager.getInstance().commitAndClose(connection);
        }
        catch (SQLException e) {
            LOGGER.error("Cannot get cars.", e);
            DBManager.getInstance().rollbackAndClose(connection);
        }
        finally {
            DBManager.getInstance().close(preparedStatement);
            DBManager.getInstance().close(resultSet);
        }
        return carList;
    }

    /**
     * Method that allows to get car by id from database.
     *
     * @param carId id of car that we want to find.
     * @return car object.
     */
    public Car getCar(int carId, String language) {
        Car car = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_CAR);
            preparedStatement.setInt(1, carId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                car = createCarFromResSet(resultSet);
                car.setDescription(getCarDescription(car, language));
            }
            DBManager.getInstance().commitAndClose(connection);
        }
        catch (SQLException e) {
            LOGGER.error("Cannot get car.", e);
            DBManager.getInstance().rollbackAndClose(connection);
        }
        finally {
            DBManager.getInstance().close(preparedStatement);
            DBManager.getInstance().close(resultSet);
        }

        return car;
    }

    /**
     * Method that allows to get cars by number of seats.
     * @param numberOfSeats parameter that
     * @return list of cars.
     */
    public Car getCarByNumberOfSeats(int numberOfSeats, String language) {
        Car car = null;

                Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_CARS_BY_NUMBER_OF_SEATS);
            preparedStatement.setInt(1, numberOfSeats);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                car = createCarFromResSet(resultSet);
                car.setDescription(getCarDescription(car, language));
            }
            DBManager.getInstance().commitAndClose(connection);
        }
        catch (SQLException e) {
            LOGGER.error("Cannot get cars with this number of seats.", e);
            DBManager.getInstance().commitAndClose(connection);
        }
        finally {
            DBManager.getInstance().close(preparedStatement);
            DBManager.getInstance().close(resultSet);
        }
        return car;
    }

    public List<Car> getCarsByCategory(String category, String language) {
        List<Car> carList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_CARS_BY_CATEGORY);

            preparedStatement.setString(1, category);
            LOGGER.info(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = createCarFromResSet(resultSet);
                car.setDescription(getCarDescription(car, language));
                carList.add(car);
            }
            DBManager.getInstance().commitAndClose(connection);
        }
        catch (SQLException e) {
            LOGGER.error("Cannot get cars by selected category.", e);
            DBManager.getInstance().rollbackAndClose(connection);
        }
        finally {
            DBManager.getInstance().close(preparedStatement);
            DBManager.getInstance().close(resultSet);
        }
        LOGGER.info("carList: " + carList);
        return carList;
    }

    /**
     * Method allows updating status of selected car.
     *
     * @param carId identifier of car.
     * @param carStatus new status that will be assigned to car.
     */
    public void updateCarStatus(int carId, String carStatus) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_CAR_STATUS);
            preparedStatement.setString(1, carStatus);
            preparedStatement.setInt(2, carId);
            preparedStatement.executeUpdate();
            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot update car status.", e);
        }
        finally {
            DBManager.getInstance().close(preparedStatement);
        }
    }

    /**
     * Method that creates car object from result set.
     *
     * @param resultSet contains information about car from executed query.
     * @return car object.
     * @throws SQLException if error.
     */
    private Car createCarFromResSet(ResultSet resultSet) throws SQLException {
        Car car = Car.createCar();
        car.setId(resultSet.getInt(1));
        car.setBrand(resultSet.getString(2));
        car.setModel(resultSet.getString(3));
        car.setCarNumber(resultSet.getString(4));
        car.setNumberOfSeats(resultSet.getInt(5));
        car.setCategory(resultSet.getString(6));
        car.setStatus(resultSet.getString(7));
        return car;
    }

    /**
     * Method allows getting description of selected car with selected language.
     *
     * @param car car object which description we want to get.
     * @param language language code in which we want to get description.
     * @return car description.
     */
    public String getCarDescription(Car car, String language) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String carDescription = "";
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_CAR_DESCRIPTION);
            preparedStatement.setString(2, language);
            preparedStatement.setInt(1, car.getId());
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                carDescription = resultSet.getString(1);
            }
            DBManager.getInstance().commitAndClose(connection);
        }
        catch (SQLException e) {
            LOGGER.error("Cannot get car's description.", e);
            DBManager.getInstance().rollbackAndClose(connection);
        }
        finally {
            DBManager.getInstance().close(preparedStatement);
            DBManager.getInstance().close(resultSet);
        }
        return carDescription;
    }
}
