package gradeapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author sandro
 */
public class ExcelReader{
    Vector<Character> key = new Vector<Character>();
    Vector<Vector<Character>> students = new Vector<Vector<Character>>();
    Vector<Character> studAns = new Vector<Character>();

    public ExcelReader (File in) throws IOException{
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(in));
        HSSFSheet MainSheet = wb.getSheetAt(0);
        HSSFRow KeyRow    = MainSheet.getRow(3);
        HSSFCell QNumCell   = KeyRow.getCell(4);

        int QNum = new Integer(QNumCell.getRichStringCellValue().getString());
        int rowCount = MainSheet.getPhysicalNumberOfRows();

        for(int i=7; i <= 7+QNum-1; i++){
            HSSFCell keyCell = KeyRow.getCell(i);
            key.add(keyCell.getRichStringCellValue().getString().charAt(0));
        }

        for(int j=4; j < rowCount-3; j++){
                studAns.clear();
                HSSFRow studRow = MainSheet.getRow(j);
                for(int k=7; k<=(7+QNum-1); k++){
                    HSSFCell studCell = studRow.getCell(k);
                    if(studCell == null){
                        studAns.add(' ');
                    }
                    else{
                        studAns.add(studCell.getRichStringCellValue().getString().charAt(0));
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
