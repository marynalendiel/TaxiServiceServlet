package com.taxiservice.command.shared;

import com.taxiservice.WebPage;
import com.taxiservice.command.Command;
import com.taxiservice.model.dao.OrderDAO;
import com.taxiservice.model.entity.Order;
import com.taxiservice.model.entity.User;
import com.taxiservice.util.Pagination;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Command that allows to get order list considering user status and sorting parameter
 *
 * @author Maryna Lendiel
 */
public class GetOrdersCommand extends Command {
    private static final long serialVersionUID = 1791239507047106527L;
    private static final Logger LOGGER = Logger.getLogger(GetOrdersCommand.class);

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("GetOrdersCommand starts");

        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");

        String refresh = request.getParameter("refresh");
        String sortParameter = request.getParameter("sort");
        String userId = request.getParameter("userId");
        String date = request.getParameter("date");

        LOGGER.info("Request parameters: userId -> " + userId +
                " sort parameter -> " + sortParameter +
                " date -> " + date);

        //If user wants to see orders in natural order
        if (!isNull(refresh)) {
            httpSession.removeAttribute("orderList");
            LOGGER.info("List of orders was refreshed");
        }

        List<Order> orderList = (List<Order>) httpSession.getAttribute("orderList");

        //return page depends on user role
        String pageUrl = user.getRole() ? WebPage.ADMIN_ACCOUNT_PAGE : WebPage.CUSTOMER_ACCOUNT_PAGE;

        OrderDAO orderDAO = new OrderDAO();
        LOGGER.info("orderList -> " + orderList);
        //If role == true, it means that the Admin requests access,
        // if the orderList == null it means that all orders must be returned
        if (user.getRole() && orderList == null) {
            orderList = orderDAO.getOrders();
            LOGGER.info("The full list of orders was taken");
        }

        //Depending on which parameters have been selected,
        // an order filter will be created
        if (!isNull(userId)) {
            orderList = orderDAO.getUserOrders(Integer.parseInt(userId));
            LOGGER.info("A list of orders for the user " + userId + " was taken");
        } else if (!isNull(date)) {
            orderList = orderDAO.getOrdersByDate(date);
            LOGGER.info("A list of orders by date " + date + " was taken");
        }

        //Get orders for the client
        if (!user.getRole() && orderList == null) {
            orderList = orderDAO.getUserOrders(user.getId());
            LOGGER.info("A list of orders for the user " + user.getId() + " was taken");
        }

        //If request has "sort" parameter order list will be sorted
        if (!isNull(sortParameter)) {
            sortOrderList(orderList, sortParameter);
            LOGGER.info("A list of orders was sorted");
        }

        Pagination.paginate(orderList, request);

        httpSession.setAttribute("orderList", orderList);
        LOGGER.info("Set order list " + orderList);

        LOGGER.info("Command finished");
        return new WebPage(pageUrl, false);
    }

    private void sortOrderList(List<Order> orderList, String sortParameter) {
        if (sortParameter.equals("date")) {
            LOGGER.debug("Sort list by date");
            orderList.sort((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));
        } else {
            LOGGER.debug("Sort list by price");
            orderList.sort(((o1, o2) -> (int) (o1.getPrice() - o2.getPrice())));
        }
    }
}
