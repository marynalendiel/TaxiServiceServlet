package com.taxiservice.command.shared;

import com.taxiservice.WebPage;
import com.taxiservice.command.Command;
import com.taxiservice.model.dao.CarDAO;
import com.taxiservice.model.dao.OrderDAO;
import com.taxiservice.model.entity.Car;
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
 * Command that allows to get information about all cars that are used in specific order.
 *
 * @author Maryna Lendiel
 */
public class GetCarInformationCommand extends Command {
    private static final long serialVersionUID = -8874959730874632473L;
    private static final Logger LOGGER = Logger.getLogger(GetCarInformationCommand.class);

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("GetCarInformationCommand starts");
        String pageUrl = WebPage.ERROR_PAGE;

        OrderDAO orderDAO = new OrderDAO();
        List<Integer> carsId = new ArrayList<>();

        HttpSession httpSession = request.getSession();
        String locale = (String) httpSession.getAttribute("locale");
        User user = (User) httpSession.getAttribute("user");
        String orderId = request.getParameter("orderId");

        // if orderId is not null we return car information page considering user role:
        // true - user is admin, false - user is client
        if (!isNull(orderId)) {
            carsId = orderDAO.getCarIdFromOrder(Integer.parseInt(orderId));
            pageUrl = user.getRole() ? WebPage.ORDER_CARS_PAGE : WebPage.CAR_INFO_PAGE;

            LOGGER.info("Received information about the cars in the order " + orderId);
        }
        List<Car> carList = getCarList(carsId,locale);

        request.setAttribute("orderId", orderId);
        request.setAttribute("carsList", carList);
        LOGGER.info("carList -> " + carList + " orderId -> " + orderId);

        LOGGER.info("GetCarInformationCommand finished");
        return new WebPage(pageUrl, false);
    }

    /**
     * Method returns list of cars of specific order and locale.
     * @param carsId list of cars' identifier which info we are going to get
     * @param locale language in which we are going to get description about car
     * @return list of car objects
     */
    public List<Car> getCarList(List<Integer> carsId, String locale) {
        List<Car> carList = new ArrayList<>();
        CarDAO carDAO = new CarDAO();

        for (Integer carId : carsId) {
            Car car = carDAO.getCar(carId,locale);
            LOGGER.info("carr - > " + car);
            carList.add(carDAO.getCar(carId, locale));
        }

        return carList;
    }
}
