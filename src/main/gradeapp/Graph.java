package gradeapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

/**
 * A singleton class that holdes the graph information from the loaded
 * spreadsheet.
 */
public class Graph {

//Default Arguments
    public static final int DEFAULT_GOODGRADE = 0;
    public static final float DEFAULT_QUALITY = 0.15f;

    /**
     * The singleton instance of the graph
     */
    private static Graph graph;

    public static Graph createGraph(File excelFile) throws FileNotFoundException, IOException, GraphFormatException {
        graph = new Graph(excelFile);
        return getGraph();
    }

    public static Graph getGraph() {
        return graph;
    }

    // ---- End static stuff, all classes from here -------------------------
    
    //Default settings
    private int goodgrade = DEFAULT_GOODGRADE;
    private float quality = DEFAULT_QUALITY;
    private Vector<Vector<Character>> grades = new Vector<Vector<Character>>();
    private Vector<Character> answerkey = new Vector<Character>();
    private MinedTree tree;
    private GRender renderer;

    /**
     * Parses the excelFile, gathers the data and
     * @param excelFile the excel file to be pased
     */
    private Graph(File excelFile) throws FileNotFoundException, IOException, GraphFormatException {
        ExcelReader excelReader = new ExcelReader(excelFile);
        grades = excelReader.getGrades();
        answerkey = excelReader.getAnswerKey();
        BootheAlgo();
    }

    private void BootheAlgo(){
        ///UGHGHG BOOTHE!!!! Converting from Vector<Vector<Charactor>> to
        //Vector<String[]> like Boothe wants.
        Vector<String[]> students = new Vector<String[]>();
        for (Vector<Character> student : grades) {
            String[] s = new String[student.size()];
            for (int i = 0; i < student.size(); i++) {
                s[i] = String.valueOf(student.get(i));
            }
            students.add(s);
        }
        String[] ans = new String[answerkey.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(answerkey.get(i));
        }
        tree = new MinedTree(getQuality(), getGoodgrade(), ans, students);
    }


    /**
     * @return the goodgrade
     */
    public int getGoodgrade() {
        return goodgrade;
    }

    /**
     * @param goodgrade the goodgrade to set
     */
    public void setGoodgrade(int goodgrade) {
        this.goodgrade = goodgrade;
        BootheAlgo();
    }

    /**
     * @return the quality
     */
    public float getQuality() {
        return quality;
    }

    /**
     * @param quality the quality to set
     */
    public void setQuality(float quality) {
        this.quality = quality;
        BootheAlgo();
    }

    public static void main(String[] args) {
        System.out.println("hi");
    }

    /**
     * @return the grades
     */
    public Vector<Vector<Character>> getGrades() {
        return grades;
    }

    /**
     * @param grades the grades to set
     */
    public void setGrades(Vector<Vector<Character>> grades) {
        this.grades = grades;
    }

    /**
     * @return the answerkey
     */
    public Vector<Character> getAnswerkey() {
        return answerkey;
    }

    /**
     * @param answerkey the answerkey to set
     */
    public void setAnswerkey(Vector<Character> answerkey) {
        this.answerkey = answerkey;
    }

    /**
     * @return the tree
     */
    public MinedTree getTree() {
        return tree;
    }

    /**
     * @return the renderer
     */
    public GRender getRenderer() {
        return renderer;
    }

    /**
     * @param renderer the renderer to set
     */
    public void setRenderer(GRender renderer) {
        this.renderer = renderer;
    }
}
