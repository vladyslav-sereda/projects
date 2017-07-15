package ua.nure.sereda.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.web.command.admin.*;
import ua.nure.sereda.SummaryTask4.web.command.common.*;
import ua.nure.sereda.SummaryTask4.web.command.librarian.CloseOrderCommand;
import ua.nure.sereda.SummaryTask4.web.command.librarian.ConfirmOrderStatusCommand;
import ua.nure.sereda.SummaryTask4.web.command.reader.AccountCommand;
import ua.nure.sereda.SummaryTask4.web.command.reader.EditUserCommand;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Vladyslav.
 */
public class CommandContainer {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);
    private static Map<String, Command> commands = new TreeMap<>();

    static {
        //common
        commands.put("index", new InitIndexCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("redirect", new RedirectCommand());
        commands.put("signIn", new SignInCommand());
        commands.put("signUp", new SignUpCommand());
        commands.put("showAvailableBooks", new ShowAvailableBooks());


        //admin
        commands.put("addBook", new AddBookCommand());
        commands.put("showBookEditForm", new ShowBookEditForm());
        commands.put("editBook", new EditBookCommand());
        commands.put("createLibrarian", new CreateLibrarianCommand());
        commands.put("deleteLibrarian", new DeleteLibrarianCommand());
        commands.put("banUser", new BanUserCommand());
        commands.put("unBanUser", new UnBanUserCommand());
        commands.put("showAllBooks", new ShowAllBooks());

        //librarian
        commands.put("confirmOrderStatus", new ConfirmOrderStatusCommand());
        commands.put("closeOrder", new CloseOrderCommand());

        //reader
        commands.put("account", new AccountCommand());
        commands.put("changeUser", new EditUserCommand());

        //no command
        commands.put("noCommand", new NoCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("No such command -> " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }
}
