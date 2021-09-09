import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int open_count = 0;
    private int size;
    WeightedQuickUnionUF unions;


    public Percolation(int n) {
        this.size = n;
        unions = new WeightedQuickUnionUF(n * n + 2);
        for (int i = 1; i < size; i++) {
            unions.union(0, i);
            unions.union(n * n + 1, n * n + 1 - i);
        }
        grid = new boolean[n][n];
    }

    public boolean percolates() {
        if (unions.connected(0, 101)) {
            return true;
        }
        return false;
    }

    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }
        StdOut.print(row);
        StdOut.print(" " + col + "\n");
        open_count++;
        grid[row][col] = true;
        connect(row, col);
    }

    public String numberOfOpenSites() {

        return String.valueOf(open_count);
    }

    public boolean isOpen(int row, int col) {
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        return false;
    }

    private void boundaries(int row, int col) {
        if (row >= size || row < 0 || col >= size || col < 0) {
            throw new java.lang.IndexOutOfBoundsException("Index out of bounds");
        }
    }

    private void connect(int row, int col) {
        int tile = size * row + col + 1;
        StdOut.print(tile);
        if (row != 0 && isOpen(row - 1, col)) {
            unions.union(((size * (row - 1) + col) + 1), tile);
            StdOut.print("down\n");
        }
        if (row != (size - 1) && isOpen(row + 1, col)) {
            unions.union(tile, ((size * (row + 1) + col) + 1));
            StdOut.print("up" + (size * row + 1 + col) + "\n");
        }
        if (col != 0 && isOpen(row, col - 1)) {
            unions.union((size * row + col), tile);
            StdOut.print("left\n");
        }
        if (col != (size - 1) && isOpen(row, col + 1)) {
            unions.union(tile, ((size * row + col + 2)));
            StdOut.print("right: " + (size * row + col + 1) + "\n");
        }
    }


    public static void main(String[] args) {

    }
}
;
