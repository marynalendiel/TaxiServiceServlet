package com.taxiservice.controller;

import com.taxiservice.WebPage;
import com.taxiservice.command.Command;
import com.taxiservice.command.CommandFactory;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main servlet controller.
 *
 * @author Maryna Lendiel
 */
@WebServlet("/controller")
public class FrontController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(FrontController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    /**
     * Main method of front controller.
     *
     */
    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        String commandName = req.getParameter("command");
        LOGGER.info("Command " + commandName);
        Command command = CommandFactory.getCommand(commandName);

        WebPage page = command.execute(req, resp);
        LOGGER.info("Url page " + page.getPageUrl());

        String errorMessage = page.getErrorMessage();

        //If an error occurs, we extract a message about it for further transmission to the user
        if (errorMessage != null && !errorMessage.isEmpty()) {
            req.setAttribute("errorMessage", errorMessage);
        }

        boolean isRedirect = page.isRedirect();

        if (isRedirect) {
            redirect(page, req, resp);
        } else {
            forward(page, req, resp);
        }
    }

    private void redirect(WebPage path, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = path.getPageUrl();
        response.sendRedirect(request.getContextPath() + url);
    }

    private void forward(WebPage path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = path.getPageUrl();

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }
}
