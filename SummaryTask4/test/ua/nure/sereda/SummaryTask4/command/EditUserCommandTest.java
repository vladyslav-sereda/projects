package ua.nure.sereda.SummaryTask4.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.sereda.SummaryTask4.exception.AppException;
import ua.nure.sereda.SummaryTask4.exception.ServiceException;
import ua.nure.sereda.SummaryTask4.models.Role;
import ua.nure.sereda.SummaryTask4.models.User;
import ua.nure.sereda.SummaryTask4.service.impl.UserServiceImpl;
import ua.nure.sereda.SummaryTask4.utils.PassEncryption;
import ua.nure.sereda.SummaryTask4.web.Services;
import ua.nure.sereda.SummaryTask4.web.command.reader.EditUserCommand;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EditUserCommandTest {

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
    private EditUserCommand editUserCommand;

    private User user;

    @Before
    public void setUp() {
        user = new User("Sasha", "Sasha@gmail.com", Role.LIBRARIAN, PassEncryption.encrypt("Sasha123"));
    }

    @Test
    public void test_Execute_changeName() throws Exception {
        final String NEW_NAME = "Oleksandr";

        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("password")).thenReturn("Sasha123");
        when(request.getParameter("field")).thenReturn("name");
        when(request.getParameter("name")).thenReturn(NEW_NAME);

        editUserCommand.execute(request, response);
        assertEquals(NEW_NAME, user.getName());
        verify(response).setStatus(200);
    }

    @Test
    public void test_Execute_changeName_invalidInput() throws Exception {
        final String NEW_NAME = "_sasha1";

        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("password")).thenReturn("Sasha123");
        when(request.getParameter("field")).thenReturn("name");
        when(request.getParameter("name")).thenReturn(NEW_NAME);

        editUserCommand.execute(request, response);
        assertNotEquals(NEW_NAME, user.getName());
        verify(response).setStatus(400);
    }


    @Test
    public void test_Execute_changeEmail() throws Exception {
        final String NEW_EMAIL = "Oleksandr@mail.ru";

        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("password")).thenReturn("Sasha123");
        when(request.getParameter("field")).thenReturn("email");
        when(request.getParameter("email")).thenReturn(NEW_EMAIL);

        editUserCommand.execute(request, response);
        assertEquals(NEW_EMAIL, user.getEmail());
        verify(response).setStatus(200);
    }

    @Test
    public void test_Execute_changeEmail_invalidInput() throws Exception {
        final String NEW_EMAIL = "wrong@email";

        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("password")).thenReturn("Sasha123");
        when(request.getParameter("field")).thenReturn("email");
        when(request.getParameter("email")).thenReturn(NEW_EMAIL);

        editUserCommand.execute(request, response);
        assertNotEquals(NEW_EMAIL, user.getEmail());
        verify(response).setStatus(400);
    }

    @Test
    public void test_Execute_changePassword() throws Exception {
        final String NEW_PASSWORD1 = "123Sasha";
        final String NEW_PASSWORD2 = "123Sasha";

        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("password")).thenReturn("Sasha123");
        when(request.getParameter("field")).thenReturn("password");
        when(request.getParameter("newPassword1")).thenReturn(NEW_PASSWORD1);
        when(request.getParameter("newPassword2")).thenReturn(NEW_PASSWORD2);

        editUserCommand.execute(request, response);
        assertEquals(PassEncryption.encrypt(NEW_PASSWORD1), user.getPassword());
        verify(response).setStatus(200);
    }

    @Test
    public void test_Execute_changePassword_invalidInput() throws Exception {
        final String NEW_PASSWORD1 = "1234";
        final String NEW_PASSWORD2 = "1234";


        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("password")).thenReturn("Sasha123");
        when(request.getParameter("field")).thenReturn("password");
        when(request.getParameter("newPassword1")).thenReturn(NEW_PASSWORD1);
        when(request.getParameter("newPassword2")).thenReturn(NEW_PASSWORD2);

        editUserCommand.execute(request, response);
        assertNotEquals(PassEncryption.encrypt(NEW_PASSWORD1), user.getPassword());
        verify(response).setStatus(400);
    }

    @Test
    public void test_Execute_changePassword_confirmationDoesntMatch() throws Exception {
        final String NEW_PASSWORD1 = "123Sasha";
        final String NEW_PASSWORD2 = "321Sasha";


        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("password")).thenReturn("Sasha123");
        when(request.getParameter("field")).thenReturn("password");
        when(request.getParameter("newPassword1")).thenReturn(NEW_PASSWORD1);
        when(request.getParameter("newPassword2")).thenReturn(NEW_PASSWORD2);

        editUserCommand.execute(request, response);
        assertNotEquals(PassEncryption.encrypt(NEW_PASSWORD1), user.getPassword());
        verify(response).setStatus(400);
    }


    @Test
    public void test_Execute_fieldEmpty() throws Exception {

        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("password")).thenReturn("Sasha123");
        when(request.getParameter("field")).thenReturn("");

        editUserCommand.execute(request, response);
    }

    @Test
    public void test_Execute_wrongPassword() throws Exception {

        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("password")).thenReturn("wrongPass");

        editUserCommand.execute(request, response);
        verify(response).setStatus(423);
    }

    @Test
    public void test_Execute_invalidPassword() throws Exception {

        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("password")).thenReturn("123");

        editUserCommand.execute(request, response);
        verify(response).setStatus(400);
    }

    @Test
    public void test_Execute_noUser() throws Exception {

        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(null);

        editUserCommand.execute(request, response);
        verify(response).setStatus(401);
    }

    @Test(expected = AppException.class)
    public void test_Execute_serviceException() throws Exception {
        final String NEW_EMAIL = "Oleksandr@mail.ru";

        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute(Services.USER)).thenReturn(userService);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("password")).thenReturn("Sasha123");
        when(request.getParameter("field")).thenReturn("email");
        when(request.getParameter("email")).thenReturn(NEW_EMAIL);
        Mockito.doThrow(new ServiceException(new Throwable())).when(userService).update(user);

        editUserCommand.execute(request, response);
    }
}
