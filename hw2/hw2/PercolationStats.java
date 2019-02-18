package hw2;

import edu.princeton.cs.algs4.StdRandom;
public class PercolationStats {
    private int[] numberOpened; // number of the opened block in T times of experiments
    private double stdDev;
    private int times;

    /** Perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if(T <= 0 || N <= 0) {
            throw new IllegalArgumentException("T & N Should be positive integers");
        }
        Percolation p[] = new Percolation[T];
        for(int i = 0; i < T; i++) {
            p[i] = new Percolation(N);
        }
        times = T;
        numberOpened = new int[T];

        for(int i = 0; i < T; i++) {
            int j = 0;
            while(!p[i].percolates()){
                int randomRow = StdRandom.uniform(N);
                int randomCol = StdRandom.uniform(N);
                while(p[i].isOpen(randomRow, randomCol)) {
                    randomRow = StdRandom.uniform(N);
                    randomCol = StdRandom.uniform(N);
                }
                p[i].open(randomRow, randomCol);
                j++;
            }
            numberOpened[i] = j;
        }

    }

    /** sample mean of percolation threshold */
    public double mean(){
        int sum = 0;
        for(int number: numberOpened) {
            sum += number;
        }
        return (double)sum/times;

    }
    /** sample standard deviation of percolation threshold */
    public double stddev(){
        int sum = 0;
        double mean = this.mean();
        for(int number: numberOpened) {
            sum += (number - mean) * (number - mean);
        }
        return (double)sum/(times - 1);
    }

    /** low endpoint of 95% confidence interval */
    public double confidenceLow(){
        return this.mean() - 1.96 * this.stddev()/Math.sqrt(times);
    }

    /** high endpoint of 95% confidence interval */
    public double confidenceHigh(){
        return this.mean() + 1.96 * this.stddev()/Math.sqrt(times);
    }


    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats test = new PercolationStats(20, 1000, pf);

        System.out.println(test.mean());
        System.out.println(test.stddev());
    }
}
