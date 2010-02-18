/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gradeapp;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JOptionPane;
import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SpringLayout;

/**
 *
 * @author Bigantfbi333
 */
public class GayStatusBar implements Runnable{
    public void run(){
  JFrame jfrProgress = new JFrame("Emailing File In Progress...");
  Container contentPane = jfrProgress.getContentPane();
  //SpringLayout layout = new SpringLayout();
  contentPane.setLayout(null);
  jfrProgress.setLocation(522,350);
  jfrProgress.setSize(400,100);
  jfrProgress.setVisible(true);

  int progress = 100;
  JProgressBar progressBar = new JProgressBar(0, progress);
  progressBar.repaint();
  jfrProgress.getContentPane().add(progressBar,BorderLayout.CENTER);
  progressBar.setValue(0);
  progressBar.setStringPainted(true);
  progressBar.setString("Step 1 - Setting Hosts");
  progressBar.setSize(500, 40);
  progressBar.setVisible(true);
  progressBar.setValue(0);
  progressBar.setStringPainted(true);
    }

}
