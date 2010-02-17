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
import java.io.File;
import java.io.IOException;

public class GraphSave {

    public static void saveImage(Component myComponent) {
        Dimension size = myComponent.getSize();
        BufferedImage myImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = myImage.createGraphics();
        myComponent.paint(g2);
        JFileChooser open_window;
        open_window = new JFileChooser();
        open_window.showSaveDialog(null);
        open_window.setFileFilter(null);
        File finishedproduct = open_window.getSelectedFile();
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
