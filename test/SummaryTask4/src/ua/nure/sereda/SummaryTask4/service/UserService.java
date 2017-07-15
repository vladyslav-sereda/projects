package ua.nure.sereda.SummaryTask4.service;

import ua.nure.sereda.SummaryTask4.exceptions.BannedUserException;
import ua.nure.sereda.SummaryTask4.exceptions.ServiceException;
import ua.nure.sereda.SummaryTask4.exceptions.WrongEmailException;
import ua.nure.sereda.SummaryTask4.exceptions.WrongPasswordException;
import ua.nure.sereda.SummaryTask4.models.Order;
import ua.nure.sereda.SummaryTask4.models.User;

import java.util.List;

/**
 * Created by Vladyslav.
 */
public interface UserService {

    void signUpReader(String email, String name, String password) throws ServiceException;

    void createLibrarian(String email, String name, String password) throws ServiceException;

    void update(User user) throws ServiceException;

    void delete(int id) throws ServiceException;

    void banUser(int id) throws ServiceException;

    void unBanUser(int id) throws ServiceException;

    User getByEmail(String email) throws ServiceException;

    User getById(int id) throws ServiceException;

    List<User> getUsersByOrders(List<Order> orders) throws  ServiceException;

    List<User> getLibrarians() throws ServiceException;

    List<User> getBannedUsers() throws ServiceException;

    User login(String email, String password)
            throws ServiceException, WrongPasswordException, WrongEmailException, BannedUserException;

}
