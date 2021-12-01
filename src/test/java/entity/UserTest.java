package entity;

import com.taxiservice.model.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
    User user;
    int id;
    String login;
    String firstName;
    String lastName;
    String email;
    String password;
    String phoneNumber;

    @Before
    public void setUp() {
        id = 1;
        login = "tomcat";
        firstName = "Tom";
        lastName = "Cat";
        email = "tomcat@tomcat.tom";
        password = "tomcat123";
        phoneNumber = "0123456789";

        user = User.createUser();
        user.setId(id);
        user.setLogin(login);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
    }

    @Test
    public void shouldCreateAccount() {
        Assert.assertTrue(User.createUser() instanceof User);
    }

    @Test
    public void shouldGetId() {
        Assert.assertEquals(id, user.getId());
    }

    @Test
    public void shouldGetLogin() {
        Assert.assertEquals(login, user.getLogin());
    }

    @Test
    public void shouldGetFirstName() {
        Assert.assertEquals(firstName, user.getFirstName());
    }

    @Test
    public void shouldGetLastName() {
        Assert.assertEquals(lastName, user.getLastName());
    }

    @Test
    public void shouldGetEmail() {
        Assert.assertEquals(email, user.getEmail());
    }

    @Test
    public void shouldGetPassword() {
        Assert.assertEquals(password, user.getPassword());
    }

    @Test
    public void shouldGetPhoneNumber() {
        Assert.assertEquals(phoneNumber, user.getPhoneNumber());
    }

    @Test
    public void roleAndDiscountShouldBeFalse() {
        Assert.assertFalse(user.getRole() && user.getDiscount());
    }

    @Test
    public void shouldSetId() {
        id = 2;
        user.setId(id);
        Assert.assertEquals(id, user.getId());
    }

    @Test
    public void shouldSetLogin() {
        login = "T'Challa";
        user.setLogin(login);
        Assert.assertEquals(login, user.getLogin());
    }

    @Test
    public void shouldFirstName() {
        firstName = "catTom";
        user.setFirstName(firstName);
        Assert.assertEquals(firstName, user.getFirstName());
    }

    @Test
    public void shouldLastName() {
        lastName = "catTom";
        user.setLastName(lastName);
        Assert.assertEquals(lastName, user.getLastName());
    }

    @Test
    public void shouldSetEmail() {
        email = "tomtom@tom.cat";
        user.setEmail(email);
        Assert.assertEquals(email, user.getEmail());
    }

    @Test
    public void shouldSetPassword() {
        password = "WakandaForever";
        user.setPassword(password);
        Assert.assertEquals(password, user.getPassword());
    }

    @Test
    public void shouldSetPhoneNumber() {
        phoneNumber = "121212";
        user.setPhoneNumber(phoneNumber);
        Assert.assertEquals(phoneNumber, user.getPhoneNumber());
    }

    @Test
    public void shouldSetDiscount() {
        user.setDiscount(true);
        Assert.assertTrue(user.getDiscount());
    }

    @Test
    public void shouldUpdateAccountRole() {
        user.setRole(true);
        Assert.assertTrue(user.getRole());
    }
}
