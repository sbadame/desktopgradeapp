package gradeapp;
//save function use by calling GraphSave.saveImage(Component myComponent) to save to a png
//
//temp function added use by calling GraphSave.tempMaker(Component myComponent) returns the temp File object.
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.IOException;
/**
 * Responsible for saving a given JFrame Component into a *.png as well as
 * generating a temp File for EmailSender to email the image.
 * 
 * @author Andres Ramirez
 */
public class GraphSave {
    /**
     *   Prepares a given JFrame Component to be saved into an image of *.png format.
     *  Also opens incorporates a JFileChooser so the user can select 
     *  where to save the image. The user need only enter the name of the 
     *  image, the extension is added automatically.
     * @param myComponent of type Component
     */
    public static void saveImage(Component myComponent) {
        String name;
        File finishedproduct=null;
        FileFilter pngfilter = new FileNameExtensionFilter("PNG Files","png");
        Dimension size = myComponent.getSize();
        BufferedImage myImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = myImage.createGraphics();
        myComponent.paint(g2);
        JFileChooser open_window;
        open_window = new JFileChooser();
        open_window.setFileFilter(pngfilter);//this filter is useless. 
        //it is here just to reassure the user that even if he/she does 
        //not specify the filename, the image will be saved to the png format
        open_window.showSaveDialog(null);
        name=null;
        try{
            name = open_window.getSelectedFile().getAbsolutePath();
        }catch(NullPointerException err){}
        if(name!=null){
            name+=".png";
            finishedproduct = new File(name);
        }
        if(finishedproduct!=null){
        try{
            ImageIO.write(myImage, "png", finishedproduct);
        
        }catch(IOException e){
            System.out.println("Error saving: " + e);
        }
        }
    }

    /**
     *   Creates a temp file for later use. 
     *  Once the call to this method is complete, the temp file is deleted.
     *  Returns a File object. 
     * @param myComponent of type Component
     * @return temp of type File
     */
    public static File tempMaker(Component myComponent){
        Dimension size = myComponent.getSize();
        BufferedImage myImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = myImage.createGraphics();
        myComponent.paint(g2);
        File temp= new File("test.png");
        try {
            temp = File.createTempFile("test",".png");
            ImageIO.write(myImage, "png", temp);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        temp.deleteOnExit();
        return temp;

    }
}
