package hw3.hash;

import java.util.LinkedList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int[] buckets = new int[M];
        for (Oomage o : oomages) {
            buckets[(o.hashCode() & 0x7FFFFFFF) % M] += 1;
        }
        for (int bucket : buckets) {
            if (bucket < oomages.size() / 50 || bucket > oomages.size() / 2.5) {
                return false;
            }
        }
        return true;
    }
}
