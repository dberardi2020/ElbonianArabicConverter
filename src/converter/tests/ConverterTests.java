package converter.tests;

import converter.ElbonianArabicConverter;
import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the ElbonianArabicConverter class.
 */
public class ConverterTests {

   /** @Test
    public void ElbonianToArabicSampleTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("1");
        assertEquals(converter.toElbonian(), "I");
    }**/

    @Test
    public void ArabicToElbonianSampleTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("I");
        assertEquals(converter.toArabic(), 1);
    }

    @Test
    public void IAmountTestPass() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("II");
        assertEquals(converter.toArabic(), 2);
    }

    @Test(expected = MalformedNumberException.class)
    public void IAmountTestFail() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("III");
    }

    @Test
    public void MAmountTestPass() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MM");
        assertEquals(converter.toArabic(), 2000);
    }

    @Test(expected = MalformedNumberException.class)
    public void MAmountTestFail() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MMM");
    }

    @Test
    public void CAmountTestPass() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("CC");
        assertEquals(converter.toArabic(), 200);
    }

    @Test(expected = MalformedNumberException.class)
    public void CAmountTestFail() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("CCC");
    }

    @Test
    public void XAmountTestPass() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("XX");
        assertEquals(converter.toArabic(), 20);
    }

    @Test(expected = MalformedNumberException.class)
    public void XAmountTestFail() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("XXX");
    }

    @Test
    public void NAmountTestPass() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("NNN");
        assertEquals(converter.toArabic(), 9000);
    }

    @Test(expected = MalformedNumberException.class)
    public void NAmountTestFail() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("NNNN");
    }

    @Test
    public void DAmountTestPass() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("DDD");
        assertEquals(converter.toArabic(), 900);
    }

    @Test(expected = MalformedNumberException.class)
    public void DAmountTestFail() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("DDDD");
    }

    @Test
    public void YAmountTestPass() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("YYY");
        assertEquals(converter.toArabic(), 90);
    }

    @Test(expected = MalformedNumberException.class)
    public void YAmountTestFail() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("YYYY");
    }

    @Test
    public void JAmountTestPass() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("JJJ");
        assertEquals(converter.toArabic(), 9);
    }

    @Test(expected = MalformedNumberException.class)
    public void JAmountTestFail() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("JJJJ");
    }

    @Test(expected = MalformedNumberException.class)
    public void letterMCannotBeUsed() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("NNNM");
    }

    @Test(expected = MalformedNumberException.class)
    public void letterCCannotBeUsed() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("DDDC");
    }

    @Test(expected = MalformedNumberException.class)
    public void letterXCannotBeUsed() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("YYYX");
    }

    @Test(expected = MalformedNumberException.class)
    public void letterICannotBeUsed() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("IJJJ");
    }

    @Test
    public void properConversion() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("NNMDYYYJI");
        assertEquals(converter.toArabic(), 7394);
    }

    @Test
    public void properConversion1() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("7394");
        assertEquals(converter.toElbonian(), "NNMDYYYJI");
    }

    @Test
    public void properConversion3() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("23");
        assertEquals(converter.toElbonian(), "XXJ");
    }

    @Test(expected = MalformedNumberException.class)
    public void WrongOrder() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("IM");
    }

    @Test(expected = MalformedNumberException.class)
    public void WrongOrder2() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("NMDCYXIJ");
    }

    @Test(expected = ValueOutOfBoundsException.class)
    public void OutOfBoundsHighTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("10000");
    }

    @Test(expected = ValueOutOfBoundsException.class)
    public void OutOfBoundsLowTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("0");
    }


    @Test(expected = MalformedNumberException.class)
    public void IllegalSpaceArabicTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("9 9");
    }

    @Test(expected = MalformedNumberException.class)
    public void IllegalSpaceElbonianTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("I I");
    }

    @Test
    public void RemoveSpacesTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("   11   ");
        assertEquals(converter.getNumber(), "11");
    }

    @Test(expected = MalformedNumberException.class)
    public void CheckEmpty () throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("        ");
    }

    /**
     * Sample Tests
     */
    @Test(expected = MalformedNumberException.class)
    public void malformedNumberTest() throws MalformedNumberException, ValueOutOfBoundsException {
        throw new MalformedNumberException("TEST");
    }

    @Test(expected = ValueOutOfBoundsException.class)
    public void valueOutOfBoundsTest() throws MalformedNumberException, ValueOutOfBoundsException {
        throw new ValueOutOfBoundsException("0");
    }
}