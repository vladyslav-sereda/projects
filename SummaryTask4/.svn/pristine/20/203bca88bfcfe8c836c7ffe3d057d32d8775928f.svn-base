package ua.nure.sereda.SummaryTask4.service.impl;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.dao.UserDao;
import ua.nure.sereda.SummaryTask4.db.TransactionManager;
import ua.nure.sereda.SummaryTask4.exception.ServiceException;
import ua.nure.sereda.SummaryTask4.exception.TransactionException;
import ua.nure.sereda.SummaryTask4.exception.WrongEmailException;
import ua.nure.sereda.SummaryTask4.exception.WrongPasswordException;
import ua.nure.sereda.SummaryTask4.models.Order;
import ua.nure.sereda.SummaryTask4.models.Role;
import ua.nure.sereda.SummaryTask4.models.User;
import ua.nure.sereda.SummaryTask4.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class UserServiceImpl implements UserService {
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);
    private UserDao userDao;
    private TransactionManager transactionManager;

    public UserServiceImpl(TransactionManager transactionManager, UserDao userDao) {
        this.transactionManager = transactionManager;
        this.userDao = userDao;
    }

    @Override
    public void signUpReader(String name, String email, String password) throws ServiceException {
        LOG.debug(String.format("Start with params: email = %s, name = %s, password = %s",
                email, name, password));

        User user = new User(name, email, Role.READER, password);
        try {
            transactionManager.task(() -> userDao.create(user));
        } catch (TransactionException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public void createLibrarian(String name, String email, String password) throws ServiceException {
        LOG.debug(String.format("Start with params: email = %s, name = %s, password = %s",
                email, name, password));

        User user = new User(name, email, Role.LIBRARIAN, password);
        try {
            transactionManager.task(() -> userDao.create(user));
        } catch (TransactionException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public void update(User user) throws ServiceException {
        LOG.debug("Start update, user -> " + user);

        try {
            transactionManager.task(() -> {
                userDao.update(user);
                return null;
            });
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        LOG.debug("Start delete user, id = " + id);

        try {
            transactionManager.task(() -> {
                userDao.delete(id);
                return null;
            });
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void banUser(int id) throws ServiceException {
        LOG.debug("Start ban user, id = " + id);

        try {
            transactionManager.task(() -> {
                User user = userDao.getById(id);
                user.setBanned(true);
                userDao.update(user);
                return null;
            });
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void unBanUser(int id) throws ServiceException {
        LOG.debug("Start allow user, id = " + id);

        try {
            transactionManager.task(() -> {
                User user = userDao.getById(id);
                user.setBanned(false);
                userDao.update(user);
                return null;
            });
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getByEmail(String email) throws ServiceException {
        LOG.debug("Start get by email = " + email);

        try {
            return transactionManager.task(() -> userDao.getUserByEmail(email));
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getById(int id) throws ServiceException {
        LOG.debug("Start get by id = " + id);

        try {
            return transactionManager.task(() -> userDao.getById(id));
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getUsersByOrders(List<Order> orders) throws ServiceException {
        LOG.debug("Start");

        try {
            return transactionManager.task(() -> {
                List<User> users = new ArrayList<>();
                User user;
                for (Order order : orders) {
                    user = userDao.getById(order.getUserId());
                    if (user != null && !users.contains(user)) {
                        users.add(user);
                    }
                }
                return users;
            });
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getLibrarians() throws ServiceException {
        LOG.debug("Start");

        try {
            return transactionManager.task(() -> userDao.getLibrarians());
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getBannedUsers() throws ServiceException {
        LOG.debug("Start");
        List<User> allUsers;
        List<User> bannedUsers = new ArrayList<>();
        try {
            allUsers = transactionManager.task(() -> userDao.getAll());
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
        if (allUsers != null) {
            for (User user : allUsers) {
                if (user.isBanned()) {
                    bannedUsers.add(user);
                }
            }
        }
        return bannedUsers;
    }

    @Override
    public User login(String email, String password)
            throws ServiceException, WrongPasswordException, WrongEmailException {
        LOG.debug(String.format("Start with params: email = %s, password = %s", email, password));
        User user;
        try {
            user = transactionManager.task(() -> userDao.getUserByEmail(email));
            if (user != null) {
                if (!user.getPassword().equals(password)) {
                    throw new WrongPasswordException();
                } else {
                    return user;
                }
            } else {
                throw new WrongEmailException();
            }
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }
}
