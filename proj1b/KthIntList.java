import java.util.Iterator;
import java.util.NoSuchElementException;

public class KthIntList implements Iterator<Integer> {
    public int k;
    private IntList curList;
    private boolean hasNext;

    public KthIntList(IntList i, int k) {
        this.k = k;
        this.curList = i;
        this.hasNext = true;
    }

    public static class IntList {
        public int head;
        public IntList tail;

        public IntList(int f, IntList r) {
            head = f;
            tail = r;
        }
    }

    @Override
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override
    public Integer next() {
        if (curList == null) {
            throw new NoSuchElementException();
        }
        Integer toReturn = curList.head;
        for (int i = 0; i < k && curList != null; i++) {
            curList = curList.tail;
        }
        hasNext = (curList != null);
        return toReturn;
    }

    public static void main(String[] args) {
        IntList L = new IntList(7, null);
        L = new IntList(6, L);
        L = new IntList(5, L);
        L = new IntList(4, L);
        L = new IntList(3, L);
        L = new IntList(2, L);
        L = new IntList(1, L);
        L = new IntList(0, L);
        for (Iterator<Integer> p = new KthIntList(L, 2); p.hasNext(); ) {
            System.out.println(p.next());
        }
    }
}
