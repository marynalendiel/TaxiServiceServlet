package com.taxiservice.command.client;

import com.taxiservice.WebPage;
import com.taxiservice.command.Command;
import com.taxiservice.model.dao.CarDAO;
import com.taxiservice.model.entity.Car;
import com.taxiservice.model.entity.Order;
import com.taxiservice.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Command that helps to choose alternative order if original is impossible to realise.
 *
 * @author Maryna Lendiel
 */
public class AlternativeOrderCommand extends Command {
    private static final long serialVersionUID = 5356277597060558160L;
    private static final Logger LOGGER = Logger.getLogger(AlternativeOrderCommand.class);

    private static final CarDAO carDAO = new CarDAO();

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("AlternativeOrderCommand starts");
        String pageUrl = WebPage.ERROR_PAGE;

        HttpSession httpSession = request.getSession();
        String locale = (String) httpSession.getAttribute("locale");
        Order order = (Order) httpSession.getAttribute("order");
        User user = (User) httpSession.getAttribute("user");

        String category = (String) httpSession.getAttribute("category");
        String orderOption = request.getParameter("orderOption");

        LOGGER.trace("Request parameter: category -> " + category + ", order option ->" + orderOption);

        //If one of the parameters is null, return to the error page with the appropriate message
        if (order == null || isNull(category, orderOption)) {
            LOGGER.info("Null or empty attribute was sent to alternative order");
            return new WebPage(pageUrl, false, "error.data");
        }

        // if user chose car from another category we find that car and return successful order page
        // or error page if auto park doesn't have car
        if (orderOption.equals("anotherCategory")) {
            LOGGER.info("User chose car with another category");
            Car car = carDAO.getCarByNumberOfSeats(order.getNumberOfPassengers(), locale);
            if (car != null) {
                LOGGER.info("Car was found " + car);
                order.setCarId(car.getId());
                pageUrl = WebPage.SUCCESSFUL_ORDER_PAGE;
            }
            else {
                LOGGER.info("Car wasn't found");
                pageUrl = WebPage.ERROR_PAGE;
            }
        }

        // if user chose car from another category we find that cars and return successful order page
        // or error page if auto park doesn't have cars
        if (orderOption.equals("sameCategory")) {
            LOGGER.info("User chose car with the same category");
            List<Car> carList = carDAO.getCarsByCategory(category, locale);
            List<Car> carsList = new ArrayList<>();
            int seat = 0;
            if (carList.size() >= 2) {
                LOGGER.info("Cars were found " + carList);
                for (Car car : carList) {
                    carsList.add(car);
                    seat = seat + car.getNumberOfSeats();
                    if (seat > order.getNumberOfPassengers()) {
                        break;
                    }
                }
                for (Car car : carsList) {
                    LOGGER.info("Car was added to order " + car);
                    order.setCarId(car.getId());
                }
                pageUrl = WebPage.SUCCESSFUL_ORDER_PAGE;
            }
            else {
                LOGGER.info("Cars weren't found");
                pageUrl = WebPage.ERROR_PAGE;
            }
        }

        LOGGER.info("AlternativeOrderCommand finished");
        if (pageUrl.equals(WebPage.ERROR_PAGE)) {
            return new WebPage(pageUrl, false, "error.cars");
        }
        return new WebPage(pageUrl, true);
    }
}
