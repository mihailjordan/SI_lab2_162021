import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    private SILab2 siLab2 = new SILab2();

    private List<Time> createList(Time... time){
        return new ArrayList<>(Arrays.asList(time));
    }

    @Test
    void testMultipleCondition(){
//        (hr < 0 || hr > 24)
//        T || X
        RuntimeException ex;
        ex = assertThrows(RuntimeException.class, () -> siLab2.function(createList(new Time(-1, 1, 1))));
        assertTrue(ex.getMessage().contains("The hours are smaller than the minimum"));
//        F || T
        ex = assertThrows(RuntimeException.class, () -> siLab2.function(createList(new Time(25, 1, 1))));
        assertTrue(ex.getMessage().contains("The hours are grater than the maximum"));
//        F || F
        assertEquals(List.of(53), siLab2.function(createList(new Time(0, 0, 53))));
//        (min < 0 || min > 59)
//        T || X
        ex = assertThrows(RuntimeException.class, () -> siLab2.function(createList(new Time(13, -1, 1))));
        assertTrue(ex.getMessage().contains("The minutes are not valid!"));
//        F || T
        ex = assertThrows(RuntimeException.class, () -> siLab2.function(createList(new Time(13, 60, 1))));
        assertTrue(ex.getMessage().contains("The minutes are not valid!"));
//        F || F
        assertEquals(List.of(70), siLab2.function(createList(new Time(0, 1, 10))));
//        (sec >= 0 && sec <= 59)
//        F && X
        ex = assertThrows(RuntimeException.class, () -> siLab2.function(createList(new Time(1, 1, -1))));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));
//        T && F
        ex = assertThrows(RuntimeException.class, () -> siLab2.function(createList(new Time(1, 1, 60))));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));
//        T && T
        assertEquals(List.of(70), siLab2.function(createList(new Time(0, 1, 10))));
//        (hr == 24 && min == 0 && sec == 0)
//        F && X && X NOT GONNA HAPPEN
//        T && F && X
        ex = assertThrows(RuntimeException.class, () -> siLab2.function(createList(new Time(24, 1, 1))));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));
//        T && T && T
        assertEquals(List.of(86400), siLab2.function(createList(new Time(24, 0, 0))));
    }

    @Test
    void testEveryBranch(){
        RuntimeException ex;
        ex = assertThrows(RuntimeException.class, () -> siLab2.function(createList(new Time(-1, 1, 1))));
        assertTrue(ex.getMessage().contains("The hours are smaller than the minimum"));

        ex = assertThrows(RuntimeException.class, () -> siLab2.function(createList(new Time(25, 1, 1))));
        assertTrue(ex.getMessage().contains("The hours are grater than the maximum"));

        ex = assertThrows(RuntimeException.class, () -> siLab2.function(createList(new Time(13, -1, 1))));
        assertTrue(ex.getMessage().contains("The minutes are not valid!"));

        ex = assertThrows(RuntimeException.class, () -> siLab2.function(createList(new Time(1, 1, -1))));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));

        ex = assertThrows(RuntimeException.class, () -> siLab2.function(createList(new Time(24, 1, 1))));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));

        assertEquals(List.of(86400, 3661), siLab2.function(createList(new Time(24, 0, 0), new Time(1,1,1))));
    }

}