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

public class GraphSave {

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
