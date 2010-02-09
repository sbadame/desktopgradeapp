package example;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


/** 
 * A simple working example of what it take to write an excel file based on
 * <a href="http://poi.apache.org/spreadsheet/how-to.html"> the apache how-to
 * </a>
 */
public class ExcelExample {

    public static void main(String[] args) {
        try {
            FileOutputStream out = new FileOutputStream("workbook.xls");
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
        }

    }
}
