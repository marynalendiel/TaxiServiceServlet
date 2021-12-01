package com.taxiservice.command.client;

import com.taxiservice.WebPage;
import com.taxiservice.command.Command;
import com.taxiservice.model.dao.UserDAO;
import com.taxiservice.model.entity.User;
import com.taxiservice.util.DataValidator;
import com.taxiservice.util.PasswordEncryptor;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command to sign up user.
 *
 * @author Maryna Lendiel
 */
public class SignUpCommand extends Command {
    private static final long serialVersionUID = 7216639533767497592L;
    private static final Logger LOGGER = Logger.getLogger(SignUpCommand.class);

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("SignUpCommand starts");
        String pageUrl = WebPage.ERROR_PAGE;

        HttpSession session = request.getSession();
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String login = request.getParameter("login");
        String phoneNumber = request.getParameter("phone_number");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LOGGER.trace("Request parameter: first name: " + firstName +
                "last name: " + lastName +
                "login: " + login
                + "phone_number "
                + phoneNumber
                + "email " + email);

        //If the data was invalid, return to the error page with the appropriate message
        if (!DataValidator.checkData(firstName, lastName, login, phoneNumber, email, password)) {
            LOGGER.error("Input data is wrong");

            return new WebPage(pageUrl, false, "error.data");
        }

        // set language as session attribute
        if (session.getAttribute("locale") == null) {
            session.setAttribute("locale", "uk");

            LOGGER.debug("Set uk locale");
        }

        // create user with entered data
        User user = User.createUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        user.setPassword(PasswordEncryptor.encrypt(password));
        UserDAO userDao = new UserDAO();

        // if new user successfully added into database we return to main page
        if (userDao.insertUser(user)) {
            session.setAttribute("account", user);
            pageUrl = WebPage.MAIN_PAGE;

            LOGGER.info("Created account " + user.getLogin());
        }

        return new WebPage(pageUrl, true);
    }
}
