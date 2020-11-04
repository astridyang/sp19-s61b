import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IteratorOfIterators implements Iterator<Integer> {
    LinkedList<Integer> list;

    public IteratorOfIterators(List<Iterator> a) {
        list = new LinkedList<>();
        int i = 0;
        while (!a.isEmpty()) {
            Iterator<Integer> curr = a.get(i);
            if (curr.hasNext()) {
                list.add(curr.next());
            } else {
                a.remove(curr);
                i -= 1;
            }
            if (a.isEmpty()) {
                break;
            }
            i = (i + 1) % a.size();
        }
    }

    @Override
    public boolean hasNext() {
        return !list.isEmpty();
    }

    @Override
    public Integer next() {
        return list.removeFirst();
    }

    public static void main(String[] args) {
        List<Integer> a = new LinkedList<>();
        a.add(1);
        a.add(2);
        List<Integer> b = new LinkedList<>();
        b.add(3);
        b.add(4);
        b.add(5);
        Iterator<Integer> ai = a.listIterator();
        Iterator<Integer> bi = b.listIterator();
        List<Iterator> c = new LinkedList<>();
        c.add(ai);
        c.add(bi);
        IteratorOfIterators ioi = new IteratorOfIterators(c);
        for (IteratorOfIterators iter = ioi; iter.hasNext(); ) {
            Integer it = iter.next();
            System.out.println(it);
        }
    }
}
