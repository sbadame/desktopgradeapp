package gradeapp;

import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.beans.*; //Property change stuff
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author andrew
 */
public class HelperButton extends JPanel{


    JLabel label;
    ImageIcon icon = createImageIcon("images/middle.gif");
    JFrame frame;
    String simpleDialogDesc = "Some simple message dialogs";
    String iconDesc = "A JOptionPane has its choice of icons";
    String moreDialogDesc = "Some more dialogs";
    CustomDialog2 customDialog2;

    /** Creates the GUI shown inside the frame's content pane. */
    public HelperButton(JFrame frame) {
        super(new BorderLayout());
        this.frame = frame;
        customDialog2 = new CustomDialog2(frame, "geisel", this);
        customDialog2.pack();

        //Create the components.
        JPanel frequentPanel = createSimpleDialogBox();

        label = new JLabel("Click the \"Show it!\" button"
                           + " to bring up the selected dialog.",
                           JLabel.CENTER);

        //Lay them out.
        Border padding = BorderFactory.createEmptyBorder(20,20,5,20);
        frequentPanel.setBorder(padding);


        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Welcome to you Help Kiosk, Enjoy!", null,
                          frequentPanel,
                          simpleDialogDesc); //tooltip text


        add(tabbedPane, BorderLayout.CENTER);
        add(label, BorderLayout.PAGE_END);
        label.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }

    /** Sets the text displayed at the bottom of the frame. */
    void setLabel(String newText) {
        label.setText(newText);
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = HelperButton.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }





     private JPanel createSimpleDialogBox() {
        final int numButtons = 5;
        JRadioButton[] radioButtons = new JRadioButton[numButtons];
        final ButtonGroup group = new ButtonGroup();

        JButton showItButton = null;

        final String defaultMessageCommand = "default";
        final String displayTree = "displayTree";
        final String emailTree = "emailTree";
        final String printTree = "printTree";
        final String authorsBib = "authorsBib";

        radioButtons[0] = new JRadioButton("How to Load an Excel File.");
        radioButtons[0].setActionCommand(defaultMessageCommand);

        radioButtons[1] = new JRadioButton("How to Alter a Graph.");
        radioButtons[1].setActionCommand(displayTree);

        radioButtons[2] = new JRadioButton("How to Email a Graph.");
        radioButtons[2].setActionCommand(emailTree);

        radioButtons[3] = new JRadioButton("How to Save a Graph.");
        radioButtons[3].setActionCommand(printTree);

        radioButtons[4] = new JRadioButton("Contact Info.");
        radioButtons[4].setActionCommand(authorsBib);

        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);

        showItButton = new JButton("Select");
        showItButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();

                //first choice
                if (command == defaultMessageCommand) {
                    JOptionPane.showMessageDialog(frame,
                                "This is the answer for the first selection.");

                //second choice
                } else if (command == displayTree) {
                   JOptionPane.showMessageDialog(frame,
                                "This is the answer for the second selection.");
                //third choice
                } else if (command == emailTree) {
                   JOptionPane.showMessageDialog(frame,
                                "This is the answer for the third selection.");
                //fourthchoice
                } else if (command == printTree) {
                   JOptionPane.showMessageDialog(frame,
                                "This is the answer for the forth selection.");
                } else if (command == authorsBib) {
                   JOptionPane.showMessageDialog(frame,
                                "This is the answer for the forth selection.");
                }
                return;
            }
        });

        return createPane(simpleDialogDesc + ":",
                          radioButtons,
                          showItButton);
    }

     /**
     * Used by createSimpleDialogBox and createFeatureDialogBox
     * to create a pane containing a description, a single column
     * of radio buttons, and the Show it! button.
     */
    private JPanel createPane(String description,
                              JRadioButton[] radioButtons,
                              JButton showButton) {

        int numChoices = radioButtons.length;
        JPanel box = new JPanel();
        JLabel label = new JLabel(description);

        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.add(label);

        for (int i = 0; i < numChoices; i++) {
            box.add(radioButtons[i]);
        }

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
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //Create and set up the content pane.
        HelperButton newContentPane = new HelperButton(frame);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

 //   public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
  //      javax.swing.SwingUtilities.invokeLater(new Runnable() {
    //        public void run() {
     //           createAndShowGUI();
    //        }
    //    });
  //  }


}