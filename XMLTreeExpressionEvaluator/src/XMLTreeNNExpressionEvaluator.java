import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        assert exp != null : "Violation of: exp is not null";

        // Check if the expression is a number
        if (exp.label().equals("number")) {
            NaturalNumber result = new NaturalNumber2(
                    Integer.parseInt(exp.attributeValue("value")));
            return result;
        } else {
            // Get the operation type
            String operation = exp.label();
            // Get the children of the expression
            XMLTree leftChild = exp.child(0);
            XMLTree rightChild = exp.child(1);

            // Recursively evaluate left and right children
            NaturalNumber leftValue = evaluate(leftChild);
            NaturalNumber rightValue = evaluate(rightChild);

            // Perform the operation
            NaturalNumber result = new NaturalNumber2();
            if (operation.equals("plus")) {
                result.add(leftValue);
                result.add(rightValue);
            } else if (operation.equals("minus")) {
                result.copyFrom(leftValue);
                result.subtract(rightValue);
            } else if (operation.equals("times")) {
                result.copyFrom(leftValue);
                result.multiply(rightValue);
            } else if (operation.equals("divide")) {
                result.copyFrom(leftValue);
                result.divide(rightValue);
            }
            return result;
        }
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
