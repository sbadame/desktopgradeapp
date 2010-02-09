package gradeapp;

import java.io.File;

/**
 * A singleton class that holdes the graph information from the loaded
 * spreadsheet.
 */
public class Graph {

    /**
     * The singleton instance of the graph
     */
    private static Graph graph;

    public static Graph createGraph(File excelFile){
        if (graph == null)
            graph = new Graph(excelFile);
        return graph;
    }

    public static Graph getGraph(){
        return graph;
    }

    // ---- End static stuff, all classes from here -------------------------
    
    //Default settings
    private float goodgrade = 80; 
    private float noise = 0.7f;

    /**
     * Parses the excelFile, gathers the data and
     * @param excelFile the excel file to be pased
     */
    private Graph(File excelFile){

    }

    /**
     * @return the goodgrade
     */
    public float getGoodgrade() {
        return goodgrade;
    }

    /**
     * @param goodgrade the goodgrade to set
     */
    public void setGoodgrade(float goodgrade) {
        this.goodgrade = goodgrade;
    }

    /**
     * @return the noise
     */
    public float getNoise() {
        return noise;
    }

    /**
     * @param noise the noise to set
     */
    public void setNoise(float noise) {
        this.noise = noise;
    }

    public static void main(String[] args) {
        System.out.println("hi");
    }
}
