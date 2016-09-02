
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Arrays;

public class RPNPlotter implements ActionListener {
RPNSimpleCalculator cal = new RPNSimpleCalculator();

    // Window that contains all the widgets of RPN Plotter
    JFrame rpnWindow;

    // Field where RPN equations is typed
    JTextField rpnEquationField;

    // Fields for minX and maxX
    JTextField minXField;
    JTextField maxXField;
    JTextField outputField;

    // Button used to plot
    JButton plotButton;

    // Panel where graph is plotted
    PlotPanel plotPanel;

    /**
     * Adds the widgets to the main window
     */
    public void addWidgets(Container pane) {

	// 
	// We use an absolute Layout to position the widgets
	// since it is the easiest one.
	//
	// Using some trial and error we give the absolute coordinates
	// for the different widgets that will be in the window.
	//
	// Rembember that 0,0 is the very top left corner of the window.
	//
        pane.setLayout(null);

	// Add the label for the RPN Equation
        JLabel label = new JLabel("Type RPN Equation: ", SwingConstants.RIGHT);
	label.setBounds( 20, 200, 140, 20);
        pane.add(label);

	// Add the field for the RPN Equation
        rpnEquationField = new JTextField(24);
        pane.add(rpnEquationField);
        Dimension size = rpnEquationField.getPreferredSize();
	rpnEquationField.setBounds(160, 200, size.width, size.height);

	// Add the label for the minX Field
	label = new JLabel("minX: ", SwingConstants.RIGHT);
	label.setBounds( 20, 225, 140, 20);
        pane.add(label);

	// Add the field for minX
	minXField  = new JTextField("0", 10);
        pane.add(minXField);
        size = minXField.getPreferredSize();
	minXField.setBounds(160, 225, size.width, size.height);

	// Add the label for the maxX Field
	label = new JLabel("maxX: ", SwingConstants.RIGHT);
	label.setBounds( 20, 250, 140, 20);
        pane.add(label);

	// Add the field for maxX
	maxXField  = new JTextField("10", 10);
        pane.add(maxXField);
        size = maxXField.getPreferredSize();
	maxXField.setBounds(160, 250, size.width, size.height);
	
	// Add the plot panel where the graph will appear
	plotPanel = new PlotPanel();
	pane.add(plotPanel);
	plotPanel.setBounds(40,20, 400, 175);

	// Add button for plotting
        plotButton = new JButton("Plot");
        pane.add(plotButton);
        Dimension buttonSize = plotButton.getPreferredSize();
        plotButton.setBounds(40, 275,
                     buttonSize.width , buttonSize.height );

	// Add listener for button
	plotButton.addActionListener(this);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private void createAndShowGUI() {
        //Create and set up the window.
        rpnWindow = new JFrame("RPN Plotter");
        rpnWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addWidgets(rpnWindow.getContentPane());

        //Size and display the window.
        Insets insets = rpnWindow.getInsets();
        rpnWindow.setSize(500 + insets.left + insets.right,
                      340 + insets.top + insets.bottom);
        rpnWindow.setVisible(true);
    }

    /* 
     * We create a subclass for the panel where the plot
     * will appear.
     */
    public class PlotPanel extends JPanel {

	/*
	 * When the Plot panel is displayed, this method
	 * will be called to paint the contents.
	 */
	public void paint(Graphics g) {
	    // Get dimensions of the plot pane
	    Dimension d = getSize();
	    int w = d.width;
	    int h = d.height;

	    // Draw box around the plot pane
	    g.setColor(Color.black);
	    g.drawLine(0, 0, w-1, 0);
	    g.drawLine(w-1, 0, w-1, h-1);
	    g.drawLine(w-1, h-1, 0, h-1);
	    g.drawLine(0, h-1, 0, 0);

	    // Generate array of points that will be plotted

	    // Array with the points to be plotted.
	    int numberOfPoints = 100;
	    double x[] = new double[numberOfPoints];
	    double y[] = new double[numberOfPoints];
	    double minX = Double.parseDouble(minXField.getText());
	    double maxX = Double.parseDouble(maxXField.getText());;
	    double increment = (maxX - minX)/numberOfPoints;
	    for (int i=0; i < numberOfPoints; i++) {
		x[i] = minX + increment * i;
		cal.setX(x[i]);
		
		String equation = rpnEquationField.getText();
		try{
			y[i] = cal.evaluate(equation);
		}
		catch(RPNEvaluationException e){
			
			e.printStackTrace();
		}
			
		
		
	    }

	    // Compute minY and maxY
	    double minY = y[0];
	    double maxY = y[0];
	    for (int i = 1; i < 100; i++) {
		if (minY > y[i]) {
		    minY = y[i];
		}
		if (maxY < y[i]) {
		    maxY = y[i];
		}
	    }

	    // Transform the x/y points to x/y screen coordinates
	    int xscreen[] = new int[numberOfPoints];
	    int yscreen[] = new int [numberOfPoints];

	    for (int i=0; i < numberOfPoints; i++) {
		// This will make xscreen[i] == 0 when x[i] == minX
		// and it will make xscreen[i] == w when x[i] == maxX
		xscreen[i] = (int) ((x[i]-minX)*w/(maxX - minX)+0);

		// This will make yscreen[i] == 0 when y[i] == maxY
		// and it will make yscreen[i] == w when y[i] == minY
		// remember that 0,0 is the top left corner of the screen
		yscreen[i] = (int) (h - (y[i]-minY)*h/(maxY - minY));
	    }

	    // Plot the graph
	    g.setColor(Color.green);
	    for (int i =1; i < numberOfPoints; i++) {
		g.drawLine(xscreen[i-1], yscreen[i-1], xscreen[i], yscreen[i]);
	    }
	}
    }

    /**
     * Method called when the plot button is pressed
     */
    public void actionPerformed(ActionEvent event) {
	// The Plotting button has been pressed.
	System.out.println("Plotting " + event.getActionCommand());
	
	// Repaint the window. 
	// The PlotPannel.paint() method will be called eventually
	// to redraw the graph
	rpnWindow.repaint();
    }

    /**
     * Execution of RPNPlotter starts here
     */
    public static void main(String[] args) {
	RPNPlotter rpnPlotter = new RPNPlotter();
	rpnPlotter.createAndShowGUI();
    }
}
