package ua.nure.sereda.Photostudio.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by sered on 11.05.2017.
 */
public class WorkDay implements Serializable {

    private static final long serialVersionUID = 8751221437353980942L;
    private int id;
    private LocalDate date;
    private LocalTime startDay;
    private LocalTime endDay;

    public WorkDay(){

    }

    public WorkDay(LocalDate date, LocalTime startDay, LocalTime endDay) {

        this.date = date;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalTime startDay) {
        this.startDay = startDay;
    }

    public LocalTime getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalTime endDay) {
        this.endDay = endDay;
    }

    @Override
    public String toString() {
        return "WorkDay{" +
                "id=" + id +
                ", startDay=" + startDay +
                ", endDay=" + endDay +
                '}';
    }
}
