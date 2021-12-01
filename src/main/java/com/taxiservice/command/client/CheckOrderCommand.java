package com.taxiservice.command.client;

import com.taxiservice.WebPage;
import com.taxiservice.command.Command;
import com.taxiservice.model.dao.CarDAO;
import com.taxiservice.model.dao.OrderDAO;
import com.taxiservice.model.entity.Car;
import com.taxiservice.model.entity.Order;
import com.taxiservice.model.entity.User;
import com.taxiservice.util.OrderDistanceCalculator;
import com.taxiservice.util.OrderPriceCalculator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

/**
 * Command that checks order created by user.
 *
 * @author Maryna Lendiel
 */
public class CheckOrderCommand extends Command {
    private static final long serialVersionUID = 2334331858811771849L;
    private static final Logger LOGGER = Logger.getLogger(CheckOrderCommand.class);

    private static final CarDAO carDAO = new CarDAO();
    private static final OrderDAO orderDAO = new OrderDAO();
    private static String locale;

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("CheckOrderCommand starts");

        String pageUrl = WebPage.ERROR_PAGE;
        Order order;

        HttpSession session = request.getSession();
        locale = (String) session.getAttribute("locale");

        String numberOfSeats = request.getParameter("numberOfSeats");
        String carCategory = request.getParameter("category");
        String startPoint = request.getParameter("startPoint");
        String finishPoint = request.getParameter("finishPoint");
        LOGGER.trace("Request parameter: number of seats " + numberOfSeats
                + "car category " + carCategory
                + "departure " + startPoint
                + "arrival " + finishPoint);

        User user = (User) session.getAttribute("user");

        // if any parameter is null we return to error page with the appropriate message
        if (isNull(numberOfSeats, carCategory, startPoint, finishPoint) || startPoint.equals(finishPoint)) {
            LOGGER.error("There was entered wrong data");

            return new WebPage(pageUrl, false, "error.data");
        }

        Car car = getCar(carCategory);
        int seats = Integer.parseInt(numberOfSeats);

        //Get distance for price calculation
        // !!! add google map api
        double distance = OrderDistanceCalculator.calculate(startPoint, finishPoint);
        if (distance == 0) {
            distance = new SecureRandom().nextInt(20 - 1 + 1) + 1;
        }
        LOGGER.debug("Return distance -> " + distance + "; disc -> " + user.getDiscount());

        double price = OrderPriceCalculator.calculate(distance, user.getDiscount(), carCategory);
        LOGGER.debug("Order price " + price);

        //Creat Order object
        order = Order.createOrder();
        order.setUserId(user.getId());
        order.setStartPoint(startPoint);
        order.setFinishPoint(finishPoint);
        order.setDistance(distance);
        order.setPrice(price);
        order.setNumberOfPassengers(seats);
        LOGGER.debug("Create order -> " + order);

        //If there is no suitable car or there are fewer seats in the car,
        // return to the analog order page
        if (car == null || car.getNumberOfSeats() < seats) {
            LOGGER.info("Cannot find appropriate car for order");

            pageUrl = WebPage.ALTERNATIVE_ORDER_PAGE;
            session.setAttribute("category", carCategory);
        } else {
            order.setCarId(car.getId());
            pageUrl = WebPage.SUCCESSFUL_ORDER_PAGE;

            LOGGER.info("Car was successfully added to order");
        }
        // set order object as session attribute for further processing
        session.setAttribute("order", order);

        LOGGER.info("CheckOrderCommand finished");
        return new WebPage(pageUrl, true);
    }

    private static Car getCar(String category) {
        Car car = null;

        List<Car> cars = carDAO.getCarsByCategory(category, locale);

        // get first car from list if list is not empty
        if (!cars.isEmpty()) {
            car = cars.get(0);
            LOGGER.info("Car was found " + car);
        }

        // return car or null object
        return car;
    }
}
