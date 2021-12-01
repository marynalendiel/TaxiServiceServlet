package com.taxiservice.command.client;

import com.taxiservice.WebPage;
import com.taxiservice.command.Command;
import com.taxiservice.model.dao.CarDAO;
import com.taxiservice.model.dao.OrderDAO;
import com.taxiservice.model.dao.UserDAO;
import com.taxiservice.model.entity.Order;
import com.taxiservice.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Command that allows to create new order and insert all dependent data into DB.
 *
 * @author Maryna Lendiel
 */
public class CreateOrderCommand extends Command {
    private static final long serialVersionUID = 2199078115772621027L;
    private static final Logger LOGGER = Logger.getLogger(CreateOrderCommand.class);

    private final UserDAO userDAO = new UserDAO();
    private final OrderDAO orderDAO = new OrderDAO();
    private final CarDAO carDAO = new CarDAO();

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("CreateOrderCommand starts");

        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");
        Order order = (Order) httpSession.getAttribute("order");

        if (user == null || order == null) {
            LOGGER.error("User or order object is null");
            return new WebPage(WebPage.ERROR_PAGE, true);
        }

        // change user's discount state
        user.setDiscount(!user.getDiscount());
        LOGGER.debug("User's discount was changed to " + user.getDiscount());
        userDAO.updateUserDiscount(user);
        LOGGER.debug("User's discount was updated in database.");

        List<Integer> carIdList = order.getCarIdList();

        // update car status which is used in order
        for (Integer carId : carIdList) {
            carDAO.updateCarStatus(carId, "on the road");
            LOGGER.debug("Car with id = " + carId + " has updated status - on the road");
        }

        // insert order into DB
        orderDAO.insertOrder(order);
        LOGGER.debug("Order with id = " + order.getId() + " was inserted into DB");

        // remove order from session
        httpSession.removeAttribute("order");

        LOGGER.info("CreateOrderCommand finished");
        return new WebPage(WebPage.MAIN_PAGE, true);
    }
}
