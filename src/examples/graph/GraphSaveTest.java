package graph;

import gradeapp.GraphSave;

public class GraphSaveTest {

    public static void main(String[] args) {
        GraphTest jGraphTest = new GraphTest();
        GraphSave.saveImage(jGraphTest, "sampledata/test2.png");
    }

}
