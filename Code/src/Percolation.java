import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] coloms;
    private int open_count = 0;
    private int size;
    WeightedQuickUnionUF unions;


    public Percolation(int n) {
        this.size = n;
        unions = new WeightedQuickUnionUF(n * n + 2);
        coloms = new boolean[n][n];
    }

    public boolean percolates() {
        if (unions.connected(1, 91)) {
            StdOut.print("yes");
            return true;
        }
        return false;
    }

    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }
        ;
        try {
            boundaries(row - 1, col - 1);

        } catch (java.lang.IndexOutOfBoundsException e) {
            StdOut.printf("out");
            return;
        }
        StdOut.print(coloms[row - 1][col - 1]);
        open_count++;
        coloms[row - 1][col - 1] = true;
    }

    public String numberOfOpenSites() {

        return String.valueOf(open_count);
    }

    public boolean isOpen(int row, int col) {
        try {
            boundaries(row - 1, col - 1);
        } catch (java.lang.IndexOutOfBoundsException e) {
            return false;
        }
        return coloms[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        return false;
    }

    private void boundaries(int row, int col) {
        if (row >= size || row < 0 || col >= size || col < 0) {
            throw new java.lang.IndexOutOfBoundsException("Index out of bounds");
        }
    }

    public static void main(String[] args) {

    }
}
