import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TestFilteredList {

    @Test
    public void test() {
        List<Integer> l = new ArrayList<>();
        l.add(2);
        l.add(0);
        l.add(5);
        l.add(4);
        Predicate<Integer> f = x -> x > 3;
        FilteredList<Integer> actual = new FilteredList<>(l, f);
        List<Integer> l2 = new ArrayList<>();
        l2.add(5);
        l2.add(0);
        l2.add(4);
        l2.add(2);

        FilteredList<Integer> expected = new FilteredList<>(l2, f);
        Collections.sort(actual);
        Collections.sort(expected);
        assertTrue(l.containsAll(l2) && l2.containsAll(l));
    }
}
