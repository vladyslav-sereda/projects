package ua.nure.sereda.SummaryTask4.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.sereda.SummaryTask4.web.Path;
import ua.nure.sereda.SummaryTask4.web.command.common.InitIndexCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class InitIndexCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private InitIndexCommand indexCommand;

    @Test
    public void test_execute() {
        String expected = Path.PAGE_INDEX;
        String actual = indexCommand.execute(request, response);
        assertEquals(expected, actual);
    }
}
