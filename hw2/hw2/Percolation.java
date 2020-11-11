package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private int open_num = 0;
    private int N;

    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf_without_bottom; // to avoid backwash
    private int uf_bottom;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        grid = new int[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
        uf_without_bottom = new WeightedQuickUnionUF(N * N + 1);
        uf_bottom = N * N + 1;
    }

    private void validate(int row, int col) {
        if (row < 0 || row > N || col < 0 || col > N) {
            throw new IllegalArgumentException();
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        grid[row][col] = 1;
        unionAround(row, col);
        open_num += 1;
    }

    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private void unionAround(int row, int col) {
        for (int[] dir : directions) {
            int x = row + dir[0];
            int y = col + dir[1];
            if (x >= 0 && x < N && y >= 0 && y < N && isOpen(x, y)) {
                uf.union(ufIndex(row, col), ufIndex(x, y));
                uf_without_bottom.union(ufIndex(row, col), ufIndex(x, y));
            }
        }
        if (row == 0) {
            uf.union(0, ufIndex(row, col));
            uf_without_bottom.union(0, ufIndex(row, col));
        }
        if (row == N - 1) {
            uf.union(ufIndex(row, col), uf_bottom);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf_without_bottom.connected(0, ufIndex(row, col));
    }

    private int ufIndex(int row, int col) {
        return row * N + col + 1;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return open_num;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(0, uf_bottom);
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(0, 1);
        p.open(1, 1);
        p.open(1, 2);
//        p.open(2, 2);
        p.open(2, 0);
        System.out.println(p.isFull(2, 0));
        System.out.println(p.percolates());
    }
}
