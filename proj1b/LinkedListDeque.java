public class LinkedListDeque<T> implements Deque<T>{

    private class itemNode {
        private T item;
        private itemNode next;
        private itemNode prev;

        private itemNode(itemNode p, T i, itemNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    public itemNode sentinel;
    public int size;

    public LinkedListDeque() {
        sentinel = new itemNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new itemNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        itemNode first = other.sentinel.next;
        for (int i = 0; i < other.size; i++) {
            addLast(first.item);
            first = first.next;
        }
    }

    public void addFirst(T item) {
        sentinel.next = new itemNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        sentinel.prev = new itemNode(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public T removeFirst() {
        itemNode first = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        if (!isEmpty()) {
            size -= 1;
        }
        return first.item;
    }

    public T removeLast() {
        itemNode last = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        if (!isEmpty()) {
            size -= 1;
        }
        return last.item;
    }

    public T get(int index) {
        itemNode first = sentinel.next;
        for (int i = 0; i < index; i++) {
            first = first.next;
        }
        return first.item;
    }

    private T getRecursive(int index, itemNode curr) {
        if (index == 0) {
            return curr.item;
        }
        return getRecursive(index - 1, curr.next);
    }

    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        itemNode first = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(first.item + " ");
            first = first.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedListDeque<String> lls = new LinkedListDeque<>();
//        System.out.println(lls.isEmpty());
        lls.addFirst("this");
        lls.addFirst("after");
        lls.addLast("is");
        lls.addLast("testing.");
//        System.out.println(lls.size);
//        lls.printDeque();
//        String first = lls.removeFirst();
//        System.out.println(first);
//        lls.printDeque();
//        String last = lls.removeLast();
//        System.out.println(last);
//        lls.printDeque();
//        String item = lls.get(1);
//        System.out.println(item);
//        LinkedListDeque<String> lls2 = new LinkedListDeque<>(lls);
//        lls.printDeque();
//        lls2.printDeque();
        String item1 = lls.getRecursive(0);
        System.out.println(item1);
    }
}

