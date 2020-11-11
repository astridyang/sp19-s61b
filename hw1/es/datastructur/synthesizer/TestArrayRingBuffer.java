package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());
        arb.enqueue(1);
        arb.enqueue(2);
        int first = (int) arb.dequeue();
        assertTrue(first == 1);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.enqueue(9);
        arb.enqueue(10);
        arb.enqueue(11);
        assertTrue(arb.isFull());
        first = (int) arb.peek();
        assertTrue(first == 2);

    }
    @Test
    public void testEqual(){
        ArrayRingBuffer arb1 = new ArrayRingBuffer(2);
        arb1.enqueue(1);
        arb1.enqueue(2);
        ArrayRingBuffer arb2 = new ArrayRingBuffer(2);
        arb2.enqueue(1);
        arb2.enqueue(2);
        ArrayRingBuffer arb3 = new ArrayRingBuffer(3);
        assertTrue(arb1.equals(arb2));
        assertFalse(arb1.equals(arb3));
    }
}
