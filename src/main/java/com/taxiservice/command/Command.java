package com.taxiservice.command;

import com.taxiservice.WebPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Main class for Command.
 *
 * @author Maryna Lendiel
 */
public abstract class Command implements Serializable {
    private static final long serialVersionUID = -2125829048242960036L;

    public abstract WebPage execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException;

    /**
     * Method checks if parameters are null.
     *
     * @param parameters parameters that are going to be checked.
     * @return true if parameters are not null.
     */
    public final boolean isNull(String... parameters) {
        for (String parameter : parameters) {
            if (parameter == null || parameter.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return a string representation of the object.
     */
    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}
