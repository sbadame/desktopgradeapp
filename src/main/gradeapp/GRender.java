package gradeapp;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.tree.JGraphTreeLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JLabel;
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
 * A Panel that conains a JScrollPane that contains a JGraph.
 * This holds all of the details of how the Graph is rendered.
 * In the context of this program it is mostly used by
 * {@link GradeApp}
 * @author sandro
 */
public class GRender extends JPanel{

    private static final String GRAPH = "Graph";
    private static final String LOAD = "Load";

    private CardLayout swingLayout = new CardLayout();

    private JGraph graph;
    private DefaultGraphModel model;
    private GraphLayoutCache view;
    private DefaultGraphCell root;
    private JGraphFacade facade;
    private JGraphTreeLayout graphLayout;

    /**
     * Returns an instance where the JGraph is blank.
     */
    public GRender(){
        super();


        //Make a graph
        model = new DefaultGraphModel();
        view = new GraphLayoutCache(model, new DefaultCellViewFactory());
        graph = new JGraph(model, view);
        graph.setAntiAliased(true);

        //Configure our layout
        graphLayout = new JGraphTreeLayout();
        graphLayout.setAlignment(SwingConstants.TOP);
        graphLayout.setOrientation(SwingConstants.NORTH);
        graphLayout.setCombineLevelNodes(false);

        //Add it to our selves
        setLayout(swingLayout);

        JLabel loadText = new JLabel("Click on the \"Load *.xls\" button above"+
                                     " to load a spreadsheet", JLabel.CENTER);

        add(loadText, LOAD);
        add(new JScrollPane(graph), GRAPH);
        swingLayout.show(this, LOAD);
    }

    public void render(){
        Graph g = Graph.getGraph();
        if (g == null) return;

        MinedTree tree = g.getTree();
        if (tree == null) return;

        render(tree);
    }

    public void render(MinedTree tree){
        swingLayout.show(this, GRAPH);
        graph.setModel(new DefaultGraphModel());//Clear the tree by replacing
                                                //the model with a blank new one

        ArrayList<DefaultGraphCell> cells = buildCellsFromTree(tree, true);
        root = cells.get(0);

        //Add the new tree
        DefaultGraphCell[] acells = cells.toArray(new DefaultGraphCell[]{});
        graph.getGraphLayoutCache().insert(acells);

        //Run the layout
        facade = new JGraphFacade(graph, new Object[]{root});
        graphLayout.run(facade);
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
    private ArrayList<DefaultGraphCell> buildCellsFromTree(MinedTree tree, boolean gotItCorrect){
        ArrayList<DefaultGraphCell> cells = new ArrayList<DefaultGraphCell>();
        DefaultGraphCell cell = getCell(tree, gotItCorrect);
        DefaultPort port = new DefaultPort();
        cell.add(port);
        cells.add(cell);

        if (tree.right != null && tree.wrong != null) {
            ArrayList<DefaultGraphCell> wrongCells = buildCellsFromTree(tree.wrong, false);
            ArrayList<DefaultGraphCell> rightCells = buildCellsFromTree(tree.right, true);
            cells.addAll(wrongCells);
            cells.addAll(rightCells);
            cells.add(getEdge(cell, wrongCells.get(0)));
            cells.add(getEdge(cell, rightCells.get(0)));
        }

       return cells;
    }

    protected DefaultEdge getEdge(DefaultGraphCell source, DefaultGraphCell target){
        DefaultEdge edge = new DefaultEdge();
        edge.setSource(source.getChildAt(0));
        edge.setTarget(target.getChildAt(0));
        GraphConstants.setLineEnd(edge.getAttributes(), GraphConstants.ARROW_CLASSIC); 
        GraphConstants.setEndFill(edge.getAttributes(), true);
        GraphConstants.setEditable(edge.getAttributes(), false);
        return edge;
    }

    protected DefaultGraphCell getCell(MinedTree tree, boolean gotItCorrect){
        String s = "<HTML>";
        if (tree.question != -1) {
            s+= "If they got question " + tree.question + (gotItCorrect ? " right" : " wrong") + " then<BR>";
        }
        int good = tree.count(true, tree.students);
        int total = tree.students.size();
        int bad = total-good;
        int goodPercent = (int)(((float)good/total)*100);
        int badPercent = 100 - goodPercent;
        s += bad + " out of " + total+ " did poorly (" + badPercent + "%)";
        s += "<BR>";
        s += good + " out of " + total + " did well (" + goodPercent +"%)";
        s += "</HTML>";
        DefaultGraphCell cell = new DefaultGraphCell(s);
        GraphConstants.setBounds(cell.getAttributes(), new Rectangle2D.Double(140,140,40,20));
        GraphConstants.setGradientColor(cell.getAttributes(), Color.ORANGE);
        GraphConstants.setOpaque(cell.getAttributes(), true);
        GraphConstants.setAutoSize(cell.getAttributes(), true);
        GraphConstants.setEditable(cell.getAttributes(), false);
        return cell;
    }

    public JGraph getGraph(){
        return graph;
    }

}