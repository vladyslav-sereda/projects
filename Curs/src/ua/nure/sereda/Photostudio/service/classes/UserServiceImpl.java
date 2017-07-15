package ua.nure.sereda.Photostudio.service.classes;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.dao.UserDao;
import ua.nure.sereda.Photostudio.db.TransactionManager;
import ua.nure.sereda.Photostudio.db.VoidTransaction;
import ua.nure.sereda.Photostudio.exception.*;
import ua.nure.sereda.Photostudio.models.User;
import ua.nure.sereda.Photostudio.service.UserService;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private TransactionManager transactionManager;
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    public UserServiceImpl(TransactionManager transactionManager, UserDao userDao) {
        this.transactionManager = transactionManager;
        this.userDao = userDao;
    }

    @Override
    public void addUser(String email, String name, String phone, String password) throws ServiceException {
        LOG.debug(String.format("Start with params: email = %s, name = %s, phone = %s, password = %s",
                email, name, phone, password));

        User user = new User(email, name, phone, password);
        try {
            transactionManager.doVoidTask(new VoidTransaction<Object>() {
                @Override
                public void executeVoid() throws DaoException {
                    userDao.create(user);
                }
            }, Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getById(int userId) throws ServiceException {
        LOG.debug("Start user id = " + userId);

        try {
            return transactionManager.doTask(() -> userDao.getById(userId), Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getByEmail(String email) throws ServiceException {
        LOG.debug("Start email = " + email);

        try {
            return transactionManager.doTask(() -> userDao.getUserByEmail(email),
                    Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        LOG.debug("Start");

        try {
            return transactionManager.doTask(() -> userDao.getAll(), Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(int userId) throws ServiceException {
        LOG.debug("Start user id = " + userId);

        try {
            transactionManager.doVoidTask(() -> userDao.remove(userId), Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(User user) throws ServiceException {
        LOG.debug("Start user >> " + user);

        try {
            transactionManager.doVoidTask(() -> userDao.update(user), Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean login(String email, String password) throws ServiceException, WrongEmailException, WrongPassException {
        LOG.debug(String.format("Start with params: email = %s, password = %s", email, password));
        User user;
        try {
            user = transactionManager.doTask(() -> userDao.getUserByEmail(email),
                    Connection.TRANSACTION_READ_COMMITTED);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    return true;
                } else {
                    throw new WrongPassException();
                }
            } else {
                throw new WrongEmailException();
            }
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }
}
