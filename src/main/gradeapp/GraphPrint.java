/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gradeapp;

/**
 *
 * @author sandro
 */
import java.awt.*;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;

import org.jgraph.JGraph;

import java.awt.print.*;
public class GraphPrint implements Printable{
    
      private JGraph graph;
      
      
      public static void printComponent(JGraph c) {
        new GraphPrint(c).print();
      }
      
      public GraphPrint(JGraph graph) {
        this.graph = graph;
      }
      
      public void print() {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet(); 
        aset.add(OrientationRequested.LANDSCAPE); 
 
        printJob.setPrintable(this);
        if (printJob.printDialog())
          try {
            printJob.print(aset);
          } catch(PrinterException pe) {
            System.out.println("Error printing: " + pe);
          }
      }
      //code found at http://www.developerdotstar.com/community/node/124
      public int print(Graphics g, PageFormat pf, int pageIndex) {
                int response = NO_SUCH_PAGE;
             
                Graphics2D g2 = (Graphics2D) g;
             
                //  for faster printing, turn off double buffering
                disableDoubleBuffering(graph);
             
                Dimension d = graph.getSize(); //get size of document
                double panelWidth = d.width; //width in pixels
                double panelHeight = d.height; //height in pixels
             
                double pageHeight = pf.getImageableHeight(); //height of printer page
                double pageWidth = pf.getImageableWidth(); //width of printer page
             
                double scale = pageWidth / panelWidth;
                int totalNumPages = (int) Math.ceil(scale * panelHeight / pageHeight);
             
                //  make sure not print empty pages
                if (pageIndex >= totalNumPages) {
                  response = NO_SUCH_PAGE;
                }
                else {
             
                  //  shift Graphic to line up with beginning of print-imageable region
                  g2.translate(pf.getImageableX(), pf.getImageableY());
             
                  //  shift Graphic to line up with beginning of next page to print
                  g2.translate(0f, -pageIndex * pageHeight);
             
                  //  scale the page so the width fits...
                  g2.scale(scale, scale);
             
                  graph.paint(g2); //repaint the page for printing
             
                  enableDoubleBuffering(graph);
                  response = Printable.PAGE_EXISTS;
                }
                return response;
              }

      public static void disableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(false);
      }

      public static void enableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(true);
      }
    }
