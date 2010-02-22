package graph;

import javax.swing.JFrame;

/**
 * With the boothe algorithm, this code is now useless
 * @author Sandro Badame <a href="mailto:s.badame@gmail.com">s.badame&amp;gmail.com</a>
 */
public class GRenderExample extends JFrame{

    public GRenderExample(){
        super("Example");

    }

    public static void main(String[] args) {
        GRenderExample graphRenderExample = new GRenderExample();
        graphRenderExample.setSize(800, 600);
        graphRenderExample.setLocationRelativeTo(null);
        graphRenderExample.setVisible(true);
        graphRenderExample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
