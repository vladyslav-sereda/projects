package ua.nure.sereda.SummaryTask4.utils;

import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vladyslav.
 */
public class Validator {
    public static final Logger LOG = Logger.getLogger(Validator.class);
    private static Pattern pattern;
    private static Matcher matcher;

    /**
     * Checks if email is correct
     *
     * @param email string
     * @return boolean
     */
    public static boolean Email(String email) {
        if (email == null) {
            return false;
        }
        pattern = Pattern.compile("^[a-z0-9._-]*@[a-z0-9]+\\.[a-z0-9]+(.[a-z0-9]+)?$");
        matcher = pattern.matcher(email.trim().toLowerCase());
        LOG.trace("Email '" + email + "' is " + (matcher.matches() ? "valid" : "invalid"));
        return matcher.matches();
    }

    /**
     * Checks if name is correct
     *
     * @param name string
     * @return bool
     */
    public static boolean Name(String name) {
        if (name == null) {
            return false;
        }

        pattern = Pattern.compile("^[a-zA-Zа-яА-ЯїЇіІєЄ][a-zA-Zа-яА-ЯїЇіІєЄ`'-]{1,20}$");
        matcher = pattern.matcher(name.trim());
        LOG.trace("Name '" + name + "' is " + (matcher.matches() ? "valid" : "invalid"));

        return matcher.matches();
    }

    /**
     * Checks if password is correct
     *
     * @param password string
     * @return bool
     */
    public static boolean Password(String password) {
        if (password == null) {
            return false;
        }

        pattern = Pattern.compile("^.{6,25}$");
        matcher = pattern.matcher(password.trim());
        LOG.trace("Password is " + (matcher.matches() ? "valid" : "invalid"));

        return matcher.matches();
    }

    /**
     * Checks if deadline is correct
     *
     * @param deadline date to validate
     * @return boolean
     */
    public static boolean Deadline(LocalDate deadline) {
        if (deadline == null) {
            return false;
        }

        if (deadline.isBefore(LocalDate.now())) {
            LOG.error("Deadline: '" + deadline + "' is invalid");
            return false;
        }
        LOG.trace("Deadline: '" + deadline + "' is  valid");

        return true;
    }
}
