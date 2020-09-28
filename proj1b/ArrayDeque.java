import java.util.Objects;

public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.items.length];
        nextFirst = 0;
        size = other.size();
        nextLast = size + 1;
        int start = other.nextFirst + 1;
        for (int i = 1; i <= size; i++) {
            items[i] = (T) other.get(start);
            start += 1;
        }
    }

    private T get(int index) {
        if (index < 0) {
            return null;
        }
        return items[index % items.length];
    }

    public boolean isFull() {
        return size == items.length;
    }

    private boolean isSparse() {
        return items.length > 16 && size < items.length / 4;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        int oldIndex = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            newArray[i] = items[oldIndex];
            oldIndex = plusOne(oldIndex);
        }
        items = newArray;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    public void addFirst(T item) {
        if (isFull()) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        if (isFull()) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    public T removeFirst() {
        if (isSparse()) {
            resize(items.length / 2);
        }
        nextFirst = plusOne(nextFirst);
        T removeItem = items[nextFirst];
        items[nextFirst] = null;
        if (!isEmpty()) {
            size -= 1;
        }
        return removeItem;
    }

    public T removeLast() {
        if (isSparse()) {
            resize(items.length / 2);
        }
        nextLast = minusOne(nextLast);
        T removeItem = items[nextLast];
        items[nextLast] = null;
        if (!isEmpty()) {
            size -= 1;
        }
        return removeItem;
    }

    public void printDeque() {
        for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> al = new ArrayDeque<>();
//        System.out.println(al.isEmpty());
//        for (int i = 9; i > 0; i--) {
//            al.addFirst(i);
//        }
        for (int i = 0; i < 4; i++) {
            al.addLast(i);
        }
        al.printDeque();
//        int five = al.get(5);
//        System.out.println(five);
//        System.out.println(al.get(6));
        ArrayDeque<Integer> al2 = new ArrayDeque<>(al);
        al2.printDeque();

    }
}
