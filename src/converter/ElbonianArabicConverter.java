package converter;

import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;
import javafx.scene.Scene;

/**
 * This class implements a converter that takes a string that represents a number in either the
 * Elbonian or Arabic numeral form. This class has methods that will return a value in the chosen form.
 *
 * @version 3/18/17
 */
public class ElbonianArabicConverter {

    // A string that holds the number (Elbonian or Arabic) you would like to convert
    private final String number;
    private final char[] validCharsInOrder = {'N', 'M', 'D', 'C', 'Y', 'X', 'J', 'I'};
    private final int[] ElbonianCharVals = {3000, 1000, 300, 100, 30, 10, 3, 1};

    /**
     * Constructor for the ElbonianArabic class that takes a string. The string should contain a valid
     * Elbonian or Arabic numeral. The String can have leading or trailing spaces. But there should be no
     * spaces within the actual number (ie. "9 9" is not ok, but " 99 " is ok). If the String is an Arabic
     * number it should be checked to make sure it is within the Elbonian number systems bounds. If the
     * number is Elbonian, it must be a valid Elbonian representation of a number.
     *
     * @param number A string that represents either a Elbonian or Arabic number.
     * @throws MalformedNumberException  Thrown if the value is an Elbonian number that does not conform
     *                                   to the rules of the Elbonian number system. Leading and trailing spaces should not throw an error.
     * @throws ValueOutOfBoundsException Thrown if the value is an Arabic number that cannot be represented
     *                                   in the Elbonian number system.
     */
    public ElbonianArabicConverter(String number) throws MalformedNumberException, ValueOutOfBoundsException {
        boolean isArabic = false;
        String tempNum;
        // TODO check to see if the number is valid, then set it equal to the string

        /** remove leading and trailing spaces */
        tempNum = number.trim();

        /** check empty */
        checkEmpty(tempNum);

        /** check for illegal space */
        checkIllegalSpace(tempNum);

        // TODO Check Type (AR or EL)
        try {
            Integer.parseInt(tempNum);
            isArabic = true;
        } catch (Exception e) {
        }

        if (isArabic) {
            checkArabicBoundries(tempNum);
        } else {
            checkElbonianBoundaries(tempNum);
            validateOrder(tempNum);
        }
        this.number = tempNum;
    }


    /**
     * Converts the number to an Arabic numeral or returns the current value as an int if it is already
     * in the Arabic form.
     *
     * @return An arabic value
     */
    public int toArabic() throws MalformedNumberException{
        int count = 0;
        char[] charArray = number.toCharArray();

        for (char c : charArray) {
            count += getElbonianCharValue(c);
        }

        return count;
    }

    /**
     * Converts the number to an Elbonian numeral or returns the current value if it is already in the Elbonian form.
     *
     * @return An Elbonian value
     */
    public String toElbonian() {
        int number = Integer.parseInt(this.number);
        String roman = "";
        while(number >= 3000){
            roman += "N";
            number -= 3000;
        }
        while(number >= 1000){
            roman += "M";
            number -= 1000;
        }
        while(number >= 300){
            roman += "D";
            number -= 300;
        }
        while(number >= 100){
            roman += "C";
            number -= 100;
        }
        while(number >= 30) {
            roman += "Y";
            number -= 30;
        }
        while(number >= 10){
            roman += "X";
            number -= 10;
        }
        while(number >= 3){
            roman += "J";
            number -= 3;
        }
        while(number >= 1){
            roman += "I";
            number -= 1;
        }
        return roman;
    }

    private void checkEmpty(String number) throws MalformedNumberException {
        if (number.isEmpty()) throw new MalformedNumberException("No Input");
    }

    private void checkIllegalSpace(String number) throws MalformedNumberException {
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == ' ') {
                throw new MalformedNumberException("ERROR: Improper Spacing");
            }
        }
    }

    private void checkArabicBoundries(String number) throws ValueOutOfBoundsException {
        int number1 = Integer.parseInt(number);
        if (number1 >= 10000 || number1 == 0) {
            throw new ValueOutOfBoundsException("ERROR: Out of Arabic Bounds");
        }
    }

    public void checkElbonianBoundaries(String number) throws MalformedNumberException {
        int m = 0;
        int c = 0;
        int x = 0;
        int i = 0;
        int n = 0;
        int d = 0;
        int y = 0;
        int j = 0;
        for (int z = 0; z < number.length(); z++) {
            switch (number.charAt(z)) {
                case 'M':
                    m++;
                    break;
                case 'C':
                    c++;
                    break;
                case 'X':
                    x++;
                    break;
                case 'I':
                    i++;
                    break;
                case 'N':
                    n++;
                    break;
                case 'D':
                    d++;
                    break;
                case 'Y':
                    y++;
                    break;
                case 'J':
                    j++;
                    break;
                default:
                    throw new MalformedNumberException("ERROR: Invalid Numeral Used");
            }
        }
        /*
        M, C, X, and I. These letters can only appear a maximum of two times in a number.
        N, D, Y, and J. These letters can only appear a maximum of three times in a number.
        If N appears three times then the letter M cannot be used.
        If D appears three times then the letter C cannot be used.
        If Y appears three times then the letter X cannot be used.
        If J appears three times then the letter I cannot be used.

        Numbers are represented by the letters from the greatest value down to the lowest value.
        In other words, the letter I would never appear before the letters M, D, X, or J.
        The letter D would never appear before N or M but would appear before Y. The letters are summed together to determine the value.
         */
        if (m > 2 || c > 2 || x > 2 || i > 2)
            throw new MalformedNumberException("ERROR: More than 2 of one of the following: [M,C,X,I]");
        if (n > 3 || d > 3 || y > 3 || j > 3)
            throw new MalformedNumberException("ERROR: More than 3 of one of the following: [M,C,X,I]");
        if (n == 3 && m > 0) throw new MalformedNumberException("ERROR: Can't have an M if you have 3 N's");
        if (d == 3 && c > 0) throw new MalformedNumberException("ERROR: Can't have a C if you have 3 D's");
        if (y == 3 && x > 0) throw new MalformedNumberException("ERROR: Can't have an X if you have 3 Y's");
        if (j == 3 && i > 0) throw new MalformedNumberException("ERROR: Can't have an I if you have 3 J's");
    }

    private void validateOrder(String number) throws MalformedNumberException {
        for (int i = 0; i < number.length() - 1; i++) {
            if (getElbonianCharValue(number.charAt(i)) < getElbonianCharValue(number.charAt(i+1)))
            {
                throw new MalformedNumberException("ERROR: Improper ordering of Numerals");
            }
        }
    }

    private int getElbonianCharValue(char c) throws MalformedNumberException {
        switch (c) {
            case 'M':
                return 1000;
            case 'C':
                return 100;
            case 'X':
                return 10;
            case 'I':
                return 1;
            case 'N':
                return 3000;
            case 'D':
                return 300;
            case 'Y':
                return 30;
            case 'J':
                return 3;
            default:
                throw new MalformedNumberException("ERROR: Invalid Numeral Used");
        }
    }

    public String getNumber() {
        return number;
    }
}
