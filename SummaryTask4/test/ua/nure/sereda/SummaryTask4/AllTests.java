package ua.nure.sereda.SummaryTask4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import ua.nure.sereda.SummaryTask4.command.*;
import ua.nure.sereda.SummaryTask4.utils.PassEncryptionTest;
import ua.nure.sereda.SummaryTask4.utils.ValidatorTest;

@RunWith(Suite.class)
@SuiteClasses({PassEncryptionTest.class, ValidatorTest.class, EditUserCommandTest.class,
        SignInCommandTest.class, InitIndexCommandTest.class, LogoutCommandTest.class,
        NoCommandTest.class, RedirectCommandTest.class, SignUpCommandTest.class,})
public class AllTests {

}
