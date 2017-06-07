package edu.hm.rfurch.shareit.model;

import java.util.Optional;

import javax.persistence.Entity;

/**
 * 
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu
 *
 */
@Entity
public class Book extends BaseMedium implements IBook {
    /**
     * Ctor to create a book object with title, author and isbn.
     * @param title of the book
     * @param author of the book
     * @param isbn of the book
     */
    public Book(String title, String author, String isbn) {
        super(title);
        if (isbn == null) {
            throw new IllegalArgumentException("ISBN = null is bad.");
        }
        //if (!IBook.validISBN(isbn)) {
        //    throw new IllegalArgumentException("ISBN is invalid");
        //}
        
        this.author = author;
        this.isbn = isbn.replace("-","");
    }

    /**
     * Ctor used by jackson and hibernate.
     */
    @SuppressWarnings("unused") // Constructor for Reflection
    Book() {
        this("", "", "");
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
    public Optional<IBook> update(IBook book) {
        Optional<IBook> result = Optional.empty();
        if (book != null && this.getIsbn().equals(book.getIsbn())) {
            result = Optional.of(new Book(
                    book.getTitle() != null ? book.getTitle() : this.getTitle(),
                    book.getAuthor() != null ? book.getAuthor() : this.getAuthor(),
                    this.getIsbn()));
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Book book = (Book) o;

        if (!author.equals(book.author)) {
            return false;
        }
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
        return new StringBuilder()
                .append("ISBN: " + this.getIsbn() + " - ")
            .append("Title: " + this.getTitle() + " - ")
            .append("Author: " + this.getAuthor()).toString();
    }
}
