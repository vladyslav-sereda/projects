package ua.nure.sereda.Photostudio.web.command;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.web.command.client.AccountCommand;
import ua.nure.sereda.Photostudio.web.command.client.DeleteReserveCommand;
import ua.nure.sereda.Photostudio.web.command.client.EditUserCommand;
import ua.nure.sereda.Photostudio.web.command.common.ReserveCommand;
import ua.nure.sereda.Photostudio.web.command.common.*;
import ua.nure.sereda.Photostudio.web.command.manager.*;

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
        commands.put("signIn", new SignInCommand());
        commands.put("signUp", new SignUpCommand());
        commands.put("redirect", new RedirectCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("index", new InitIndexCommand());

        //admin
        commands.put("addWorkday", new AddWorkdayCommand());
        commands.put("changeWorkday", new EditWorkdayCommand());
        commands.put("deleteWorkday", new DeleteWorkdayCommand());
        commands.put("changeReserveStatus", new ChangeReserveStatusCommand());
        commands.put("showAllUnpaidReserves", new showAllUnpaidReservesCommand());


        //client
        commands.put("reserve", new ReserveCommand());
        commands.put("deleteReserve", new DeleteReserveCommand());
        commands.put("account", new AccountCommand());
        commands.put("changeUserInf", new EditUserCommand());

        //no command
        commands.put("noCommand", new NoCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("No such command >> " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }
}
