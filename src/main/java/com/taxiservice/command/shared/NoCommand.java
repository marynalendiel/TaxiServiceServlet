package com.taxiservice.command.shared;

import com.taxiservice.WebPage;
import com.taxiservice.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * No command class.
 *
 * @author Maryna Lendiel
 */
public class NoCommand extends Command {
    private static final long serialVersionUID = -7013638892070315701L;
    private static final Logger LOGGER = Logger.getLogger(NoCommand.class);

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.debug("NoCommand starts");

        String errorMessage = "No such command";
        LOGGER.error(errorMessage);

        LOGGER.debug("NoCommand finished");
        return new WebPage(WebPage.ERROR_PAGE, true);
    }
}
