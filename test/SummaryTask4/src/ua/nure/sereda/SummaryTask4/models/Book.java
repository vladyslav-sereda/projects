package ua.nure.sereda.SummaryTask4.models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Vladyslav.
 */
public class Book implements Serializable{

    private static final long serialVersionUID = -4491030264212748768L;
    private int id;
    private String name;
    private String author;
    private String publisher;
    private LocalDate publicationDate;
    private int available;

    public Book() {
    }

    public Book(String name, String author, String publisher, LocalDate publicationDate, int available) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", available=" + available +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publicationDate=" + publicationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (!getName().equals(book.getName())) return false;
        if (!getAuthor().equals(book.getAuthor())) return false;
        if (!getPublisher().equals(book.getPublisher())) return false;
        return getPublicationDate().equals(book.getPublicationDate());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getAuthor().hashCode();
        result = 31 * result + getPublisher().hashCode();
        result = 31 * result + getPublicationDate().hashCode();
        return result;
    }
}

