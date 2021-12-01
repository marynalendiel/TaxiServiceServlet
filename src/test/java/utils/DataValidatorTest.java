package utils;

import com.taxiservice.util.DataValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataValidatorTest {
    String firstName;
    String lastName;
    String login;
    String phoneNumber;
    String email;
    String password;

    @Before
    public void setUp(){
        firstName = "Tom";
        lastName = "Cat";
        login = "tomcat";
        phoneNumber = "0123456789";
        email = "tomcat@tomcat.tom";
        password = "tomcat123";
    }

    @Test
    public void successfulCheckData() {
        assertTrue(DataValidator.checkData(firstName, lastName, login, phoneNumber, email, password));
    }

    @Test
    public void unSuccessfulCheckData() {
        email = "asdf@asdf";
        assertFalse(DataValidator.checkData(firstName, lastName, login, phoneNumber, email, password));
    }
}
