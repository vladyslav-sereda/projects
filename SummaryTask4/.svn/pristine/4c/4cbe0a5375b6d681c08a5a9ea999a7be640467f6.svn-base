package ua.nure.sereda.SummaryTask4.utils;

import ua.nure.sereda.SummaryTask4.models.Book;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class CatalogSorter {

    private static final Comparator<Book> SORT_BOOKS_BY_NAME = Comparator.comparing(Book::getName);

    private static final Comparator<Book> SORT_BOOKS_BY_AUTHOR = Comparator.comparing(Book::getAuthor);

    private static final Comparator<Book> SORT_BOOKS_BY_PUBLISHER = Comparator.comparing(Book::getPublisher);

    private static final Comparator<Book> SORT_BOOKS_BY_PUBLICATION_DATE = Comparator.comparing(Book::getPublicationDate);

    public void setSortBooksByName(List<Book> books) {
        books.sort(SORT_BOOKS_BY_NAME);
    }

    public void setSortBooksByAuthor(List<Book> books) {
        books.sort(SORT_BOOKS_BY_AUTHOR);
    }

    public void setSortBooksByPublisher(List<Book> books) {
        books.sort(SORT_BOOKS_BY_PUBLISHER);
    }

    public void setSortBooksByPublicationDate(List<Book> books) {
        books.sort(SORT_BOOKS_BY_PUBLICATION_DATE);
    }
}
