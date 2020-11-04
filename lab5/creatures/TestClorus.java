package creatures;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.awt.Color;

import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {
    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.95, c.energy(), 0.01);
        c.stay();
        assertEquals(1.96, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        // TODO
        Clorus c1 = new Clorus(2);
        Clorus c2 = c1.replicate();
        assertNotSame(c1, c2);
        assertEquals(1, c1.energy(), 0.01);
        assertEquals(1, c2.energy(), 0.01);
    }

    @Test
    public void testChoose() {
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // attack
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> hasPlip = new HashMap<Direction, Occupant>();
        hasPlip.put(Direction.TOP, new Empty());
        hasPlip.put(Direction.BOTTOM, new Plip());
        hasPlip.put(Direction.LEFT, new Impassible());
        hasPlip.put(Direction.RIGHT, new Impassible());
        actual = c.chooseAction(hasPlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);
        assertEquals(actual, expected);

        // energy>=1.0 replicate
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);

        // energy<1.0;move
        c = new Clorus(0.99);
        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);
        assertEquals(expected, actual);

    }
}
