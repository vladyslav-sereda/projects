package ua.nure.sereda.Photostudio.models;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * Created by sered on 10.05.2017.
 */
public class Reservation implements Serializable, Comparable<Reservation> {

    private static final long serialVersionUID = -2269160302571487103L;
    private int id;
    private int dayId;
    private int userId;
    private LocalTime startTime;
    private LocalTime endTime;
    private ReservationStatus status;
    private float price;


    public Reservation(int dayId, int userId, LocalTime startTime, LocalTime endTime, float price) {
        this.dayId = dayId;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public Reservation() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", userId=" + userId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Reservation o) {
        return o.getStartTime().isAfter(startTime) ? -1 : (o.getStartTime().isBefore(startTime)) ? 1 : 0;
    }
}
