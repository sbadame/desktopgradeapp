package excel;

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
 * A simple working example of what it take to write an excel file based on
 * <a href="http://poi.apache.org/spreadsheet/how-to.html"> the apache how-to
 * </a>
 */
public class ExcelExample {

    public static void main(String[] args) throws IOException {

        Vector<Character> key = new Vector<Character>();
        Vector<Vector<Character>> students = new Vector<Vector<Character>>();
        Vector<Character> studAns = new Vector<Character>();

        //Use File.separator because differen OSs have different slashes
        // Linux/Mac - "/" Windows - "\"
        InputStream input = new FileInputStream("sampledata" + File.separator + "Math_103_Assessment_S10.xls");

        HSSFWorkbook wb     = new HSSFWorkbook(input);

        HSSFSheet MainSheet = wb.getSheetAt(0);

        HSSFRow KeyRow    = MainSheet.getRow(3);
        HSSFCell QNumCell   = KeyRow.getCell(4);

        int rowCount = MainSheet.getPhysicalNumberOfRows();

        int QNum = new Integer(QNumCell.getRichStringCellValue().getString());

        
        for(int i=7; i<=(7+QNum-1); i++ ){
            HSSFCell keyCell = KeyRow.getCell(i);
            key.add(keyCell.getRichStringCellValue().getString().charAt(0));
        }

        for(int j=4; j<rowCount-3; j++ ){
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

        System.out.println(key);

        System.out.println(students); //Sub 3 to compensate
     
     
        /*try {
            FileOutputStream out = new FileOutputStream("sampledata/workbook.xls");
            Workbook wb = new HSSFWorkbook();
            Sheet s = wb.createSheet(); 
            Row r = s.createRow(0);
            Cell c= r.createCell(0);
            c.setCellValue("Woot woot sandro!");
            wb.write(out);
            out.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }*/

    }
}
