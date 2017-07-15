package ua.nure.sereda.Photostudio.models;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * Created by Vladyslav.
 */
public class FreeTimeForReservation implements Serializable, Comparable<FreeTimeForReservation>{
    private static final long serialVersionUID = -1827792559124052985L;
    private LocalTime startTime;
    private LocalTime endTime;

    public FreeTimeForReservation(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
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

    @Override
    public String toString() {
        return "FreeTimeForReservation{" +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    @Override
    public int compareTo(FreeTimeForReservation o) {
        return o.getStartTime().isAfter(startTime) ? -1 : (o.getStartTime().isBefore(startTime)) ? 1 : 0;
    }
}
