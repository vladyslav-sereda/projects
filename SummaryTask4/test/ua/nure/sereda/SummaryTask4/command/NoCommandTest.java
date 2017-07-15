package ua.nure.sereda.SummaryTask4.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.sereda.SummaryTask4.exception.AppException;
import ua.nure.sereda.SummaryTask4.web.Path;
import ua.nure.sereda.SummaryTask4.web.command.common.NoCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class NoCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private NoCommand noCommand;

    @Test
    public void test_execute() throws AppException {
        String expected = Path.PAGE_ERROR_PAGE;
        String actual = noCommand.execute(request, response);
        assertEquals(expected, actual);
    }
}
