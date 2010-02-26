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

    //lets us swap between showing the "click on the load button!" text
    //and actually showing the graph
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
        graph.setDisconnectOnMove(true);
        graph.setConnectable(true);
        graph.setDisconnectOnMove(false);
        graph.setDisconnectable(false);
        graph.setEdgeLabelsMovable(false);

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

    /**
     * Same as {@link #render(gradeapp.MinedTree) } except it does the following:
     * <code>render(Graph.getGraph());</code> This method does nothing if
     * the graph or tree is null.
     */
    public void render(){
        Graph g = Graph.getGraph();
        if (g == null) return;

        MinedTree tree = g.getTree();
        if (tree == null) return;

        render(tree);
    }

    /**
     * Does the hard work of deleting the current tree, creating a graph
     * from the given tree and displayig it on the jGraph.
     * @param tree the tree to render.
     */
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
     * Takes care of all of the hardwork that goes into builing a JGraph from
     * a MinedTree. This method takes care of traversing the tree, and creating
     * Cells using {@link #getCell(gradeapp.MinedTree, boolean) } and putting edges
     * between them using {@link #getEdge(org.jgraph.graph.DefaultGraphCell, org.jgraph.graph.DefaultGraphCell) }.
     * This method only has the algoritm for conversion and the structure between
     * cells. To change how things look, go to getEdge or getCell.
     * @param tree the Tree to turn into a graph.
     * @return the list of cells that make up the graph to be rendered. Note:
     *          the first element of this list is the root of the tree.
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

    /**
     * Given 2 cells it creates an edge between them. Edit this to change what
     * edges look like.
     * @param source The cell from which a cell begins
     * @param target The cell in which a cell ends
     * @return The edge that is created
     */
    protected DefaultEdge getEdge(DefaultGraphCell source, DefaultGraphCell target){
        DefaultEdge edge = new DefaultEdge();
        edge.setSource(source.getChildAt(0));
        edge.setTarget(target.getChildAt(0));
        GraphConstants.setLineEnd(edge.getAttributes(), GraphConstants.ARROW_CLASSIC); 
        GraphConstants.setEndFill(edge.getAttributes(), true);
        GraphConstants.setEditable(edge.getAttributes(), false);
        GraphConstants.setSelectable(edge.getAttributes(), false);
        return edge;
    }

    /**
     * Transforms a MinedTree Node from boothe into a DefaultGraphCell.
     * This basically converts from boothe -> JGraph. Edit this to change how
     * cells look. Since cell looks are dependent on whether the cell is correct
     * or not, we need to know if they got it correct or not.
     * @param tree the tree from the boothe algorithm to render
     * @param gotItCorrect whether this cell got the question correct or not.
     * @return the graphcell that represents the MinedTree passed.
     */
    protected DefaultGraphCell getCell(MinedTree tree, boolean gotItCorrect){
        String s = "<HTML>"; //You make new lines in JGraph by rendering the
                             //With HTML. Crazy? yes. See jgraphmanual.pdf
                             //Whenever there is a <BR> that's a new line.
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
        //Just need to pick an arbitrary size, the layout goes and overrides this
        GraphConstants.setBounds(cell.getAttributes(), new Rectangle2D.Double(140,140,40,20));
        //I like orange.
        GraphConstants.setGradientColor(cell.getAttributes(), Color.ORANGE);
        //Apparently we need to make the cell Opaque?
        GraphConstants.setOpaque(cell.getAttributes(), true);
        //Resize depending on the size of the text that this cell contains
        GraphConstants.setAutoSize(cell.getAttributes(), true);
        //No editing!!!!
        GraphConstants.setEditable(cell.getAttributes(), false);
        return cell;
    }

    /**
     * Gives access to the JGraph object that is rendering the graph.
     * @return the JGraph object showing the graph
     */
    public JGraph getGraph(){
        return graph;
    }

}