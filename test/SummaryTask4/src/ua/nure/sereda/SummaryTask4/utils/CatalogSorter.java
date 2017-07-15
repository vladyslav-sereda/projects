package ua.nure.sereda.SummaryTask4.utils;

import ua.nure.sereda.SummaryTask4.models.Book;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class CatalogSorter {

    private static final Comparator<Book> SORT_BOOKS_BY_NAME = new Comparator<Book>() {
        @Override
        public int compare(Book book1, Book book2) {
            return book1.getName().compareTo(book2.getName());
        }
    };

    private static final Comparator<Book> SORT_BOOKS_BY_AUTHOR= new Comparator<Book>() {
        @Override
        public int compare(Book book1, Book book2) {
            return book1.getAuthor().compareTo(book2.getAuthor());
        }
    };

    private static final Comparator<Book> SORT_BOOKS_BY_PUBLISHER = new Comparator<Book>() {
        @Override
        public int compare(Book book1, Book book2) {
            return book1.getPublisher().compareTo(book2.getPublisher());
        }
    };

    private static final Comparator<Book> SORT_BOOKS_BY_PUBLICATION_DATE = new Comparator<Book>() {
        @Override
        public int compare(Book book1, Book book2) {
            return book1.getPublicationDate().compareTo(book2.getPublicationDate());
        }
    };

    public void setSortBooksByName(List<Book> books){
        books.sort(SORT_BOOKS_BY_NAME);
    }

    public void setSortBooksByAuthor (List<Book> books){
        books.sort(SORT_BOOKS_BY_AUTHOR);
    }

    public void setSortBooksByPublisher(List<Book> books){
        books.sort(SORT_BOOKS_BY_PUBLISHER);
    }

    public void setSortBooksByPublicationDate(List<Book> books){
        books.sort(SORT_BOOKS_BY_PUBLICATION_DATE);
    }
}
