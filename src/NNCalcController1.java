import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Put your name here
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {

        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();

        // Update displays directly with NaturalNumber instances
        view.updateTopDisplay(top);
        view.updateBottomDisplay(bottom);

        // Update the subtract button allowed status
        view.updateSubtractAllowed(bottom.compareTo(top) <= 0);

        // Update the divide button allowed status (not allowed if bottom is zero)
        view.updateDivideAllowed(!bottom.isZero());

        // Update the power button allowed status (allowed if bottom <= INT_LIMIT)
        view.updatePowerAllowed(bottom.compareTo(INT_LIMIT) <= 0);

        // Update the root button allowed status if bottom is between 2 and INT_LIMIT`
        view.updateRootAllowed(
                bottom.compareTo(TWO) >= 0 && bottom.compareTo(INT_LIMIT) <= 0);
    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {

        // Get alias to bottom from model and copy it to top
        NaturalNumber bottom = this.model.bottom();
        this.model.top().copyFrom(bottom);
        // Update view to reflect changes in model
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddEvent() {

        // Get aliases to top and bottom from model
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        // Perform addition
        bottom.add(top);
        top.clear();
        // Update view to reflect changes in model
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processSubtractEvent() {

        // Get aliases to top and bottom from model
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        // Perform subtraction
        top.subtract(bottom);
        bottom.copyFrom(top);
        top.clear();
        // Update view to reflect changes in model
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processMultiplyEvent() {

        // Get aliases to top and bottom from model
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        // Perform multiplication
        bottom.multiply(top);
        top.clear();
        // Update view to reflect changes in model
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processDivideEvent() {

        // Get aliases to top and bottom from model
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        // Perform division
        NaturalNumber remainder = top.divide(bottom);
        bottom.transferFrom(top);
        top.copyFrom(remainder);
        // Update view to reflect changes in model
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processPowerEvent() {

        // Get aliases to top and bottom from model
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        // Perform power
        top.power(bottom.toInt());
        bottom.copyFrom(top);
        top.clear();
        // Update view to reflect changes in model
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processRootEvent() {

        // Get aliases to top and bottom from model
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        // Perform root
        top.root(bottom.toInt());
        bottom.transferFrom(top);
        top.clear();
        // Update view to reflect changes in model
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {

        // Get alias to bottom from model
        NaturalNumber bottom = this.model.bottom();
        // Multiply the current bottom by 10
        bottom.multiplyBy10(1);
        // Create a new NaturalNumber for the digit
        NaturalNumber newDigit = new NaturalNumber2(digit);
        // Add the new digit to the bottom
        bottom.add(newDigit);
        // Update view to reflect changes in model
        updateViewToMatchModel(this.model, this.view);
    }
}
