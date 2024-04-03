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
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
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
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        // Check if the expression is a number
        if (exp.label().equals("number")) {
            return Integer.parseInt(exp.attributeValue("value"));
        } else {
            // Get the operation type
            String operation = exp.label();
            // Get the children of the expression
            XMLTree leftChild = exp.child(0);
            XMLTree rightChild = exp.child(1);

            // Recursively evaluate left and right children
            int leftValue = evaluate(leftChild);
            int rightValue = evaluate(rightChild);

            // Perform the operation
            if (operation.equals("plus")) {
                return leftValue + rightValue;
            } else if (operation.equals("minus")) {
                return leftValue - rightValue;
            } else if (operation.equals("times")) {
                return leftValue * rightValue;
            } else if (operation.equals("divide")) {
                return leftValue / rightValue;
            }
            return 0; // or any other suitable value to signal error
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
