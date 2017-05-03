package edu.hm.rfurch.shareit.model;

import java.util.Optional;

/**
 * 
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu
 *
 */
public interface IDisc extends IMedium {
    /**
     * Length of an EAN.
     */
    int EAN_LENGTH = 13;
    /**
     * Multiplicator every second EAN number character is multiplied.
     */
    int EAN_MULTIPLICATOR = 3;
    /**
     * 10 to extract the last number.
     */
    int TEN = 10;
    /**
     * Getter to get the barcode of a disc.
     * @return barcode of the disc
     */
    String getBarcode();
    
    /**
     * Getter to get the director of a disc.
     * @return director of the disc
     */
    String getDirector();
    
    /**
     * Getter to get the fsk of a disc.
     * @return fsk of the disc
     */
    Integer getFsk();
    
    /**
     * Setter to update the values of the disc.
     * @param disc with the new values.
     * @return an Optional of a disc
     */
    Optional<IDisc> update(IDisc disc);
    
    /**
     * Check if the EAN is valid.
     * @param ean to check
     * @return if the EAN is valid
     */
    static boolean validEAN(String ean) {
        boolean result = false;
        try {
            if (ean.length() == EAN_LENGTH) {
                int[] numbers = ean.chars()
                        .map(character -> Integer.parseInt(Character.toString((char) character))).toArray();
                int checksum = 0;
                for (int index = 0; index < numbers.length - 1; index++) {
                    checksum += index % 2 == 0 ? numbers[index] : numbers[index] * EAN_MULTIPLICATOR;
                }
                checksum = (TEN - checksum % TEN) % TEN;
                result = checksum == numbers[numbers.length - 1];
            }
        } catch (NumberFormatException exception) {
            // result = false
        }
        return result;
    }
    
    /**
     * Check if the EAN of the disc is valid.
     * @return if the EAN of this disc is valid.
     */
    default boolean hasValidEAN() {
        return validEAN(getBarcode());
    }
    
    @Override
    default boolean isValid() {
        return hasValidEAN();
    }
}
