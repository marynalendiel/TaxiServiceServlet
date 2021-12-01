package com.taxiservice.command.shared;

import com.taxiservice.WebPage;
import com.taxiservice.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutCommand extends Command {
    private static final long serialVersionUID = 2120139538699063908L;
    private static final Logger LOGGER = Logger.getLogger(LogOutCommand.class);

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.debug("LogOutCommand starts.");

        HttpSession httpSession = request.getSession();
        httpSession.invalidate();

        LOGGER.debug("LogOutCommand finished.");

        return new WebPage(WebPage.MAIN_PAGE, true);
    }
}
