import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation {
    private final boolean[][] grid;
    private int open_count = 0;
    private final int size;
    //WeightedQuickUnionUF unions;
    QuickFindUF unions;

    public Percolation(int n) {
        this.size = n;
        unions = new QuickFindUF(n * n + 2);
        for (int i = 1; i <= size; i++) {
            unions.union(0, i);
            unions.union(n * n + 1, n * n + 1 - i);
        }
        grid = new boolean[n][n];
    }

    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }
        boundaries(row, col);
        open_count++;
        grid[row][col] = true;
        connect(row, col);
    }

    public boolean isOpen(int row, int col) {
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        int tile = size * row + col + 1;
        return isOpen(row, col) && unions.connected(0, tile);
    }

    public String numberOfOpenSites() {
        return String.valueOf(open_count);
    }

    public boolean percolates() {
        return unions.connected(0, size * size);
    }

    public static void main(String[] args) {
        int n = 10;
        if (args.length == 1) n = Integer.parseInt(args[0]);
        Percolation percolation = new Percolation(n);
        do {
            int row = StdRandom.uniform(0, n);
            int col = StdRandom.uniform(0, n);
            percolation.open(row, col);
        } while (!percolation.percolates());
    }


    private void boundaries(int row, int col) {
        if (row >= size || row < 0 || col >= size || col < 0) {
            throw new java.lang.IndexOutOfBoundsException("Index out of bounds");
        }
    }

    private void connect(int row, int col) {
        int tile = size * row + col + 1;
        if (row != 0 && isOpen(row - 1, col)) {
            unions.union(((size * (row - 1) + col) + 1), tile);
        }
        if (row != (size - 1) && isOpen(row + 1, col)) {
            unions.union(tile, ((size * (row + 1) + col) + 1));
        }
        if (col != 0 && isOpen(row, col - 1)) {
            unions.union((size * row + col), tile);
        }
        if (col != (size - 1) && isOpen(row, col + 1)) {
            unions.union(tile, ((size * row + col + 2)));
        }
    }
}

