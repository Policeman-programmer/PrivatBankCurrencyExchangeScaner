package logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

class PeriodCounter {
    static Collection<String> makePeriodList(String from, String to) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate start = LocalDate.parse(from,dtf);
        LocalDate end = LocalDate.parse(to,dtf);
        Collection<String> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start.format(dtf));
            start = start.plusDays(1);
        }
        return totalDates;
    }
}
