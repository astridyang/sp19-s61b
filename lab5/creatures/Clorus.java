package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {
    private double energy;

    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    public Color color() {
        return color(34, 0, 231);
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public void move() {
        energy -= 0.03;
    }

    public void stay() {
        energy += 0.01;
    }

    public Clorus replicate() {
        double new_energy = energy / 2;
        energy = new_energy;
        return new Clorus(new_energy);
    }

    public double energy() {
        return energy;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        for (Direction d : neighbors.keySet()) {
            if (neighbors.get(d).name().equals("empty")) {
                emptyNeighbors.addFirst(d);
            } else if (neighbors.get(d).name().equals("plip")) {
                plipNeighbors.addFirst(d);
            }
        }
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }
        // Rule 2
        if (plipNeighbors.size() != 0) {
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors));
        }
        // Rule 3
        if (energy >= 1.0) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        } else {
            // Rule 4
            return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
        }

    }
}
