package command;

import com.taxiservice.WebPage;
import com.taxiservice.command.shared.LogOutCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class LogOutCommandTest {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpSession session = mock(HttpSession.class);
    String pageURL = WebPage.MAIN_PAGE;

    @Before
    public void setUp() {
        when(request.getSession()).thenReturn(session);
        doNothing().when(session).invalidate();
    }

    @Test
    public void shouldRedirectToTheMainPage() throws ServletException, IOException {
        LogOutCommand logoutCommand = new LogOutCommand();
        WebPage path = logoutCommand.execute(request, response);

        Assert.assertTrue(path.getPageUrl().equals(pageURL) && path.isRedirect());
    }
}
