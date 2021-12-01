package com.taxiservice.command.admin;

import com.taxiservice.WebPage;
import com.taxiservice.command.Command;
import com.taxiservice.command.shared.GetCarInformationCommand;
import com.taxiservice.model.dao.CarDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command that allows to change status of specific car
 *
 * @author Maryna Lendiel
 */
public class ChangeCarStatusCommand extends Command {
    private static final long serialVersionUID = 3848133007600369236L;
    private static final Logger LOGGER = Logger.getLogger(ChangeCarStatusCommand.class);

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("ChangeCarStatusCommand starts");

        CarDAO carDAO = new CarDAO();

        String carId = request.getParameter("carId");
        String status = request.getParameter("status");
        String orderId = request.getParameter("orderId");
        LOGGER.debug("Get parameters: carId -> " + carId + "; status -> " + status + "; order id -> " + orderId);

        // we can change car status if carId and status are not null or empty
        if (carId != null && !carId.isEmpty() && status != null && !status.isEmpty()) {
            carDAO.updateCarStatus(Integer.parseInt(carId), status);
            LOGGER.debug("Car with id -> " + carId + "has updated status -> " + status);
        }

        // if orderId is not null or empty ChangeCarStatusCommand was called from "information about car" page
        // in this case we return to GetCarInformationCommand
        if (orderId != null && !orderId.isEmpty()) {
            request.setAttribute("orderId", orderId);
            LOGGER.debug("Transfer to order " + orderId + "cars details");

            return new GetCarInformationCommand().execute(request, response);
        }

        LOGGER.info("Command finished");
        // otherwise, we return to GetCarsCommand
        return new GetCarsCommand().execute(request, response);
    }
}
