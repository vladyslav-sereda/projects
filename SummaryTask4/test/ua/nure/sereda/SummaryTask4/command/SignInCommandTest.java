package ua.nure.sereda.SummaryTask4.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.sereda.SummaryTask4.exception.AppException;
import ua.nure.sereda.SummaryTask4.exception.ServiceException;
import ua.nure.sereda.SummaryTask4.models.Role;
import ua.nure.sereda.SummaryTask4.models.User;
import ua.nure.sereda.SummaryTask4.service.impl.UserServiceImpl;
import ua.nure.sereda.SummaryTask4.web.Services;
import ua.nure.sereda.SummaryTask4.web.command.common.SignInCommand;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class SignInCommandTest {

    @Mock
    private UserServiceImpl userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ServletContext context;

    @Mock
    private HttpSession session;

    @InjectMocks
    private SignInCommand signInCommand;

    private User user;

    @Before
    public void setUp() {
        user = new User("pasha", "pasha@gmail.com", Role.LIBRARIAN, "pasha123");
    }

    @Test
    public void test_Execute_normal() throws Exception {
        when(userService.getByEmail("pasha@gmail.com")).thenReturn(user);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn(user.getEmail());
        when(request.getParameter("password")).thenReturn(user.getPassword());
        signInCommand.execute(request, response);

        verify(response).setStatus(200);
    }

    @Test
    public void test_Execute_invalidEmail() throws Exception {
        when(userService.getByEmail("pasha@gmail.com")).thenReturn(user);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("wrongMail");
        when(request.getParameter("password")).thenReturn(user.getPassword());
        signInCommand.execute(request, response);

        verify(response).setStatus(406);
    }

    @Test
    public void test_Execute_invalidPassword() throws Exception {
        when(userService.getByEmail("pasha@gmail.com")).thenReturn(user);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn(user.getEmail());
        when(request.getParameter("password")).thenReturn("1234");
        signInCommand.execute(request, response);

        verify(response).setStatus(423);
    }

    @Test(expected = AppException.class)
    public void test_Execute_serviseException() throws Exception {
        when(userService.getByEmail("pasha@gmail.com")).thenThrow(new ServiceException(new Throwable()));
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn(user.getEmail());
        when(request.getParameter("password")).thenReturn("random");

        signInCommand.execute(request, response);

        verify(response).setStatus(500);
    }
}
