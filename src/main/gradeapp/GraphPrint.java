/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gradeapp;


import java.awt.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import org.jgraph.JGraph;
import java.awt.print.*;
/**
 *  Printing is handled in this class.
 *   Code found at http://www.developerdotstar.com/community/node/124 and 
 *   edited to work with rest of the system.
 *   
 * @author Andres Ramirez
 */
public class GraphPrint implements Printable{
    
      private JGraph graph;
      
      /**
       * Opens a print dialog box, from which the user can choose a
       * printer to print the JGraph.
       * @param c of type JGraph
       */
      public static void printComponent(JGraph c) {
        new GraphPrint(c).print();
      }
      
      public GraphPrint(JGraph graph) {
        this.graph = graph;
      }
      /**
       * Used internally by GraphPrint. it sets up the PrinterJob as well 
       * as the printDialog to be displayed.
       */
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
      
      /**
       * Does the actual printing. The code is based on the code found at
       * http://www.developerdotstar.com/community/node/124 which has
       * been modified to fit our necessities.
       * 
       * @param g of type Graphics to paint on
       * @param pf of type PageFormat to set the proper width and height
       * @param pageIndex of type Int to end printing when all pages have been printed
       *
       */
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
      /**
       *   Disables double buffering to make printing faster.
       *  It is called automatically by the printComponent method.
       * @param c of type component to disable double buffering  
       */
      public static void disableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(false);
      }
      /**
       *   Enables double buffering once printing is complete.
       *  It is called automatically by the printComponent method.
       * @param c of type component to enable double buffering  
       */
      public static void enableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(true);
      }
    }
