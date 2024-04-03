import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Ask user if they would like to calculate a square root and calculate if yes.
 * Calculates square root of 0.
 *
 * @author Obsa Temesgen
 *
 */
public final class Newton2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton2() {
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

        out.print("Do you wish to calculate a square root? "); //prompt for calc
        String num = in.nextLine(); // reads it

        while (num.equals("y")) { // calculate square root if user enters y
            out.print("Enter a number: "); // prompts users to enter number

            double xNum = in.nextDouble();
            in.nextLine();

            num = in.nextLine();

            if (xNum >= 0) { // greater than or equal to 0

                double result = squareRoot(xNum); //Calculate sqrt w/ newton iteration

                System.out.printf("The square root of %.2f is %.5f", xNum,
                        result);

            }
        }

        in.close();
        out.close();
    }

}
