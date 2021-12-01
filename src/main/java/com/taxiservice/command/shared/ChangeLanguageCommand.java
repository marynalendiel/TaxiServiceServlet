package com.taxiservice.command.shared;

import com.taxiservice.WebPage;
import com.taxiservice.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

/**
 * Command that allows to change language which is selected.
 *
 * @author Maryna Lendiel
 */
public class ChangeLanguageCommand extends Command {
    private static final long serialVersionUID = 1725040118588884295L;
    private static final Logger LOGGER = Logger.getLogger(ChangeLanguageCommand.class);

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("ChangeLanguageCommand starts.");

        HttpSession httpSession = request.getSession();
        String loc = request.getParameter("locale");

        if (loc.equals("uk")) {
            Config.set(httpSession, Config.FMT_LOCALE, "uk");
            LOGGER.info("Locale was changed to " + loc);
        }
        else {
            Config.set(httpSession, Config.FMT_LOCALE, "en");
            LOGGER.info("Locale was changed to en");
        }

        httpSession.setAttribute("locale", loc);

        LOGGER.info("ChangeLanguageCommand finished");
        return new WebPage(WebPage.MAIN_PAGE, true);
    }
}