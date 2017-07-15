package ua.nure.sereda.Photostudio.dao;

import ua.nure.sereda.Photostudio.exception.DaoException;
import ua.nure.sereda.Photostudio.models.WorkDay;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by sered on 11.05.2017.
 */
public interface WorkDayDao extends Dao<WorkDay>{

    WorkDay getWorkDayByDate (LocalDate date) throws DaoException;

    List<WorkDay> getWorkDaysFromToday () throws DaoException;

}
