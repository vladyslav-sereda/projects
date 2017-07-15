package ua.nure.sereda.SummaryTask4.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.sereda.SummaryTask4.models.Role;
import ua.nure.sereda.SummaryTask4.models.User;
import ua.nure.sereda.SummaryTask4.service.UserService;
import ua.nure.sereda.SummaryTask4.web.Services;
import ua.nure.sereda.SummaryTask4.web.command.common.SignUpCommand;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignUpCommandTest {

    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ServletContext context;

    @Mock
    private HttpSession session;

    @InjectMocks
    private SignUpCommand signUpCommand;

    private User user;

    @Before
    public void setUp() {
        user = new User("ivan", "ivan@gmail.com", Role.READER, "123123");
    }

    @Test
    public void test_Execute_normal() throws Exception {
        when(userService.getByEmail("ivan@gmail.com")).thenReturn(null);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("ivan@gmail.com");
        when(request.getParameter("password")).thenReturn("123123");
        when(request.getParameter("passwordConfirmation")).thenReturn("123123");
        when(request.getParameter("name")).thenReturn("ivan");
        signUpCommand.execute(request, response);

        verify(response).setStatus(200);
    }

    @Test
    public void test_Execute_emailInUse() throws Exception {
        when(userService.getByEmail("ivan@gmail.com")).thenReturn(user);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("ivan@gmail.com");
        when(request.getParameter("password")).thenReturn("123123");
        when(request.getParameter("passwordConfirmation")).thenReturn("123123");
        when(request.getParameter("name")).thenReturn("ivan");
        signUpCommand.execute(request, response);

        verify(response).setStatus(409);
    }

    @Test
    public void test_Execute_invalidEmail() throws Exception {
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("ivangmail.com");
        when(request.getParameter("password")).thenReturn("123123");
        when(request.getParameter("passwordConfirmation")).thenReturn("123123");
        when(request.getParameter("name")).thenReturn("ivan");

        signUpCommand.execute(request, response);

        verify(response).setStatus(400);
    }

    @Test
    public void test_Execute_passwordConfirmDoesntMatch() throws Exception {
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("ivan@gmail.com");
        when(request.getParameter("password")).thenReturn("123123");
        when(request.getParameter("passwordConfirmation")).thenReturn("321321");
        when(request.getParameter("name")).thenReturn("ivan");
        signUpCommand.execute(request, response);

        verify(response).setStatus(400);
    }

}
