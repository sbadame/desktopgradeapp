package graph;

import gradeapp.GRender;
import gradeapp.MinedTree;
import javax.swing.JFrame;

public class GRenderExample extends JFrame{

    public GRenderExample(){
        super("Example");
        GRender gr = new GRender();
        MinedTree root = new MinedTree("root");
        root.right = new MinedTree("right");
        root.wrong = new MinedTree("wrong");

        root.right.right = new MinedTree("Sandro");
        root.right.wrong = new MinedTree("Badame");

        root.wrong.right = new MinedTree("Do");
        root.wrong.wrong = new MinedTree("Work");


        gr.render(root);
        setContentPane(gr);

    }

    public static void main(String[] args) {
        GRenderExample graphRenderExample = new GRenderExample();
        graphRenderExample.setSize(800, 600);
        graphRenderExample.setLocationRelativeTo(null);
        graphRenderExample.setVisible(true);
        graphRenderExample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
