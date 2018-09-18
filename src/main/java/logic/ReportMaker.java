package logic;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import logic.entity.DailyExchangeRate;
import logic.entity.ExchangeRate;

import java.io.File;
import java.io.IOException;
import java.util.Map;

class ReportMaker {
    private WritableSheet mWritableSheet;

    /**
     *@author Yevgehii Thokhniuk
     * @param file File where will be put Excel document
     * @param dERForPeriod Map of Daily exchange rate for the particular period.
     * @throws IOException throws IOException
     * @throws WriteException throw WriteException
     */
    void createExcelReport(File file, Map<String, DailyExchangeRate> dERForPeriod) throws IOException, WriteException {
        WritableWorkbook mWritableWorkbook = Workbook.createWorkbook(file);
        int rowNumber = 1;
        int sheetPosition = 0;
        boolean initSheetColumn = false;
        for (Map.Entry<String, DailyExchangeRate> exchangeRateEntry : dERForPeriod.entrySet()) {

            for (ExchangeRate exchangeRate : exchangeRateEntry.getValue().getExchangeRate()) {
                String sheetName = exchangeRate.getCurrency();
                //if sheet not exist - create new, otherwise use created
                if ((mWritableSheet = mWritableWorkbook.getSheet(sheetName)) == null) {
                    mWritableSheet = mWritableWorkbook.createSheet(sheetName, sheetPosition++);
                    initSheetColumn = false;
                }
                String date = exchangeRateEntry.getKey();
                // first-row use for initializing column
                if (!initSheetColumn) {
                    setValueIntoCell( 0, 0, "date");
                    setValueIntoCell( 1, 0, "base currency");
                    setValueIntoCell( 2, 0, "currency");
                    setValueIntoCell( 3, 0, "sale rate NB");
                    setValueIntoCell( 4, 0, "purchase rate NB");
                    setValueIntoCell( 5, 0, "sale rate");
                    setValueIntoCell( 6, 0, "purchase rate");
                    initSheetColumn=true;
                }
                setValueIntoCell( 0, rowNumber, date);
                setValueIntoCell( 1, rowNumber, exchangeRate.getBaseCurrency());
                setValueIntoCell( 2, rowNumber, exchangeRate.getCurrency());
                setValueIntoCell( 3, rowNumber, String.valueOf(exchangeRate.getSaleRateNB()));
                setValueIntoCell( 4, rowNumber, String.valueOf(exchangeRate.getPurchaseRateNB()));
                setValueIntoCell( 5, rowNumber, String.valueOf(exchangeRate.getSaleRate() == 0.0 ? "" : exchangeRate.getSaleRate()));
                setValueIntoCell( 6, rowNumber, String.valueOf(exchangeRate.getPurchaseRate() == 0.0 ? "" : exchangeRate.getPurchaseRate()));
            }
            rowNumber++;
        }
            mWritableWorkbook.write();
            mWritableWorkbook.close();
    }

    private void setValueIntoCell( int column, int row, String data) {
        Label label = new Label(column, row, data);
        try {
            mWritableSheet.addCell(label);
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}
