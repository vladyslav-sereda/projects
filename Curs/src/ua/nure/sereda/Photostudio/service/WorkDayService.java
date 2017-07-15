package ua.nure.sereda.Photostudio.service;

import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.models.WorkDay;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public interface WorkDayService {

    WorkDay addDay(LocalDate date, LocalTime startDay, LocalTime endDay) throws ServiceException;

    WorkDay getById(int dayId) throws ServiceException;

    List<WorkDay> getAll() throws ServiceException;

    List<WorkDay> getAllFromToday() throws ServiceException;

    void delete(int dayId) throws ServiceException;

    void update(WorkDay workDay) throws ServiceException;

    WorkDay getByDate (LocalDate date) throws ServiceException;

    boolean availableWorkDay (LocalDate date) throws ServiceException;

}
