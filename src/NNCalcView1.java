import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import components.naturalnumber.NaturalNumber;

/**
 * View class.
 *
 * @author Put your name here
 */
public final class NNCalcView1 extends JFrame implements NNCalcView {

    private static final long serialVersionUID = 1L; // Added serialVersionUID

    /**
     * Controller object registered with this view to observe user-interaction
     * events.
     */
    private NNCalcController controller;

    /**
     * State of user interaction: last event "seen".
     */
    private enum State {
        /**
         * Last event was clear, enter, another operator, or digit entry, resp.
         */
        SAW_CLEAR, SAW_ENTER_OR_SWAP, SAW_OTHER_OP, SAW_DIGIT
    }

    /**
     * State variable to keep track of which event happened last; needed to
     * prepare for digit to be added to bottom operand.
     */
    private State currentState;

    /**
     * Text areas.
     */
    private final JTextArea tTop, tBottom;

    /**
     * Operator and related buttons.
     */
    private final JButton bClear, bSwap, bEnter, bAdd, bSubtract, bMultiply,
            bDivide, bPower, bRoot;

    /**
     * Digit entry buttons.
     */
    private final JButton[] bDigits;

    /**
     * Useful constants.
     */
    private static final int TEXT_AREA_HEIGHT = 5, TEXT_AREA_WIDTH = 20,
            DIGIT_BUTTONS = 10, MAIN_BUTTON_PANEL_GRID_ROWS = 4,
            MAIN_BUTTON_PANEL_GRID_COLUMNS = 4, SIDE_BUTTON_PANEL_GRID_ROWS = 3,
            SIDE_BUTTON_PANEL_GRID_COLUMNS = 1, CALC_GRID_ROWS = 3,
            CALC_GRID_COLUMNS = 1;

    /**
     * No argument constructor.
     */
    public NNCalcView1() {
        // Create the JFrame being extended

        super("Natural Number Calculator");

        this.tTop = new JTextArea(TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);
        this.tBottom = new JTextArea(TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);

        // Set up the GUI widgets --------------------------------------------

        // Make text areas uneditable
        this.tTop.setEditable(false);
        this.tBottom.setEditable(false);

        // Initialize buttons
        this.bClear = new JButton("Clear");
        this.bSwap = new JButton("Swap");
        // [Initialization of other buttons]

        // Digit buttons array
        this.bDigits = new JButton[DIGIT_BUTTONS];
        for (int i = 0; i < DIGIT_BUTTONS; i++) {
            this.bDigits[i] = new JButton(String.valueOf(i));
        }

        // Register buttons with event handling
        this.bClear.addActionListener(this);
        this.bSwap.addActionListener(this);
        // [Event handling registration for other buttons]

        this.currentState = State.SAW_CLEAR;

        // Create widgets

        this.tTop.setLineWrap(true);
        this.tBottom.setLineWrap(true);

        // Initialize button
        this.bEnter = new JButton("Enter");
        this.bAdd = new JButton("+");
        this.bSubtract = new JButton("-");
        this.bMultiply = new JButton("*");
        this.bDivide = new JButton("/");
        this.bPower = new JButton("^");
        this.bRoot = new JButton("√");

        // Disable buttons initially where needed
        this.bDivide.setEnabled(false); // Divisor must not be 0
        this.bRoot.setEnabled(false); // Root must be at least 2

        // Create scroll panes for the text areas
        JScrollPane scrollPaneTop = new JScrollPane(this.tTop);
        JScrollPane scrollPaneBottom = new JScrollPane(this.tBottom);

        // Main button panel
        JPanel mainButtonPanel = new JPanel(new GridLayout(
                MAIN_BUTTON_PANEL_GRID_ROWS, MAIN_BUTTON_PANEL_GRID_COLUMNS));
        mainButtonPanel.add(this.bClear);
        mainButtonPanel.add(this.bSwap);
        mainButtonPanel.add(this.bEnter);
        mainButtonPanel.add(this.bAdd);
        mainButtonPanel.add(this.bSubtract);
        mainButtonPanel.add(this.bMultiply);
        mainButtonPanel.add(this.bDivide);
        mainButtonPanel.add(this.bPower);
        mainButtonPanel.add(this.bRoot);

        // Side button panel for digit buttons
        JPanel sideButtonPanel = new JPanel(new GridLayout(
                SIDE_BUTTON_PANEL_GRID_ROWS, SIDE_BUTTON_PANEL_GRID_COLUMNS));
        for (JButton button : this.bDigits) {
            sideButtonPanel.add(button);
        }

        // Create combined button panel
        JPanel combinedButtonPanel = new JPanel();
        combinedButtonPanel.setLayout(new FlowLayout());
        combinedButtonPanel.add(mainButtonPanel);
        combinedButtonPanel.add(sideButtonPanel);

        // Organize main window
        this.setLayout(new BorderLayout());
        this.add(scrollPaneTop, BorderLayout.NORTH);
        this.add(scrollPaneBottom, BorderLayout.CENTER);
        this.add(combinedButtonPanel, BorderLayout.SOUTH);

        // Register all buttons with action listeners
        this.bClear.addActionListener(this);
        this.bSwap.addActionListener(this);
        this.bEnter.addActionListener(this);
        this.bAdd.addActionListener(this);
        this.bSubtract.addActionListener(this);
        this.bMultiply.addActionListener(this);
        this.bDivide.addActionListener(this);
        this.bPower.addActionListener(this);
        this.bRoot.addActionListener(this);
        for (JButton button : this.bDigits) {
            button.addActionListener(this);
        }
        this.setLayout(new GridLayout(CALC_GRID_ROWS, CALC_GRID_COLUMNS));

        // Configure and display the main window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }

    @Override
    public void registerObserver(NNCalcController controller) {

        this.controller = controller;

    }

    @Override
    public void updateTopDisplay(NaturalNumber n) {

        this.tTop.setText(n.toString());

    }

    @Override
    public void updateBottomDisplay(NaturalNumber n) {

        this.tBottom.setText(n.toString());

    }

    @Override
    public void updateSubtractAllowed(boolean allowed) {

        this.bSubtract.setEnabled(allowed);

    }

    @Override
    public void updateDivideAllowed(boolean allowed) {

        this.bDivide.setEnabled(allowed);

    }

    @Override
    public void updatePowerAllowed(boolean allowed) {

        this.bPower.setEnabled(allowed);

    }

    @Override
    public void updateRootAllowed(boolean allowed) {

        this.bRoot.setEnabled(allowed);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        /*
         * Set cursor to indicate computation on-going; this matters only if
         * processing the event might take a noticeable amount of time as seen
         * by the user
         */
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        /*
         * Determine which event has occurred that we are being notified of by
         * this callback; in this case, the source of the event (i.e, the widget
         * calling actionPerformed) is all we need because only buttons are
         * involved here, so the event must be a button press; in each case,
         * tell the controller to do whatever is needed to update the model and
         * to refresh the view
         */
        Object source = event.getSource();
        if (source == this.bClear) {
            this.controller.processClearEvent();
            this.currentState = State.SAW_CLEAR;
        } else if (source == this.bSwap) {
            this.controller.processSwapEvent();
            this.currentState = State.SAW_ENTER_OR_SWAP;
        } else if (source == this.bEnter) {
            this.controller.processEnterEvent();
            this.currentState = State.SAW_ENTER_OR_SWAP;
        } else if (source == this.bAdd) {
            this.controller.processAddEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bSubtract) {
            this.controller.processSubtractEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bMultiply) {
            this.controller.processMultiplyEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bDivide) {
            this.controller.processDivideEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bPower) {
            this.controller.processPowerEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bRoot) {
            this.controller.processRootEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else {
            for (int i = 0; i < DIGIT_BUTTONS; i++) {
                if (source == this.bDigits[i]) {
                    switch (this.currentState) {
                        case SAW_ENTER_OR_SWAP:
                            this.controller.processClearEvent();
                            break;
                        case SAW_OTHER_OP:
                            this.controller.processEnterEvent();
                            this.controller.processClearEvent();
                            break;
                        default:
                            break;
                    }
                    this.controller.processAddNewDigitEvent(i);
                    this.currentState = State.SAW_DIGIT;
                    break;
                }
            }
        }
        /*
         * Set the cursor back to normal (because we changed it at the beginning
         * of the method body)
         */
        this.setCursor(Cursor.getDefaultCursor());
    }

}
