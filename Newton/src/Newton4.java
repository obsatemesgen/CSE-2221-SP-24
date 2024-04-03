import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Asks for a new value of x each time and interprets a negative value as an
 * indication that it's time to quit.
 *
 * @author Obsa Temesgen
 *
 */
public final class Newton4 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double squareRoot(double x) {
        double calcNew = x;
        final double e = 0.0001;
        double epsilon = e; //relative error

        while (Math.abs((calcNew * calcNew - x) / x) > epsilon) {
            calcNew = (calcNew + x / calcNew) / 2; //newtons iteration formula
        }
        return calcNew;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        double xNum;

        out.print("Enter a number: "); // Asks user to input number
        xNum = in.nextDouble();
        in.nextLine();

        if (xNum >= 0) { //if greater than or equal to user, calculate square root

            double result = squareRoot(xNum); //Calculate sqrt w/ newton iteration

            System.out.printf("The square root of %.2f is %.5f", xNum, result);
            in.nextLine();

        } else { // if user inputs negative number quit the program
            out.println("Invalid Value");
        }

        in.close();
        out.close();
    }

}
