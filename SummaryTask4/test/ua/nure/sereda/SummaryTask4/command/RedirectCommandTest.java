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
import ua.nure.sereda.SummaryTask4.service.impl.BookServiceImpl;
import ua.nure.sereda.SummaryTask4.service.impl.OrderServiceImpl;
import ua.nure.sereda.SummaryTask4.service.impl.UserServiceImpl;
import ua.nure.sereda.SummaryTask4.web.Path;
import ua.nure.sereda.SummaryTask4.web.Services;
import ua.nure.sereda.SummaryTask4.web.command.common.RedirectCommand;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RedirectCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ServletContext context;

    @Mock
    private HttpSession session;

    @InjectMocks
    private RedirectCommand redirectCommand;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private OrderServiceImpl orderService;

    @Mock
    private BookServiceImpl bookService;

    private User client;
    private User manager;


    @Before
    public void setUp() {
        client = new User("misha", "misha@nure.ua", Role.READER, "123qwe");

        manager = new User("admin", "admin@nure.ua", Role.ADMIN, "123qwe123'");
    }

    @Test
    public void test_Execute_client() throws Exception {
        when(request.getServletContext()).thenReturn(context);
        when(session.getAttribute("user")).thenReturn(client);
        when(request.getSession()).thenReturn(session);

        String expected = Path.PAGE_INDEX;
        String actual = redirectCommand.execute(request, response);

        assertEquals(expected, actual);
    }

    @Test
    public void test_Execute_manager() throws Exception {
        when(request.getServletContext()).thenReturn(context);
        when(session.getAttribute("user")).thenReturn(manager);
        when(request.getSession()).thenReturn(session);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(context.getAttribute(Services.ORDER)).thenReturn(orderService);
        String expected = Path.PAGE_ADMIN;
        String actual = redirectCommand.execute(request, response);

        assertEquals(expected, actual);
    }


    @Test(expected = AppException.class)
    public void test_Execute_manager_serviceException() throws Exception {
        when(request.getServletContext()).thenReturn(context);
        when(session.getAttribute("user")).thenReturn(manager);
        when(request.getSession()).thenReturn(session);
        when(context.getAttribute(Services.USER)).thenReturn(userService);

        when(userService.getBannedUsers()).thenThrow(new ServiceException(new Throwable()));

        String actual = redirectCommand.execute(request, response);

        assertNull(actual);
    }

    @Test
    public void test_Execute_nullUser() throws Exception {
        when(request.getServletContext()).thenReturn(context);
        when(session.getAttribute("user")).thenReturn(null);
        when(request.getSession()).thenReturn(session);

        String expected = Path.PAGE_INDEX;
        String actual = redirectCommand.execute(request, response);

        assertEquals(expected, actual);
    }
}