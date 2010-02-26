package gradeapp;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JTextPane;
/**
 *
 * @author andrew
 */
public class HelperButton extends JPanel{

    JLabel label;
    JFrame frame;
    String simpleDialogDesc = "Choose the topic that you need help with";
    String iconDesc = "A JOptionPane has its choice of icons";
    String moreDialogDesc = "Some more dialogs";

    /** Creates the GUI shown inside the helperframe's content pane. */
    public HelperButton(JFrame frame) {
        super(new BorderLayout());
        this.frame = frame;

        //Create the components.
        JPanel frequentPanel = createSimpleDialogBox();

        label = new JLabel("Choose the category of help that you need." ,JLabel.CENTER);

        //Lay them out.
        Border padding = BorderFactory.createEmptyBorder(20,20,5,20);
        frequentPanel.setBorder(padding);

        add(frequentPanel, BorderLayout.CENTER);
        add(label, BorderLayout.PAGE_END);
        label.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }

    private JPanel createSimpleDialogBox() {
        final ButtonGroup group = new ButtonGroup();

        final String loadHelpCommand = "loadhelp";
        final String graphHelpCommand = "graphhelp";
        final String emailHelpCommand = "emailhelp";
        final String saveHelpCommand = "savehelp";
        final String printHelpCommand = "printhelp";
        final String aboutCommand = "abouthelp";

        JRadioButton loadhelp = new JRadioButton("Loading an Excel File.");
        loadhelp.setActionCommand(loadHelpCommand);
        group.add(loadhelp);

        JRadioButton graphHelp = new JRadioButton("Understanding and Modifying a Grade Tree");
        graphHelp.setActionCommand(graphHelpCommand);
        group.add(graphHelp);

        JRadioButton emailHelp = new JRadioButton("Emailing a Tree");
        emailHelp.setActionCommand(emailHelpCommand);
        group.add(emailHelp);

        JRadioButton saveHelp = new JRadioButton("Saving a Tree");
        saveHelp.setActionCommand(saveHelpCommand);
        group.add(saveHelp);

        JRadioButton printHelp = new JRadioButton("Printing a Tree");
        printHelp.setActionCommand(printHelpCommand);
        group.add(printHelp);

        JRadioButton aboutHelp = new JRadioButton("About the developers");
        aboutHelp.setActionCommand(aboutCommand);
        group.add(aboutHelp);

        JButton showItButton = new JButton("Select");
        showItButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();

                JTextPane textPane = new JTextPane(); // creates an empty text pane
                textPane.setContentType("text/html"); // lets Java know it will be HTML
                String text = "<HTML>";

                if (command.equals(loadHelpCommand)) {
                    text += "<h1>How to load a file</h1>";
                    text += "<p>Begin by clicking on the 'Load *.xls' button<br />" +
                    "which will open a window where you can browse<br />" +
                    "your local drives for an Excel file to load. If you<br /> " +
                    "have any issue loading a file, check the following:</p>" +
                    "<ul><li>Are you selecting an Excel file(*.xls or *.xlsx)?</li>"+
                    "<li>Is the data in the selected Excel file arranged<br />"+
                    "in the same manner as was produced by the<br />Scantron machine?</li>"+
                    "</ul>";

                } else if (command.equals(graphHelpCommand)) {

                } else if (command.equals(saveHelpCommand)) {
                    text += "<h1>Saving a Tree</h1>" +
                    "<p><b>Note:</b> You need to have loaded an Excel file<br />" +
                    "into the program before you will be able to save a tree.</p>" +
                    "<br />Save a tree by clicking on the 'Save' button. This will<br />" +
                    "open a window where you can browse to where you want to<br />" +
                    "save a copy of the tree. You do not need to specify an<br />" +
                    "extension (ie: .jpg, .gif, etc) the image will be automatically<br />" +
                    "saved with a *.png extension for you.</p>";
                } else if (command.equals(emailHelpCommand)) {
                    //text += "This is the answer for the Display selection.";
                } else if (command.equals(printHelpCommand)) {
                    //text += "This is the answer for the Display selection.";
                } else if (command.equals(aboutCommand)) {
                    //text += "This is the answer for the Display selection.";
                }

                text += "</HTML>";
                textPane.setText(text);
                textPane.setEditable(false);
                JFrame helperframe = new JFrame(); // makes a window to put it in
                helperframe.getContentPane().add(textPane); // adds the text pane to the window
                helperframe.pack(); // adjusts the window to the right size
                helperframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                helperframe.addWindowListener(new WindowListener(){
                    public void windowClosed(WindowEvent e) {
                        frame.pack();
                        frame.setVisible(true);
                    }
                    public void windowOpened(WindowEvent e) {}
                    public void windowClosing(WindowEvent e) {}
                    public void windowIconified(WindowEvent e) {}
                    public void windowDeiconified(WindowEvent e) {}
                    public void windowActivated(WindowEvent e) {}
                    public void windowDeactivated(WindowEvent e) {}
                });

                frame.setVisible(false); //Hide the help panel
                helperframe.setVisible(true); // makes it show up
                helperframe.setLocationRelativeTo(null);
                return;
            }
        });

        return createPane(simpleDialogDesc + ":", group, showItButton);
    }

     /**
     * Used by createSimpleDialogBox and createFeatureDialogBox
     * to create a pane containing a description, a single column
     * of radio buttons, and the Show it! button.
     */
    private JPanel createPane(String description,
                              ButtonGroup group,
                              JButton showButton) {

        JPanel box = new JPanel();
        JLabel label = new JLabel(description);

        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.add(label);
        Enumeration<AbstractButton> buttons = group.getElements();
        while(buttons.hasMoreElements())
            box.add(buttons.nextElement());

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(box, BorderLayout.PAGE_START);
        pane.add(showButton, BorderLayout.PAGE_END);
        return pane;
    }


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Help Menu");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        //Create and set up the content pane.
        HelperButton newContentPane = new HelperButton(frame);
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}

