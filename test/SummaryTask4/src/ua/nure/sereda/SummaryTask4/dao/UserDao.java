package ua.nure.sereda.SummaryTask4.dao;

import ua.nure.sereda.SummaryTask4.exceptions.DaoException;
import ua.nure.sereda.SummaryTask4.models.User;

import java.util.List;

/**
 * Created by Vladyslav.
 */
public interface UserDao extends Dao<User> {

    User getUserByEmail(String email) throws DaoException;

    List<User> getLibrarians() throws DaoException;

}
