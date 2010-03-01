package gradeapp;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The main GUI and driver of the program.
 * Look here to see how it all plays out. The UI code is generated using
 * Netbeans, becareful where you edit, Netbeans might just regenerate it.
 * To avoid this try to edit this file only using Netbeans. All other files
 * can be edited without worry using any editor.
 *
 * This class mostly detects events with the UI and then calls methods in
 * the Graph class to set those changes.
 */
public class GradeApp extends javax.swing.JFrame {

    /** Creates new form GradeApp */

    public GradeApp() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadButton = new javax.swing.JButton();
        emailButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();
        goodGradePanel = new javax.swing.JPanel();
        gradeSlider = new javax.swing.JSlider();
        qualitySliderPanel = new javax.swing.JPanel();
        qualitySlider = new javax.swing.JSlider();
        graphPanel = new javax.swing.JPanel();
        gRender = new gradeapp.GRender();
        scaleSlider = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        helpButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("The Weeder");

        loadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gradeapp/images/document-open.png"))); // NOI18N
        loadButton.setText("Load *.xls");
        loadButton.setMaximumSize(new java.awt.Dimension(105, 32));
        loadButton.setMinimumSize(new java.awt.Dimension(105, 32));
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        emailButton.setForeground(new java.awt.Color(1, 1, 1));
        emailButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gradeapp/images/mail-forward.png"))); // NOI18N
        emailButton.setText("Email");
        emailButton.setEnabled(false);
        emailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailButtonActionPerformed(evt);
            }
        });

        printButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gradeapp/images/document-print.png"))); // NOI18N
        printButton.setText("Print");
        printButton.setEnabled(false);
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        goodGradePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Good Grade - 0/0"));
        goodGradePanel.setToolTipText("This bar defines what a good grade is. Move it right to increase the grade, move it left to decrease it.");

        gradeSlider.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        gradeSlider.setMajorTickSpacing(5);
        gradeSlider.setMaximum(0);
        gradeSlider.setMinorTickSpacing(1);
        gradeSlider.setPaintLabels(true);
        gradeSlider.setPaintTicks(true);
        gradeSlider.setToolTipText("This bar defines what a good grade is. Move it right to increase the grade, move it left to decrease it.");
        gradeSlider.setValue((int)(Graph.DEFAULT_GOODGRADE));
        gradeSlider.setEnabled(false);
        gradeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                gradeSliderStateChanged(evt);
            }
        });

        javax.swing.GroupLayout goodGradePanelLayout = new javax.swing.GroupLayout(goodGradePanel);
        goodGradePanel.setLayout(goodGradePanelLayout);
        goodGradePanelLayout.setHorizontalGroup(
            goodGradePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(goodGradePanelLayout.createSequentialGroup()
                .addComponent(gradeSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 1079, Short.MAX_VALUE)
                .addContainerGap())
        );
        goodGradePanelLayout.setVerticalGroup(
            goodGradePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(goodGradePanelLayout.createSequentialGroup()
                .addComponent(gradeSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        qualitySliderPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Quality - " + (int)(Graph.DEFAULT_QUALITY*100) + "%"));
        qualitySliderPanel.setToolTipText("This bar modifies the quality of the graph. Increasing quality shrinks the graph but gives more definate results, while reducing quality gives more results but they may not be the most telling.");

        qualitySlider.setFont(new java.awt.Font("DejaVu Sans", 0, 8)); // NOI18N
        qualitySlider.setMajorTickSpacing(10);
        qualitySlider.setMinimum(1);
        qualitySlider.setMinorTickSpacing(5);
        qualitySlider.setPaintLabels(true);
        qualitySlider.setPaintTicks(true);
        qualitySlider.setToolTipText("This bar modifies the quality of the graph. Increasing quality shrinks the graph but gives more definate results, while reducing quality gives more results but they may not be the most telling.");
        qualitySlider.setValue((int)(Graph.DEFAULT_QUALITY*100));
        qualitySlider.setEnabled(false);
        qualitySlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                qualitySliderStateChanged(evt);
            }
        });

        javax.swing.GroupLayout qualitySliderPanelLayout = new javax.swing.GroupLayout(qualitySliderPanel);
        qualitySliderPanel.setLayout(qualitySliderPanelLayout);
        qualitySliderPanelLayout.setHorizontalGroup(
            qualitySliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qualitySliderPanelLayout.createSequentialGroup()
                .addComponent(qualitySlider, javax.swing.GroupLayout.DEFAULT_SIZE, 1079, Short.MAX_VALUE)
                .addContainerGap())
        );
        qualitySliderPanelLayout.setVerticalGroup(
            qualitySliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qualitySliderPanelLayout.createSequentialGroup()
                .addComponent(qualitySlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        gRender.setBackground(new java.awt.Color(254, 254, 254));

        scaleSlider.setOrientation(javax.swing.JSlider.VERTICAL);
        scaleSlider.setPaintLabels(true);
        scaleSlider.setPaintTicks(true);
        scaleSlider.setValue(100);
        scaleSlider.setEnabled(false);
        scaleSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                scaleSliderStateChanged(evt);
            }
        });

        jLabel1.setText("Zoom");

        javax.swing.GroupLayout graphPanelLayout = new javax.swing.GroupLayout(graphPanel);
        graphPanel.setLayout(graphPanelLayout);
        graphPanelLayout.setHorizontalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(graphPanelLayout.createSequentialGroup()
                .addGroup(graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(scaleSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gRender, javax.swing.GroupLayout.DEFAULT_SIZE, 1057, Short.MAX_VALUE))
        );
        graphPanelLayout.setVerticalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, graphPanelLayout.createSequentialGroup()
                .addGroup(graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(gRender, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, graphPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scaleSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)))
                .addContainerGap())
        );

        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gradeapp/images/help.png"))); // NOI18N
        helpButton.setText("Help!");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gradeapp/images/document-save.png"))); // NOI18N
        saveButton.setText("Save");
        saveButton.setEnabled(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(graphPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(qualitySliderPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(goodGradePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 725, Short.MAX_VALUE)
                        .addComponent(helpButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailButton)
                    .addComponent(printButton)
                    .addComponent(helpButton)
                    .addComponent(saveButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(goodGradePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(qualitySliderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Runs when the load *.xls button is pressed
     * @param evt - the Event generated by the action
     */
    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter xlsfilter = new FileNameExtensionFilter("XLS Files","xls");
        fc.setFileFilter(xlsfilter);
        if (fc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
            return;
        File input = fc.getSelectedFile();
        try {
            Graph.createGraph(input);

            qualitySlider.setMaximum(100);
            qualitySlider.setValue((int)(Graph.getGraph().getQuality()*100));
            gradeSlider.setMaximum(Graph.getGraph().getAnswerkey().size());
            gradeSlider.setValue((int) (Graph.getGraph().getAnswerkey().size() * 0.8f));

            gradeSlider.setEnabled(true);// TODO add your handling code here:
            emailButton.setEnabled(true);
            saveButton.setEnabled(true);
            printButton.setEnabled(true);
            scaleSlider.setEnabled(true);
            qualitySlider.setEnabled(true);

            //gRender.render();
        } catch (FileNotFoundException ex) {
            JOptionPane NotFoundFrame = new JOptionPane();
            JOptionPane.showMessageDialog(NotFoundFrame, "Cannot Find the Requested File. Please Enter Proper File Name.", "Missing File", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane IOFrame = new JOptionPane();
            JOptionPane.showMessageDialog(IOFrame, "Problems Opening File. Please Check Permissions", "File Error", JOptionPane.ERROR_MESSAGE);
        } catch (GraphFormatException ex) {
            JOptionPane FormatFrame = new JOptionPane();
            JOptionPane.showMessageDialog(FormatFrame, "Problems with Sheet Data. Please Regenerate File or Load Another.", "File Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_loadButtonActionPerformed

    /**
     * Executed when the Help! button is pressed.
     * @param evt the event generated from clicking on the help button
     */
    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        HelperButton.createAndShowGUI();
    }//GEN-LAST:event_helpButtonActionPerformed

    /**
     * Runs when the grade slider is moved
     * @param evt
     */
    private void gradeSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_gradeSliderStateChanged
        Graph.getGraph().setGoodgrade(gradeSlider.getValue());   
        String title = "Good Grade - ";
        ((TitledBorder)goodGradePanel.getBorder()).setTitle(title + gradeSlider.getValue() + "/" + Graph.getGraph().getAnswerkey().size());
        goodGradePanel.repaint();
        gRender.render();
    }//GEN-LAST:event_gradeSliderStateChanged

    /**
     *
     * @param evt
     */
    private void qualitySliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_qualitySliderStateChanged
        Graph.getGraph().setQuality(qualitySlider.getValue()/100.0f);
        String title = "Quality - ";
        ((TitledBorder)qualitySliderPanel.getBorder()).setTitle(title + qualitySlider.getValue() + "%");
        qualitySliderPanel.repaint();
        gRender.render();
    }//GEN-LAST:event_qualitySliderStateChanged


    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        GraphSave.saveImage(gRender.getGraph());
    }//GEN-LAST:event_saveButtonActionPerformed

    private void emailButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        File tmpFile = GraphSave.tempMaker(gRender.getGraph());
        // Thanks gRender private, had to pass in tmpFile
        // O Well
        // Its Static so I can pull this shit
        EmailSender.sentEmail(tmpFile);
    }                                                                       

    private void scaleSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_scaleSliderStateChanged
        int c = scaleSlider.getValue();
        gRender.getGraph().setScale(c/100.0);
    }//GEN-LAST:event_scaleSliderStateChanged

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        GraphPrint.printComponent(gRender.getGraph());
    }//GEN-LAST:event_printButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GradeApp gradeApp = new GradeApp();
                //Center the app by setting it relative to null. This is a
                //weird property of the "setLocationRelativeTo" method.
                gradeApp.setLocationRelativeTo(null);
                gradeApp.setVisible(true);
                gradeApp.setTitle("The Weeder");
                gradeApp.setDefaultCloseOperation(EXIT_ON_CLOSE);
                // Ask User For Confirmation of Close
             
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton emailButton;
    private gradeapp.GRender gRender;
    private javax.swing.JPanel goodGradePanel;
    private javax.swing.JSlider gradeSlider;
    private javax.swing.JPanel graphPanel;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton printButton;
    private javax.swing.JSlider qualitySlider;
    private javax.swing.JPanel qualitySliderPanel;
    private javax.swing.JButton saveButton;
    private javax.swing.JSlider scaleSlider;
    // End of variables declaration//GEN-END:variables

}
