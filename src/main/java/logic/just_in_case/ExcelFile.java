package logic.just_in_case;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;

public class ExcelFile {
    Workbook workbook;
    private WritableWorkbook writableWorkbook;
    private WritableSheet writableSheet;
    private int sheetPosition = 0;
    private File file;

    public File getFile() {
        return file;
    }

    public ExcelFile(File file) throws IOException {
        this.file = file;
        writableWorkbook = Workbook.createWorkbook(file);

    }

    public void readExcel(File file) throws IOException, BiffException {
        workbook = Workbook.getWorkbook(file);
        writableWorkbook = Workbook.createWorkbook(new File(file.getPath()+"/PB currency exchangeCopy.xls"), workbook);
    }

    public void setValueIntoCell(String sheetName, int column, int row, String data) throws WriteException {
        if (writableWorkbook.getSheet(sheetName) == null)
            writableSheet = writableWorkbook.createSheet(sheetName, sheetPosition++);
        else writableSheet = writableWorkbook.getSheet(sheetName);
        Label label = new Label(column, row, data);
        writableSheet.addCell(label);
    }

    public void closeFile() {

        try {
            try {
                writableWorkbook.write();
                writableWorkbook.close();
                workbook.close();
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
    }


}
