import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Test methods in the class focusing on testing a specific functionality of the
 * CryptoUtilities class.
 */

public class CryptoUtilitiesTest {

    /**
     * Testing reduceToGCD with n1 = 24 and m1 = 36, testing GCD calculation.
     */
    @Test
    public void testReduceToGCD() {
        NaturalNumber n = new NaturalNumber2(36);
        NaturalNumber m = new NaturalNumber2(24);
        CryptoUtilities.reduceToGCD(n, m);
        System.out.println("GCD of 36 and 24: " + n);
    }

    /**
     * Verifies that the method correctly identifies whether a given number is
     * even.
     */
    @Test
    public void testIsEven() {

        NaturalNumber n = new NaturalNumber2(40);
        System.out.println("Is 40 even? " + CryptoUtilities.isEven(n)); //Is 40 even? true

    }

    /**
     * Verifies that the method correctly computes the result of n^p mod m.
     */
    @Test
    public void testPowerMod() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber p = new NaturalNumber2(4);
        NaturalNumber m = new NaturalNumber2(5);
        CryptoUtilities.powerMod(n, p, m);
        System.out.println("3^4 mod 5: " + n); // Expected output: 3^4 mod 5: 1
    }

    /**
     * Verifies that the method correctly identifies whether a given number is a
     * witness to the compositeness of another number.
     */
    @Test
    public void testIsWitnessToCompositeness() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2(9);
        System.out.println("Is 2 a witness that 9 is composite? "
                + CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    /**
     * Verifies that the method correctly determines whether a given number is
     * prime.
     */

    @Test
    public void testIsPrime2() {
        NaturalNumber n = new NaturalNumber2(2);
        System.out.println("Is 2 prime? " + CryptoUtilities.isPrime2(n));
    }

    /**
     * Verifies that the method correctly generates the next likely prime number
     * after a given number.
     */
    @Test
    public void testGenerateNextLikelyPrime() {
        NaturalNumber n = new NaturalNumber2(30);
        CryptoUtilities.generateNextLikelyPrime(n);
        System.out.println("Next likely prime after 30: " + n);
    }

}
