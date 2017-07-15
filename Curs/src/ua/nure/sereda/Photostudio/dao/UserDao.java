package ua.nure.sereda.Photostudio.dao;

import ua.nure.sereda.Photostudio.exception.DaoException;
import ua.nure.sereda.Photostudio.models.User;

/**
 * Created by sered on 11.05.2017.
 */
public interface UserDao extends Dao<User> {

    User getUserByEmail(String email) throws DaoException;
}
