import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private double[] times;

    public PercolationStats(int N, int T) // perform T independent experiments on an N-by-N grid
    {
        if (N == 0 || T == 0) {
            throw new java.lang.IllegalArgumentException("Index out of bounds");
        }
        times = new double[T];
        Stopwatch total = new Stopwatch();

        for (int i = 0; i < T; i++) {
            //StdOut.print(i);
            Stopwatch timer = new Stopwatch();
            Percolation percolation = new Percolation(N);
            do {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                percolation.open(row, col);
            } while (!percolation.percolates());
            double time = timer.elapsedTime();
            times[i] = time;
        }
        double totaltime = total.elapsedTime();
        StdOut.print("Total time: " + totaltime + "\n");
        StdOut.print("mean: " + mean() + "\n");
        StdOut.print("Stddev: " + stddev() + "\n");
        StdOut.print("conf low: " + confidenceLow() + "\n");
        StdOut.print("conf high: " + confidenceHigh() + "\n");
    }

    public double mean() // sample mean of percolation threshold
    {
        return StdStats.mean(times);
    }

    public double stddev() // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(times);
    }

    public double confidenceLow() // low endpoint of 95% confidence interval
    {

        return mean() - (1.96 * stddev() / Math.sqrt(times.length));
    }

    public double confidenceHigh() // high endpoint of 95 confidence interval
    {
        return mean() + (1.96 * stddev() / Math.sqrt(times.length));
    }

    public static void main(String[] args) {
        new PercolationStats(64, 256);
    }
}
