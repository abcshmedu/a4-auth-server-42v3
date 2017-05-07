package edu.hm.rfurch.shareit.model;

import java.util.Optional;

/**
 * 
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu
 *
 */
public interface IBook extends IMedium {
    /**
     * Length of a ISBN number.
     */
    int ISBN_LENGTH = 13;
    /**
     * Number of parts of a ISBN number. 
     */
    int ISBN_PARTS = 5;
    /**
     * Multiplicator every second ISBN number character is multiplied.
     */
    int ISBN_MULTIPLICATOR = 3;
    /**
     * 10 to extract the last number.
     */
    int TEN = 10;
    /**
     * Getter to get the author of a book.
     * @return author of the book
     */
    String getAuthor();
    
    /**
     * Getter to get the isbn of a book.
     * @return isbn of the book
     */
    String getIsbn();
    
    /**
     * Setter to update the values of the book.
     * @param medium with the new values.
     * @return an Optional of a book
     */
    Optional<IBook> update(IBook medium);
    
    /**
     * Checks if the ISBN is valid.
     * @param isbn to check
     * @return if ISBN is valid
     */
    static boolean validISBN(String isbn) {
        boolean result = false;
        try {
            if (isbn.split("-").length <= ISBN_PARTS && isbn.replace("-", "").length() == ISBN_LENGTH) {
                String isbnNumbers = isbn.replace("-", "");
                int[] numbers = isbnNumbers.chars()
                        .map(character -> Integer.parseInt(Character.toString((char) character))).toArray();
                int checksum = 0;
                for (int index = 0; index < numbers.length - 1; index++) {
                    checksum += index % 2 == 0 ? numbers[index] : numbers[index] * ISBN_MULTIPLICATOR;
                }
                checksum = (TEN - checksum % TEN) % TEN;
                result = checksum == numbers[numbers.length - 1];
            }
        } catch (NumberFormatException exception) {
            //result = false
        }
        return result;
    }
    
    /**
     * Check if the ISBN of the book is valid.
     * @return if the ISBN is valid
     */
    default boolean hasValidISBN() {
        return validISBN(getIsbn());
    }
    
    @Override
    default boolean valid() {
        return hasValidISBN();
    }
}
