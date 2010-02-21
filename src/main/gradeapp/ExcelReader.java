package gradeapp;

import java.io.File;
import java.util.Vector;

/**
 *
 * @author sandro
 */
public class ExcelReader {
    public ExcelReader (File in){}
    public Vector <Character> getAnswerKey(){
        Vector<Character> test = new Vector<Character>();
        test.add('a');
        test.add('b');
        test.add('b');
        test.add('c');
        test.add('b');
        return test;
    }
    public Vector <Vector <Character>> getGrades(){
        Vector<Vector <Character>> students = new Vector<Vector<Character>>();
        Vector<Character> student1 = new Vector<Character>();
        Vector<Character> student2 = new Vector<Character>();
        student1.add('b');
        student1.add('a');
        student1.add('b');
        student1.add('c');
        student1.add('d');
        student2.add('d');
        student2.add('a');
        student2.add('c');
        student2.add('c');
        student2.add('b');
        students.add(student1);
        students.add(student2);
        return students;
    }
}
