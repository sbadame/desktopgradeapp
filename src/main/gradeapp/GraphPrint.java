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
import java.awt.print.*;
public class GraphPrint implements Printable{
    
      private Component componentToBePrinted;
      
      
      public static void printComponent(Component c) {
        new GraphPrint(c).print();
      }
      
      public GraphPrint(Component componentToBePrinted) {
        this.componentToBePrinted = componentToBePrinted;
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

      public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
          double dl = pageFormat.getImageableWidth();
          double dh = pageFormat.getImageableHeight();
           
          double xScale = dl / componentToBePrinted.getWidth();
          double yScale = dh / componentToBePrinted.getHeight();
        if (pageIndex >0) {
          return(NO_SUCH_PAGE);
        } else {
          Graphics2D g2d = (Graphics2D)g;
          g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
          g2d.scale( xScale, yScale);
          disableDoubleBuffering(componentToBePrinted);
          componentToBePrinted.paint(g2d);
          enableDoubleBuffering(componentToBePrinted);
          return(PAGE_EXISTS);
        }
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
