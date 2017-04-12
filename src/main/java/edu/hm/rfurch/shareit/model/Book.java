package edu.hm.rfurch.shareit.model;

import java.util.Objects;

/**
 * Created by rapha on 12.04.2017.
 */
public class Book extends BaseMedium implements IBook{
    public Book(String title, String author, String isbn) {
        super(title);
        this.author = author;
        this.isbn = isbn;
    }

    private Book(){
        this("","","");
    }


    private final String author;
    @Override
    public String getAuthor() {
       return this.author;
    }

    private final String isbn;
    @Override
    public String getIsbn() {
        return this.isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!author.equals(book.author)) return false;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        int result = author.hashCode();
        result = 31 * result + isbn.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return null;
    }
}
