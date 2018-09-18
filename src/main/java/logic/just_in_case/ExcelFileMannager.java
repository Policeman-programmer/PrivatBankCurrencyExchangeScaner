package logic.just_in_case;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import logic.entity.DailyExchangeRate;
import logic.entity.ExchangeRate;
import logic.just_in_case.ExcelFile;

import java.io.File;
import java.io.IOException;

public class ExcelFileMannager {
    private ExcelFile excel;
    private File file;

    public ExcelFileMannager(ExcelFile excel) {
        this.excel = excel;
        this.file = excel.getFile();
    }

    public void initColumns(DailyExchangeRate dailyExchangeRate) throws WriteException {
        for (ExchangeRate exchangeRate : dailyExchangeRate.getExchangeRate()) {
            String sheetName = exchangeRate.getCurrency();
            excel.setValueIntoCell(sheetName, 0, 0, "date");
            excel.setValueIntoCell(sheetName, 1, 0, "base currency");
            excel.setValueIntoCell(sheetName, 2, 0, "currency");
            excel.setValueIntoCell(sheetName, 3, 0, "sale rate NB");
            excel.setValueIntoCell(sheetName, 4, 0, "purchase rate NB");
            excel.setValueIntoCell(sheetName, 5, 0, "sale rate");
            excel.setValueIntoCell(sheetName, 6, 0, "purchase rate");
        }
        excel.closeFile();
    }

    public void insertRow(DailyExchangeRate dailyExchangeRate, String date, int rowNumber) throws WriteException, IOException, BiffException {
        excel.readExcel(file);
        for (ExchangeRate exchangeRate : dailyExchangeRate.getExchangeRate()) {
            String sheetName = exchangeRate.getCurrency();
            excel.setValueIntoCell(sheetName, 0, rowNumber, date);
            excel.setValueIntoCell(sheetName, 1, rowNumber, exchangeRate.getBaseCurrency());
            excel.setValueIntoCell(sheetName, 2, rowNumber, exchangeRate.getCurrency());
            excel.setValueIntoCell(sheetName, 3, rowNumber, String.valueOf(exchangeRate.getSaleRateNB()));
            excel.setValueIntoCell(sheetName, 4, rowNumber, String.valueOf(exchangeRate.getPurchaseRateNB()));
            excel.setValueIntoCell(sheetName, 5, rowNumber, String.valueOf(exchangeRate.getSaleRate()==0.0?"":exchangeRate.getSaleRate()));
            excel.setValueIntoCell(sheetName, 6, rowNumber, String.valueOf(exchangeRate.getPurchaseRate()==0.0?"":exchangeRate.getPurchaseRate()));
        }
        excel.closeFile();
    }
}
