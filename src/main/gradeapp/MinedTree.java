package gradeapp;

import java.util.*;

/**
 * A MinedTree is created from an array of student data using the ID3 algorithm.
 * The noise threshold of the tree is adjusted and set at creation time.  This
 * is meant to serve as a useful library for Software Engineering students
 * working on their project.
 * @author Peter Boothe - peter.boothe@manhattan.edu
 * @date 9 Feb 2010
 */
//Sandro here: this is still a broken version, just using it so that I can
//start building
public class MinedTree {
    int correct;
    int[] key;
    Collection<int[]> students;

    String message;

    MinedTree right, wrong;

    /**
     * Make a new MinedTree from the given inputs.
     * @param message The message that belongs in the root node.
     * @param threshold A float between 0 and 1 inclusive.  Closer 1 indicates
     * a smaller, less exact tree, while closer to 0 indicates a larger tree.
     * @param correct The number correct that serves as the dividing line
     * between high and low quality.
     * @param key The answer key.  This array should be the same length as all
     * the arrays in students.
     * @param students This is the list of student answers
     */
    public MinedTree(String message, float threshold, int correct, int[] key,
                Collection<int[]> students)
    {
        this.correct = correct;
        this.key = key;
        this.students = students;

        // find the most incisive question
        int bestQuestion = -1;
        float bestQuality = 0;
        for (int question = 0; question < key.length; question++) {
            float quality = splitQuality(question);
            if (quality >= threshold && quality > bestQuality) {
                bestQuestion = question;
            }
        }

        // if there was a question incisive enough, recurse
        if (bestQuestion != -1) {
            right = new MinedTree("They got question " + bestQuestion + " right",
                    threshold, correct, key, findAnswers(bestQuestion, true));
            wrong = new MinedTree("They got question " + bestQuestion + " wrong",
                    threshold, correct, key, findAnswers(bestQuestion, false));
        } else {
        // if we didn't find a good question, provide a summary
            message += "\n\n";
            //int numGood = count(key, correct, students, true);
            //int numBad = count(key, correct, students, false);

        }
    }

    float splitQuality(int question)
    {
        return -1;
    }

    Collection<int[]> findAnswers(int question, boolean right)
    {
        Collection<int[]> peeps = new LinkedList<int[]>();
        for (int[] person : students) {
            if ((person[question] == key[question]) == right) {
                peeps.add(person);
            }
        }
        return peeps;
    }
}