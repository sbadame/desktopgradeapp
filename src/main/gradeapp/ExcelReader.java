package gradeapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author sandro
 */
public class ExcelReader{
    Vector<Character> key = new Vector<Character>();
    Vector<Vector<Character>> students = new Vector<Vector<Character>>();

    public ExcelReader (File in) throws IOException{
        Workbook wb = new XSSFWorkbook(new FileInputStream(in));
        Sheet MainSheet = wb.getSheetAt(0);
        Row KeyRow    = MainSheet.getRow(3);
        Cell QNumCell   = KeyRow.getCell(4);

        int QNum = new Integer(QNumCell.getRichStringCellValue().getString());
        int rowCount = MainSheet.getPhysicalNumberOfRows();

        for(int i=7; i <= 7+QNum-1; i++){
            Cell keyCell = KeyRow.getCell(i);
            char tempKey;
            tempKey = Character.toUpperCase(keyCell.getRichStringCellValue().getString().charAt(0));
            key.add(tempKey);
        }

        for(int j=4; j < rowCount-3; j++){
                Vector<Character> studAns = new Vector<Character>();
                char tempStudAns;
                Row studRow = MainSheet.getRow(j);
                for(int k=7; k<=(7+QNum-1); k++){
                    Cell studCell = studRow.getCell(k);
                    if(studCell == null){
                        studAns.add(' ');
                    }
                    else{
                        tempStudAns = Character.toUpperCase(studCell.getRichStringCellValue().getString().charAt(0));
                        studAns.add(tempStudAns);
                    }
                }
                students.add(studAns);
            }
    }

    public Vector <Character> getAnswerKey(){
        return key;
    }

    public Vector <Vector <Character>> getGrades(){
        return students;
    }
}
