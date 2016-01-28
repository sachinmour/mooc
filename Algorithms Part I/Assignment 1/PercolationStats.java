import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] thresholds;
    private int experiments;

    public PercolationStats(int N, int T) {
        if (N<=0 && T<=0) {
            throw new IllegalArgumentException(Integer.toString(N));
        }
        experiments = T;
        thresholds = new double[T];
        for (int i = 0; i < experiments; i++) {
            Percolation perc = new Percolation(N);
            int n = 0;
            while (!perc.percolates()) {
                int p = StdRandom.uniform(1, N+1);
                int q = StdRandom.uniform(1, N+1);
                if (!perc.isOpen(p, q)) {
                    perc.open(p, q);
                    n++;
                }
            }
            thresholds[i] = (double)n/(N*N);
        }
    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    public double confidenceLo() {
        return mean()-(1.96*stddev()/Math.sqrt((double) experiments));
    }

    public double confidenceHi() {
        return mean()+(1.96*stddev()/Math.sqrt((double) experiments));
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        if (N<=0 && T<=0) {
            throw new IllegalArgumentException(Integer.toString(N));
        }
        PercolationStats stats = new PercolationStats(N, T);
        System.out.println("mean                    = "+ Double.toString(stats.mean()));
        System.out.println("stddev                  = " + Double.toString(stats.stddev()));
        System.out.println("95% confidence interval = " + Double.toString(stats.confidenceLo()) + ", " + Double.toString(stats.confidenceHi()));
    }

}
