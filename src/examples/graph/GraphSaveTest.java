package graph;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.File;
import java.io.IOException;

public class GraphSaveTest {

    public static void saveImage(Component myComponent, String filename) {
        Dimension size = myComponent.getSize();
        BufferedImage myImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = myImage.createGraphics();
        myComponent.paint(g2);
        File finishedproduct= new File(filename);
       
        try{
            ImageIO.write(myImage, "png", finishedproduct);
        
        }catch(IOException e){
            System.out.println("Error saving: " + e);
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
