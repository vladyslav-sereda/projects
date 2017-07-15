package ua.nure.sereda.Photostudio.utils.validation;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.exception.TransactionException;
import ua.nure.sereda.Photostudio.models.WorkDay;
import ua.nure.sereda.Photostudio.service.WorkDayService;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vladyslav.
 */
public class Validator {

    private static final Logger LOG = Logger.getLogger(Validator.class);
    private static Pattern pattern;
    private static Matcher matcher;

    /**
     * Checks if email is correct
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
     */
    public static boolean Name(String name) {
        if (name == null) {
            return false;
        }

        pattern = Pattern.compile("^[a-zA-Zа-яА-ЯїЇіІєЄ][a-zA-Zа-яА-ЯїЇіІєЄ `'-]{1,20}$");
        matcher = pattern.matcher(name.trim());
        LOG.trace("Name '" + name + "' is " + (matcher.matches() ? "valid" : "invalid"));

        return matcher.matches();
    }

    /**
     * Checks if phone is correct
     */
    public static boolean Phone(String phone) {
        if (phone == null) {
            return false;
        }

        pattern = Pattern.compile("^[+]?[0-9 ]{6,17}$");
        matcher = pattern.matcher(phone.trim());
        LOG.trace("Phone '" + phone + "' is " + (matcher.matches() ? "valid" : "invalid"));

        return matcher.matches();
    }

    /**
     * Checks if password is correct
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
     * Checks if date after today
     */
    public static boolean dateAfterToday(LocalDate date) {

        if (date.isBefore(LocalDate.now())) {
            LOG.error("Date: '" + date + "' is invalid");
            return false;
        }

        LOG.trace("Date: '" + date + "' is valid");

        return true;
    }


    /**
     * Checks if time is correct
     */
    public static boolean time(LocalTime from, LocalTime to) {

        if (to.getHour() <= from.getHour()) {
            LOG.error("Time from: '" + from + "' and to: '" + to + "' is invalid");
            return false;
        }

        LOG.trace("Time from: '" + from + "' and to: '" + to + "' is valid");

        return true;
    }
}
