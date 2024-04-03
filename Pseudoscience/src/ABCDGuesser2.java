import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Numerical optimization for the de Jager formula with user input.
 *
 * @author Obsa Temesgen
 *
 */
public final class ABCDGuesser2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        double userInput = 0.0;
        boolean validInput = false;

        while (!validInput) {
            out.print("Enter a value: ");

            String input = in.nextLine();

            if (FormatChecker.canParseDouble(input)) {
                userInput = Double.parseDouble(input);

                if (userInput > 0) {
                    validInput = true;
                } else {
                    out.println(
                            "Invalid input. Enter a positive number greater than 0.");
                }
            } else {
                out.println("Invalid input. Please enter a valid real number.");
            }
        }

        return userInput;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        boolean validInput = false;
        double value = 0.0;

        while (!validInput) {
            out.print("Enter a positive real number not equal to 1.0: ");
            String input = in.nextLine();

            if (FormatChecker.canParseDouble(input)) {
                value = Double.parseDouble(input);

                if (value > 0 && value != 1.0) {
                    validInput = true;
                } else {
                    out.println(
                            "Invalid input. Enter a positive number not equal to 1.0.");
                }
            } else {
                out.println("Invalid input. Please enter a valid real number.");
            }
        }

        return value;
    }

    /**
     * Finds the set of exponents that minimizes the relative error in
     * approximating μ using the de Jager formula.
     *
     * @param mu
     *            constant to be approximated
     * @param w
     *            personal number w
     * @param x
     *            personal number x
     * @param y
     *            personal number y
     * @param z
     *            personal number z
     * @return an array containing the exponents [a, b, c, d] that minimize the
     *         relative error
     */
    private static double[] findBestExponents(double mu, double w, double x,
            double y, double z) {
        final int aFour = 4;
        final int aThree = 3;
        double[] exponents = new double[aFour];
        double minError = Double.MAX_VALUE;

        for (double a : getExponents()) {
            for (double b : getExponents()) {
                for (double c : getExponents()) {
                    for (double d : getExponents()) {
                        double result = calculateFormula(w, x, y, z,
                                new double[] { a, b, c, d });
                        double error = calculateRelativeError(mu, result);

                        if (error < minError) {
                            minError = error;
                            exponents[0] = a;
                            exponents[1] = b;
                            exponents[2] = c;
                            exponents[aThree] = d;
                        }
                    }
                }
            }
        }

        return exponents;
    }

    /**
     * Retrieves predefined exponents for de Jager formula calculations.
     *
     * @return array of predefined exponents
     */
    private static double[] getExponents() {
        final double[] jagExponents = { -5, -4, -3, -2, -1, -1.0 / 2, -1.0 / 3,
                -1.0 / 4, 0, 1.0 / 4, 1.0 / 3, 1.0 / 2, 1, 2, 3, 4, 5 };
        return jagExponents;
    }

    /**
     * Calculates de Jager formula result with specified exponents.
     *
     * @param w
     *            personal number w
     * @param x
     *            personal number x
     * @param y
     *            personal number y
     * @param z
     *            personal number z
     * @param exponents
     *            array [a, b, c, d] of exponents
     * @return result of de Jager formula
     */
    private static double calculateFormula(double w, double x, double y,
            double z, double[] exponents) {
        final int aThree = 3;
        return Math.pow(w, exponents[0]) * Math.pow(x, exponents[1])
                * Math.pow(y, exponents[2]) * Math.pow(z, exponents[aThree]);
    }

    /**
     * Calculates relative error percentage between actual and approximated
     * values.
     *
     * @param actual
     *            actual value
     * @param approximation
     *            approximated value
     * @return relative error as a percentage
     */
    private static double calculateRelativeError(double actual,
            double approximation) {
        final double aHundred = 100.0;
        return Math.abs((actual - approximation) / actual) * aHundred;
    }

    /**
     * The main method where the program execution begins.
     *
     * @param args
     *            command-line arguments
     */
    public static void main(String[] args) {

        final int aThree = 3;

        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the constant μ: ");
        double mu = getPositiveDouble(in, out);

        out.print("Enter the personal number w: ");
        double w = getPositiveDoubleNotOne(in, out);

        out.print("Enter the personal number x: ");
        double x = getPositiveDoubleNotOne(in, out);

        out.print("Enter the personal number y: ");
        double y = getPositiveDoubleNotOne(in, out);

        out.print("Enter the personal number z: ");
        double z = getPositiveDoubleNotOne(in, out);

        double[] exponents = findBestExponents(mu, w, x, y, z);
        double result = calculateFormula(w, x, y, z, exponents);

        out.println("Exponents: a = " + exponents[0] + ", b = " + exponents[1]
                + ", c = " + exponents[2] + ", d = " + exponents[aThree]);
        out.println("Best approximation: " + result);
        out.println(
                "Relative error: " + calculateRelativeError(mu, result) + "%");

        in.close();
        out.close();
    }

}
