package command;

import static org.mockito.Mockito.mock;

import com.taxiservice.WebPage;
import com.taxiservice.command.shared.NoCommand;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCommandTest {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    String pageUrl = WebPage.ERROR_PAGE;

    @Test
    public void shouldReturnErrorPage() throws ServletException, IOException {
        NoCommand noCommand = new NoCommand();
        WebPage path = noCommand.execute(request, response);

        Assert.assertEquals(path.getPageUrl(), pageUrl);
    }
}
