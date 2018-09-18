package logic;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class PeriodCounterTest {

    @Test
    public void makePeriodList() {
        Collection<String> collectionDays= PeriodCounter.makePeriodList("01.01.2016","31.12.2017");
        for (String day :
                collectionDays) {
            System.out.println(day);
        }
    }


}