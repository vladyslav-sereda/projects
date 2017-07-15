package ua.nure.sereda.SummaryTask4.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Vladyslav.
 */
public class Order implements Serializable, Comparable<Order>{

    private static final long serialVersionUID = 520823386880963863L;
    private int id;
    private int userId;
    private int bookId;
    private boolean readingRoom;
    private OrderStatus status;
    private LocalDate deadline;
    private BigDecimal penalty;

    public Order() {
    }

    public Order(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
        this.readingRoom = true;
        this.deadline = LocalDate.now();
        this.penalty = BigDecimal.ZERO;
    }

    public Order(int userId, int bookId, LocalDate deadline) {
        this.userId = userId;
        this.bookId = bookId;
        this.deadline = deadline;
        this.penalty = BigDecimal.ZERO;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public boolean isReadingRoom() {
        return readingRoom;
    }

    public void setReadingRoom(boolean readingRoom) {
        this.readingRoom = readingRoom;
        if(readingRoom){
            deadline = LocalDate.now();
        }
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setPenalty(BigDecimal penalty) {
        this.penalty = penalty;
    }

    public BigDecimal getPenalty() {
        return penalty;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", readingRoom=" + readingRoom +
                ", status=" + status +
                ", deadline=" + deadline +
                ", penalty=" + penalty +
                '}';
    }

    @Override
    public int compareTo(Order order) {
        return this.deadline.compareTo(order.getDeadline());
    }
}
