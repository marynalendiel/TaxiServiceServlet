package utils;

import com.taxiservice.util.PasswordEncryptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PasswordEncryptorTest {
    String value;

    @Before
    public void setUp() {
        value = "qwerty123";
    }

    @Test
    public void shouldReturnEncryptedValueTest() {
        System.out.println(PasswordEncryptor.encrypt(value));
        Assert.assertNotEquals(value, PasswordEncryptor.encrypt(value));
    }
}
