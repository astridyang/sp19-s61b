import java.util.*;

public class FilteredList<T> extends ArrayList<T> implements Iterable<T> {
    List<T> list;
    Predicate<T> pred;

    public FilteredList(List<T> L, Predicate<T> filter) {
        this.list = L;
        this.pred = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new FilteredListIterator(list, pred);
    }

    private class FilteredListIterator implements Iterator<T> {
        List<T> list;
        Predicate<T> pred;
        int index;

        public FilteredListIterator(List<T> l, Predicate<T> f) {
            list = l;
            pred = f;
            index = 0;
        }

        @Override
        public boolean hasNext() {
            while (index < list.size() && !pred.test(list.get(index))) {
                index += 1;
            }
            return index < list.size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            index += 1;
            return list.get(index - 1);
        }
    }

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        l.add(2);
        l.add(0);
        l.add(5);
        l.add(4);
        Predicate<Integer> f = x -> x > 3;
        FilteredList<Integer> actual = new FilteredList<>(l, f);
        for (int item : actual) {
            System.out.println(item);
        }
    }
}
