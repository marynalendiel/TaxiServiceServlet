package com.taxiservice.command;

import com.taxiservice.command.admin.ChangeCarStatusCommand;
import com.taxiservice.command.admin.GetCarsCommand;
import com.taxiservice.command.client.AlternativeOrderCommand;
import com.taxiservice.command.client.CheckOrderCommand;
import com.taxiservice.command.client.CreateOrderCommand;
import com.taxiservice.command.shared.*;
import com.taxiservice.command.client.SignUpCommand;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * Main Command factory for Controller.
 *
 * @author Maryna Lendiel
 */
public class CommandFactory {
    private static final Logger LOGGER = Logger.getLogger(CommandFactory.class);

    private static Map<String, Command> commands = new TreeMap<>();

    static {
        // common commands
        commands.put("logIn", new LogInCommand());
        commands.put("logOut", new LogOutCommand());
        commands.put("changeLanguage", new ChangeLanguageCommand());
        commands.put("noCommand", new NoCommand());

        // client commands
        commands.put("signUp", new SignUpCommand());
        commands.put("createOrder", new CreateOrderCommand());
        commands.put("checkOrder", new CheckOrderCommand());
        commands.put("alternativeOrder", new AlternativeOrderCommand());
        commands.put("getCarInformation", new GetCarInformationCommand());

        // admin commands
        commands.put("getCars", new GetCarsCommand());
        commands.put("getOrders", new GetOrdersCommand());
        commands.put("changeCarStatus", new ChangeCarStatusCommand());
    }

    // method that helps to execute command from map by key
    public static Command getCommand(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOGGER.info("Command wasn't found. Name -> " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }

}
