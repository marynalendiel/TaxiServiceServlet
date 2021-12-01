package command;

import com.taxiservice.command.CommandFactory;
import com.taxiservice.command.client.SignUpCommand;
import com.taxiservice.command.shared.NoCommand;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CommandFactoryTest {
    String commandName;

    @Before
    public void setUp() {
        commandName = "signUp";
    }

    @Test
    public void shouldReturnSignUpCommand() {
        assertTrue(CommandFactory.getCommand(commandName) instanceof SignUpCommand);
    }

    @Test
    public void shouldReturnNoCommand() {
        commandName = "asdf";
        assertTrue(CommandFactory.getCommand(commandName) instanceof NoCommand);
    }

}
