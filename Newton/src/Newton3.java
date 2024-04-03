import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Ask user if they would like to calculate a square root and calculate if yes.
 * Calculates using user entered value of epsilon.
 *
 * @author Obsa Temesgen
 *
 */
public final class Newton3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton3() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     * @param epsilon
     *
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double squareRoot(double x, double epsilon) {
        double calcNew = x;

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

        out.print("Enter epsilon:"); // Ask user to input epsilon value
        double epsilon = in.nextDouble();
        in.nextLine();

        out.print("Do you wish to calculate a square root? "); //prompt for calc
        String num = in.nextLine(); // reads it

        while (num.equals("y")) { // calculate square root if user enters y
            out.print("Enter a number: "); // prompts users to enter number

            double xNum = in.nextDouble();
            in.nextLine();

            num = in.nextLine();

            if (xNum > 0) {

                double result = squareRoot(xNum, epsilon); //Calculate sqrt w/ NI

                System.out.printf("The square root of %.2f is %.5f", xNum,
                        result);

            }
        }

        in.close();
        out.close();
    }

}
