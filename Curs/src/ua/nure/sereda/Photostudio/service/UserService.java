package ua.nure.sereda.Photostudio.service;

import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.exception.WrongEmailException;
import ua.nure.sereda.Photostudio.exception.WrongPassException;
import ua.nure.sereda.Photostudio.models.User;

import java.util.List;

/**
 * Created by Vladyslav.
 */
public interface UserService {

    void addUser(String email, String name, String phone, String password) throws ServiceException;

    User getById(int userId) throws ServiceException;

    User getByEmail(String email) throws ServiceException;

    List<User> getAll() throws ServiceException;

    void delete(int userId) throws ServiceException;

    void update(User user) throws ServiceException;

    boolean login(String email, String password) throws ServiceException, WrongPassException, WrongEmailException;

}
