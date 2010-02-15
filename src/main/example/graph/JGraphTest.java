package example.graph;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphContext;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;

public class JGraphTest extends JFrame {

    public JGraphTest(){
        //DefaultGraphModel model = new DefaultGraphModel();
    }

    public static void main(String[] args) {
        GraphModel model  = new DefaultGraphModel();
        GraphLayoutCache view = new GraphLayoutCache(model, new DefaultCellViewFactory());
        JGraph graph = new JGraph(model, view);


        DefaultGraphCell[] cells = new DefaultGraphCell[3];
        //0
        cells[0] = new DefaultGraphCell("Hello");
        GraphConstants.setBounds(cells[0].getAttributes(), new Rectangle2D.Double(20,20,40,20));
        GraphConstants.setGradientColor(cells[0].getAttributes(), Color.ORANGE);
        GraphConstants.setOpaque(cells[0].getAttributes(), true);
        DefaultPort port0 = new DefaultPort();
        cells[0].add(port0);

        //1
        cells[1] = new DefaultGraphCell("World");
        GraphConstants.setBounds(cells[1].getAttributes(), new Rectangle2D.Double(140,140,40,20));
        GraphConstants.setGradientColor(cells[1].getAttributes(), Color.ORANGE);
        GraphConstants.setOpaque(cells[1].getAttributes(), true);
        DefaultPort port1 = new DefaultPort();
        cells[1].add(port1);

        DefaultEdge edge = new DefaultEdge();
        edge.setSource(cells[0].getChildAt(0));
        edge.setTarget(cells[1].getChildAt(0));
        cells[2] = edge;

        int arrow = GraphConstants.ARROW_CLASSIC;
        GraphConstants.setLineEnd(edge.getAttributes(), arrow);
        GraphConstants.setEndFill(edge.getAttributes(), true);

        graph.getGraphLayoutCache().insert(cells);

        JGraphTest jGraphTest = new JGraphTest();
        jGraphTest.getContentPane().add(new JScrollPane(graph));
        jGraphTest.pack();
        jGraphTest.setLocationRelativeTo(null);
        jGraphTest.setVisible(true);
    }
}
