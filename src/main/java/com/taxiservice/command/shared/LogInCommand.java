package com.taxiservice.command.shared;

import com.taxiservice.WebPage;
import com.taxiservice.command.Command;
import com.taxiservice.model.dao.UserDAO;
import com.taxiservice.model.entity.User;
import com.taxiservice.util.PasswordEncryptor;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command to log in user.
 *
 * @author Maryna Lendiel
 */
public class LogInCommand extends Command {
    private static final long serialVersionUID = 495433053394965577L;
    private static final Logger LOGGER = Logger.getLogger(LogInCommand.class);

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("LogInCommand starts");

        String pageUrl = WebPage.ERROR_PAGE;
        HttpSession httpSession = request.getSession();
        String login = request.getParameter("login");
        LOGGER.info("Request parameter: login -> " + login);

        String password = PasswordEncryptor.encrypt(request.getParameter("password"));

        if (isNull(login, password)) {
            LOGGER.error("Login or password is empty");
            return new WebPage(pageUrl, false, "error.data");
        }

        // find user in DB with UserDAO
        // if account doesn't exist method returns null
        User user = new UserDAO().getUser(login);
        LOGGER.info("User in DB: " + user);

        // block returns error page if user is null or password is wrong
        if (user == null || !password.equals(user.getPassword())) {
            LOGGER.error("Cannot find user with such login or password");
            return new WebPage(pageUrl, false, "error.data");
        }
        else {
            pageUrl = WebPage.MAIN_PAGE;
        }

        httpSession.setAttribute("user", user);
        LOGGER.info("Set the session attribute: user " + user);
        // set language as session attribute
        if (httpSession.getAttribute("locale") == null) {
            httpSession.setAttribute("locale", "en");
        }

        LOGGER.info("LogInCommand finished");
        return new WebPage(pageUrl, true);
    }
}
