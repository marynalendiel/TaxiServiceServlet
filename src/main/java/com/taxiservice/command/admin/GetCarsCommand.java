package com.taxiservice.command.admin;

import com.taxiservice.WebPage;
import com.taxiservice.command.Command;
import com.taxiservice.model.dao.CarDAO;
import com.taxiservice.model.entity.Car;
import com.taxiservice.util.Pagination;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Command that allows to get all all existing cars
 *
 * @author Maryna Lendiel
 */
public class GetCarsCommand extends Command {
    private static final long serialVersionUID = -104096516353860907L;
    private static final Logger LOGGER = Logger.getLogger(GetCarsCommand.class);

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("GetCarsCommand starts");

        CarDAO carDAO = new CarDAO();
        HttpSession httpSession = request.getSession();
        String locale = (String) httpSession.getAttribute("locale");

        List<Car> carList = carDAO.getCars(locale);
        LOGGER.info("carList " + carList);

        Pagination.paginate(carList, request);

        LOGGER.info("GetCarsCommand finished");
        return new WebPage(WebPage.AUTO_PARK_PAGE, false);
    }
}
