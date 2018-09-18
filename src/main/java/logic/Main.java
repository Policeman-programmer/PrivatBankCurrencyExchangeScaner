package logic;

import jxl.write.WriteException;

import java.io.IOException;

/**
 * @author Serhiy.K.Dubovenko
 */

public class Main {

    public static final String URL_EXCHANGE_RATES = "https://api.privatbank.ua/p24api/exchange_rates?json";
    private static Sender tlsSender = new Sender("19strongman93@gmail.com", "016559cadet");

    public static void main(String[] args) throws WriteException, IOException {
        tlsSender.send("This is Subject", "TLS: This is text!", "19strongman93@gmail.com", "1993cadet@gmail.com");
//        PBAPI pb = new PBAPI();
//
//        Collection<String> daysList = PeriodCounter.makePeriodList("01.01.2016","06.01.2016");
//        Map<String,DailyExchangeRate>dailyExchangeRateMap = new LinkedHashMap<>();
//        for (String exchangeRateDay:daysList) {
//            Map<String, String> queryParms = new HashMap<>();
//            queryParms.put("date", String.valueOf(exchangeRateDay));
//            dailyExchangeRateMap.put(exchangeRateDay,pb.getDailyExchangeRate(new Gson(), URL_EXCHANGE_RATES, queryParms));
//        }
//
//        File PBCurrencyExchange = new File("spreadsheets/PB currency exchange.xls");
//        new ReportMaker().createExcelReport(PBCurrencyExchange,dailyExchangeRateMap);

    }
}
