//package assign3;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // perform independent trials on an n-by-n grid
//    Initialize all sites to be blocked.
//    Repeat the following until the system percolates:
//    Choose a site uniformly at random among all blocked sites.
//    Open the site.
//    The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.

    private static final double CONFIDENCE = 1.96;

    private int n;
    private int trials;
    private double[] percolationThreshold;

    public PercolationStats(int n, int trials) {
        this.n = n;
        this.trials = trials;
        percolationThreshold = new double[trials];
        performTrials();
    }


    private void performTrials() {
        int cnt = 0;
        while (cnt < trials) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                percolation.open(randomSite(), randomSite());
            }
            percolationThreshold[cnt] = (double) percolation.numberOfOpenSites() / n * n;
            cnt++;
        }
    }

    private int randomSite() {
        return StdRandom.uniformInt(0, n);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percolationThreshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percolationThreshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE * stddev()) / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE * stddev()) / Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
    }

}

