package gradeapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

    public ExcelReader (File in) throws IOException, GraphFormatException{
        Workbook wb;
        if (in.getName().endsWith("xlsx")){
            wb = new XSSFWorkbook(new FileInputStream(in));
        }else{
            wb = new HSSFWorkbook(new FileInputStream(in));
        }

        Sheet MainSheet = wb.getSheetAt(0);
        Row TitleRow = MainSheet.getRow(0);
        Cell TitleCell = TitleRow.getCell(0);

        String Title = TitleCell.getStringCellValue();

        if(Title.equals("Scanner Results")){
            Row KeyRow    = MainSheet.getRow(3);
            Cell QNumCell   = KeyRow.getCell(4);

            int QNum = new Integer(QNumCell.getRichStringCellValue().getString());
            int rowCount = MainSheet.getPhysicalNumberOfRows();

            //Grabs the Key from Excel Sheet
            for(int i=7; i <= 7+QNum-1; i++){
                Cell keyCell = KeyRow.getCell(i);
                char tempKey;
                try{
                    tempKey = Character.toUpperCase(keyCell.getRichStringCellValue().getString().charAt(0));
                }
                catch(IllegalStateException ex){
                    throw new GraphFormatException();
                }
                if(tempKey >='A' && tempKey <='Z'){
                    key.add(tempKey);
                }
                else{
                    throw new GraphFormatException();
                }
            }

        //Grabs Student Answers from Excel Sheet
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
                            try{
                                tempStudAns = Character.toUpperCase(studCell.getRichStringCellValue().getString().charAt(0));
                            }
                            catch(IllegalStateException ex){
                                throw new GraphFormatException();
                            }
                            if(tempStudAns >='A' && tempStudAns <='Z'){
                                studAns.add(tempStudAns);
                            }
                            else{
                                throw new GraphFormatException();
                            }
                        }
                    }
                    students.add(studAns);
             }
        }
        else{
            throw new GraphFormatException();
        }
    }

    public Vector <Character> getAnswerKey(){
        return key;
    }

    public Vector <Vector <Character>> getGrades(){
        return students;
    }
}
