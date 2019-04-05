package converter;

import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;

import java.util.HashMap;
import java.util.Set;


/**
 * This class implements a converter that takes a string that represents a number in either the
 * Elbonian or Arabic numeral form. This class has methods that will return a value in the chosen form.
 *
 * @version 3/18/17
 */
public class ElbonianArabicConverter {

    // A string that holds the number (Elbonian or Arabic) you would like to convert
    private final String number;
    private HashMap<Character, Integer> getNumeralValue = new HashMap<>();

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
        constructHashmap();
        String tempNum;

        /** remove leading and trailing spaces */
        tempNum = number.trim();

        /** check empty */
        checkEmpty(tempNum);

        /** check for illegal space */
        checkIllegalSpace(tempNum);

        try {
            Integer.parseInt(tempNum);
            isArabic = true;
        } catch (Exception e) {
            checkElbonianBoundaries(tempNum);
            validateOrder(tempNum);
        }

        if (isArabic) {
            checkArabicBoundries(tempNum);
        }

        this.number = tempNum;
    }


    /**
     * Converts the number to an Arabic numeral or returns the current value as an int if it is already
     * in the Arabic form.
     *
     * @return An arabic value
     */
    public int toArabic() throws MalformedNumberException {
        int count = 0;

        for (char c : number.toCharArray()) {
            count += getNumeralValue.get(c);
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
        while (number >= 3000) {
            roman += "N";
            number -= 3000;
        }
        while (number >= 1000) {
            roman += "M";
            number -= 1000;
        }
        while (number >= 300) {
            roman += "D";
            number -= 300;
        }
        while (number >= 100) {
            roman += "C";
            number -= 100;
        }
        while (number >= 30) {
            roman += "Y";
            number -= 30;
        }
        while (number >= 10) {
            roman += "X";
            number -= 10;
        }
        while (number >= 3) {
            roman += "J";
            number -= 3;
        }
        while (number >= 1) {
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
        int tempNum = Integer.parseInt(number);
        if (tempNum >= 10000 || tempNum == 0) {
            throw new ValueOutOfBoundsException("ERROR: Out of Arabic Bounds");
        }
    }

    private void validateOrder(String number) throws MalformedNumberException {
        for (int i = 0; i < number.length() - 1; i++) {
            if (getNumeralValue.get(number.charAt(i)) < getNumeralValue.get(number.charAt(i + 1))) {
                throw new MalformedNumberException("ERROR: Improper ordering of Numerals");
            }
        }
    }

    private void checkElbonianBoundaries(String number) throws MalformedNumberException {
        Set<Character> keys = getNumeralValue.keySet();
        HashMap<Character, Integer> counters = new HashMap<>();
        for (Character key : keys) {
            counters.put(key, 0);
        }

        for (char c : number.toCharArray()) {
            if (counters.containsKey(c)) {
                counters.put(c, counters.get(c) + 1);
            } else {
                throw new MalformedNumberException("ERROR: Invalid Numeral Used");
            }
        }

        char[] twoMax = {'M', 'C', 'X', 'I'};
        for (char c : twoMax)
        {
            if(counters.get(c) > 2) throw new MalformedNumberException("ERROR: More than 2 of one of the following: [M,C,X,I]");
        }
        char[] threeMax = {'N', 'D', 'Y', 'J'};
        for (char c : threeMax)
        {
            if(counters.get(c) > 3) throw new MalformedNumberException("ERROR: More than 3 of one of the following: [M,C,X,I]");
        }

        if (counters.get('N') == 3 && counters.get('M') > 0) throw new MalformedNumberException("ERROR: Can't have an M if you have 3 N's");
        if (counters.get('D') == 3 && counters.get('C') > 0) throw new MalformedNumberException("ERROR: Can't have a C if you have 3 D's");
        if (counters.get('Y') == 3 && counters.get('X') > 0) throw new MalformedNumberException("ERROR: Can't have an X if you have 3 Y's");
        if (counters.get('J') == 3 && counters.get('I') > 0) throw new MalformedNumberException("ERROR: Can't have an I if you have 3 J's");
    }

        private void constructHashmap () {
            getNumeralValue.put('N', 3000);
            getNumeralValue.put('M', 1000);
            getNumeralValue.put('D', 300);
            getNumeralValue.put('C', 100);
            getNumeralValue.put('Y', 30);
            getNumeralValue.put('X', 10);
            getNumeralValue.put('J', 3);
            getNumeralValue.put('I', 1);
        }

        public String getNumber () {
            return number;
        }
    }
