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
import javax.swing.JScrollPane;
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


        //Lay them out.
        Border padding = BorderFactory.createEmptyBorder(20,20,5,20);
        frequentPanel.setBorder(padding);

        add(frequentPanel, BorderLayout.CENTER);
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
text+= "<h1>Reading a Tree</h1><p>Once you have loaded a file a tree will be" +
"shown. The tree is made up of cells and edges. Cell are the orange "+
"boxes and edges connect them. Each cell represents whether a question was" +
"answered correctly or incorrectly. The main idea here is that there is no" +
" extrapolation or guesswork done by the program. The results you see are" +
" real.</p>";
text+="<h1>Setting what a good grade is.</h1><p>Of course, every exam is " +
"different and so what constitues a good grade needs to be defined by you." +
"The way that you do this is by moving the \"Good Grade\" slider to what" +
"you think a good grade is.</p>";
text+="<h1>Setting the Quality</h1><p>Your first question probably is: \"What" +
" is quality?\" or \"Why wouldn't I want the best Quality?\" What the quality" +
" bar is really asking is \"How good a predictor does a question need to be" +
" for me to show it to you?\" Basically, as the program breaks down how people" +
" did on the exam (or as you traverse down the tree, however you wish to look"+
" at it.) the questions become worse and predictors. By raising the \"Quality\"" +
" bar you are raising the cutoff on how good of a predictor the question needs" +
" to be for it to be shown. As you increase quality you will see your tend to" +
" shrink, increase and it grows. But be warned, a large graph does not necessarily " +
" mean more information. Since the amount Quality wanted is dependent on the " +
"exam,it needs to be tuned manually. A good rule of thumb is to increase the " +
" as far as possible while still getting a <i>good</i>(This is a weasel word," +
" I know) sized graph and then going down no further. </p>";
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
                    text += "<h1>Emailing a File</h1>" +
                       "<p>You also have to load an excel file so a graph is displayed before it can be emailed.<br>"+
                       "When you click on the email button you must input an email address to send the graph to.<br>" +
                       "You do not have to select any file, the application will automaticlly select the picture of the graph.<br>"+
                       "After selecting a file, application will open a progress bar, which will close when the emailing process is complete.<br>"+
                       "You will then get a message stating whether the file was successfully sent or not.<br</p>"+
                       "<h2>Issues</h2>" +
                       "<p>If not successful please must sure that you are connected to the internet.<br>"+
                       "Also must sure that you typed in the correct email address.<br>"+
                       "Please check to see if gmail server or your email server is up.<br>";
                } else if (command.equals(printHelpCommand)) {
                    text +="<h1>Printing a File</h1>" +
                       "<p>You also have to load an excel file so a graph is displayed before it can be printed.<br>"+
                       "When you click on the print button a standard print window will open.<br>"+
                       "Please just select your printer and print the file.<br>"+
                       "The file will be printed zoomed out so it doesnt matter where the zoom slider is.<br>";
                } else if (command.equals(aboutCommand)) {
text += "<h1>Team Members</h1><ul><li>Sandro Badame</li><li>Anthony DiFiore</li>" +
        "<li>Christopher Eichel<li><li>Andrew Esca</li> <li>Andres Ramirez" +
        "</li><li></li></ul><h2>To Learn more about this project follow us at:" +
        "<a href=\"http://github.com/sbadame/desktopgradeapp/\">http://github.com/sbadame/desktopgradeapp/</a></h2>";
                }

                text += "</HTML>";
                textPane.setText(text);
                textPane.setEditable(false);
                JFrame helperframe = new JFrame(); // makes a window to put it in
                helperframe.getContentPane().add(new JScrollPane(textPane)); // adds the text pane to the window
                helperframe.pack(); // adjusts the window to the right size
                helperframe.setSize(600, 500);
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
                textPane.setCaretPosition(0);
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
        JLabel label2 = new JLabel(description);

        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.add(label2);
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

