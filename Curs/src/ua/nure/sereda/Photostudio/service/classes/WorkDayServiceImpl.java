package ua.nure.sereda.Photostudio.service.classes;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.dao.WorkDayDao;
import ua.nure.sereda.Photostudio.dao.classes.WorkDayDaoImpl;
import ua.nure.sereda.Photostudio.db.TransactionManager;
import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.exception.TransactionException;
import ua.nure.sereda.Photostudio.models.WorkDay;
import ua.nure.sereda.Photostudio.service.WorkDayService;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class WorkDayServiceImpl implements WorkDayService {

    private WorkDayDao workDayDao;
    private TransactionManager transactionManager;
    private static final Logger LOG = Logger.getLogger(WorkDayDaoImpl.class);

    public WorkDayServiceImpl(TransactionManager transactionManager, WorkDayDao workDayDao) {
        this.transactionManager = transactionManager;
        this.workDayDao = workDayDao;
    }

    @Override
    public WorkDay addDay(LocalDate date, LocalTime startDay, LocalTime endDay) throws ServiceException {
        LOG.debug(String.format("Start with params: date = %s, startDay = %s, endDay = %s ",
                date, startDay, endDay));

        WorkDay workDay = new WorkDay(date, startDay, endDay);
        try {
            return transactionManager.doTask(() -> workDayDao.create(workDay), Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public WorkDay getById(int dayId) throws ServiceException {
        LOG.debug("Start day id = " + dayId);

        try {
            return transactionManager.doTask(() -> workDayDao.getById(dayId), Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<WorkDay> getAll() throws ServiceException {
        LOG.debug("Start");

        try {
            return transactionManager.doTask(() -> workDayDao.getAll(), Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<WorkDay> getAllFromToday() throws ServiceException {
        LOG.debug("Start");

        try {
            return transactionManager.doTask(() -> workDayDao.getWorkDaysFromToday(), Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(int dayId) throws ServiceException {
        LOG.debug("Start day id = " + dayId);

        try {
            transactionManager.doVoidTask(() -> workDayDao.remove(dayId), Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(WorkDay workDay) throws ServiceException {
        LOG.debug("Start workday >> " + workDay);

        try {
            transactionManager.doVoidTask(() -> workDayDao.update(workDay), Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public WorkDay getByDate(LocalDate date) throws ServiceException {
        LOG.debug("Start date >> " + date);

        try {
            return transactionManager.doTask(() -> workDayDao.getWorkDayByDate(date), Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean availableWorkDay(LocalDate date) throws ServiceException{
        LOG.debug(String.format("Start with param date = %s ", date));

        try {
            WorkDay unAvailableDay = transactionManager.doTask(() -> workDayDao.getWorkDayByDate(date),
                    Connection.TRANSACTION_READ_COMMITTED);
            if (unAvailableDay == null) {
                return true;
            }
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
        return false;  }
}
