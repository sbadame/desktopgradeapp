package graph;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.tree.JGraphTreeLayout;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import org.jgraph.JGraph;
import org.jgraph.graph.*;

/**
 * This code is based on the documentation found in jgraphmanual.pdf
 * Specifically pages 106-113 for Tree Layout info and 17-22 for everything else
 * @author Sandro Badame <a href="mailto:s.badame@gmail.com">s.badame&amp;gmail.com</a>
 */
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
        GraphConstants.setGradientColor(cells[1].getAttributes(), Color.BLUE);
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

        //Build our layout
        JGraphTreeLayout layout = new JGraphTreeLayout();
        layout.setAlignment(SwingConstants.TOP);
        layout.setOrientation(SwingConstants.NORTH);
        layout.setCombineLevelNodes(false);

        //Do the tree layout here
        JGraphFacade facade = new JGraphFacade(graph, new Object[]{});
        layout.run(facade); //Run the layout on the facade
        //Obtain a map of the resulting changes from the facade
        Map nested = facade.createNestedMap(true, true);
        graph.getGraphLayoutCache().edit(nested); //Apply changes

        JGraphTest jGraphTest = new JGraphTest();
        jGraphTest.getContentPane().add(new JScrollPane(graph));
        jGraphTest.pack();
        jGraphTest.setLocationRelativeTo(null);
        jGraphTest.setVisible(true);
        //adding this line below to try out the print function
        GraphPrintTest.printComponent(jGraphTest);
        jGraphTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
