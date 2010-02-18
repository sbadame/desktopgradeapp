package gradeapp;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.tree.JGraphTreeLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;

/**
 * Holds a JGraph and does work on it
 * @author sandro
 */
public class GRender extends JPanel{
    
    JGraph graph;
    DefaultGraphModel model;
    GraphLayoutCache view;
    DefaultGraphCell root;
    JGraphFacade facade;
    JGraphTreeLayout layout;

    public GRender(){
        //Make a graph
        model = new DefaultGraphModel();
        view = new GraphLayoutCache(model, new DefaultCellViewFactory());
        graph = new JGraph(model, view);
        graph.setAntiAliased(true);

        //Configure our layout
        layout = new JGraphTreeLayout();
        layout.setAlignment(SwingConstants.TOP);
        layout.setOrientation(SwingConstants.NORTH);
        layout.setCombineLevelNodes(false);

        //Add it to our selves
        setLayout(new GridLayout());
        add(new JScrollPane(graph));
    }

    public void render(){
        Graph g = Graph.getGraph();
        if (g == null) return;

        MinedTree tree = g.getTree();
        if (tree == null) return;

        render(tree);
    }

    public void render(MinedTree tree){
        //If the graph is already displaying a tree, then render it
        if (root !=  null)
           graph.getGraphLayoutCache().remove(new Object[]{root}, true, true);


        ArrayList<DefaultGraphCell> cells = buildCellsFromTree(tree);
        root = cells.get(0);

        //Add the new tree
        DefaultGraphCell[] acells = cells.toArray(new DefaultGraphCell[]{});
        graph.getGraphLayoutCache().insert(acells);

        //Run the layout
        facade = new JGraphFacade(graph, new Object[]{root});
        layout.run(facade);
        Map nested = facade.createNestedMap(true, true);
        graph.getGraphLayoutCache().edit(nested);//Apply the layout's measurements
        repaint();//Repaint!
    }

    /**
     * Takes care of all of the hardwork that goes into builing cells from 
     * trees.
     * @param tree
     * @return
     */
    private ArrayList<DefaultGraphCell> buildCellsFromTree(MinedTree tree){
        ArrayList<DefaultGraphCell> cells = new ArrayList<DefaultGraphCell>();
        DefaultGraphCell cell = getCell(tree);
        DefaultPort port = new DefaultPort();
        cell.add(port);
        cells.add(cell);

        if (tree.right != null && tree.wrong != null) {
            ArrayList<DefaultGraphCell> wrongCells = buildCellsFromTree(tree.wrong);
            ArrayList<DefaultGraphCell> rightCells = buildCellsFromTree(tree.right);
            cells.addAll(wrongCells);
            cells.addAll(rightCells);
            cells.add(getEdge(cell, wrongCells.get(0)));
            cells.add(getEdge(cell, rightCells.get(0)));
        }

       return cells;
    }

    private DefaultEdge getEdge(DefaultGraphCell source, DefaultGraphCell target){
        DefaultEdge edge = new DefaultEdge();
        edge.setSource(source.getChildAt(0));
        edge.setTarget(target.getChildAt(0));
        GraphConstants.setLineEnd(edge.getAttributes(), GraphConstants.ARROW_CLASSIC); 
        GraphConstants.setEndFill(edge.getAttributes(), true);
        GraphConstants.setEditable(edge.getAttributes(), false);
        return edge;
    }

    private DefaultGraphCell getCell(MinedTree tree){
        DefaultGraphCell cell = new DefaultGraphCell(tree.message);
        GraphConstants.setBounds(cell.getAttributes(), new Rectangle2D.Double(140,140,40,20));
        GraphConstants.setGradientColor(cell.getAttributes(), Color.ORANGE);
        GraphConstants.setOpaque(cell.getAttributes(), true);
        GraphConstants.setAutoSize(cell.getAttributes(), true);
        GraphConstants.setEditable(cell.getAttributes(), false);
        return cell;
    }

}