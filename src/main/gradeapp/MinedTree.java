package gradeapp;

import java.util.*;
import java.io.*; // for the tests

/**
 * A MinedTree is created from an array of student data using the ID3 algorithm.
 * The noise threshold of the tree is adjusted and set at creation time.  This
 * is meant to serve as a useful library for Software Engineering students
 * working on their project.
 * @author Peter Boothe - peter.boothe@manhattan.edu
 * @date 9 Feb 2010
 */
public class MinedTree {
    int correct;
    String[] key;
    Collection<String[]> students;

    public int numGood, numBad;
    public int question=-1;
    public MinedTree right=null, wrong=null;

    /**
     * Make a new MinedTree from the given inputs.
     * @param threshold A double between 0 and 1 inclusive.  Closer 1 indicates
     * a smaller, less exact tree, while closer to 0 indicates a larger tree.
     * @param correct The number correct that serves as the dividing line
     * between high and low quality.
     * @param key The answer key.  This array should be the same length as all
     * the arrays in students.
     * @param students This is the list of student answers
     */
    public MinedTree(double threshold, int correct, String[] key,
            Collection<String[]> students)
    {
        this(threshold, correct, key, students, -1);
    }

    /**
     * Make a new MinedTree from the given inputs.
     * @param threshold A double between 0 and 1 inclusive.  Closer 1 indicates
     * a smaller, less exact tree, while closer to 0 indicates a larger tree.
     * @param correct The number correct that serves as the dividing line
     * between high and low quality.
     * @param key The answer key.  This array should be the same length as all
     * the arrays in students.
     * @param students This is the list of student answers
     * @param question The question that this node was split upon.
     */
    public MinedTree(double threshold, int correct, String[] key,
            Collection<String[]> students, int question)
    {
        this.correct = correct;
        this.key = key;
        this.students = students;
        this.question = question;


        numGood = count(true, students);
        numBad = count(false, students);

        // find the most incisive question
        int bestQuestion = -1;
        double bestQuality = 0;
        for (int i = 0; i < key.length; i++) {
            double quality = splitQuality(i);
            if (quality >= threshold && quality > bestQuality) {
                bestQuestion = i;
            }
        }

        // if there was a question incisive enough, recurse
        if (bestQuestion != -1) {
            //System.out.println("Make right");
            right = new MinedTree(threshold, correct, key,
                    findAnswers(bestQuestion, true), bestQuestion);
            //System.out.println("Make wrong");
            wrong = new MinedTree(threshold, correct, key,
                    findAnswers(bestQuestion, false), bestQuestion);
        }
    }

    double findEntropy(Collection<String[]> data)
    {
        double p_good = ((double)count(true, data)) / data.size();
        double p_bad = ((double)count(false, data)) / data.size();

        double entropy;

        if (p_good > 0 && p_bad > 0)
            entropy = -1.0 / Math.log(2) * (p_good * Math.log(p_good) + p_bad * Math.log(p_bad));
        else
            entropy = 0;

        //System.out.println("p_good (" + p_good + ")  p_bad (" + p_bad +")" + "  entropy (" + entropy + ")");

        return entropy;
    }

    double splitQuality(int question)
    {
        double classEntropy = findEntropy(students);
        Collection<String[]> right = findAnswers(question, true);
        Collection<String[]> wrong = findAnswers(question, false);

        double splitEntropy = (right.size() * findEntropy(right) + wrong.size() * findEntropy(wrong)) / ((double)students.size());

        //System.out.println("question " + question);
        //System.out.println("ce " + classEntropy + " se " + splitEntropy);
        //System.out.println("se " + right.size() + " ws " + wrong.size());
        //System.out.println("correct " + correct);
        return classEntropy - splitEntropy;
    }

    int count(boolean passing, Collection<String[]> students)
    {
        int c = 0;
        for (String[] person : students) {
            int corr = 0;
            for (int i = 0; i < key.length; i++) {
                if (person[i].equals(key[i])) corr++;
            }

            if ((corr >= correct) == passing) c++;
        }

        return c;
    }

    Collection<String[]> findAnswers(int question, boolean right)
    {
        return findAnswers(question, right, students);
    }


    Collection<String[]> findAnswers(int question, boolean right,
            Collection<String[]> students)
    {
        Collection<String[]> peeps = new LinkedList<String[]>();
        for (String[] person : students) {
            if ((person[question].equals(key[question])) == right) {
                peeps.add(person);
            }
        }
        return peeps;
    }

    public String toString() {
        String m = "Top of tree\n ";
        m += count(true, students) + "/" + students.size() + " good\n ";
        m += count(false, students) + "/" + students.size() + " bad\n";
        if (right != null && wrong != null) {
            return m + "\n" + right.toString(true) + "\n" + wrong.toString(false);
        } else {
            return m;
        }
    }

    String toString(boolean gotItCorrect)
    {
        if (question == -1) throw new RuntimeException("THIS SHOULD NOT HAPPEN");
        String thisMessage = "They got question " + question + (gotItCorrect ? " right" : " wrong") + "\n ";
        thisMessage += count(true, students) + "/" + students.size() + " good\n ";
        thisMessage += count(false, students) + "/" + students.size() + " bad\n";
        if (right != null && wrong != null) {
           thisMessage += "\n" + right.toString(true) + "\n" + wrong.toString(false);
        }
        return "(" + thisMessage + ")";
    }

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String line = br.readLine().trim();
        String[] key = new String[line.length()];
        for (int i = 0; i < key.length; i++) {
            key[i] = "" + line.charAt(i);
            System.out.print(key[i]);
        }
        System.out.println();

        Collection<String[]> answers = new LinkedList<String[]>();
        while ((line = br.readLine()) != null) {
            line = line.trim();
            String[] answer = new String[key.length];
            for (int i = 0; i < key.length; i++) {
                answer[i] = "" + line.charAt(i);
                System.out.print(answer[i]);
            }
            System.out.println();
            answers.add(answer);
        }

        System.out.println(new MinedTree(.2, (int)(.7 * key.length), key, answers));
    }
}