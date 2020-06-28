import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private final double mean;
	
	private final double stddev;
	
	private final double confidenceLO;
	
	private final double confidenceHi;
	
	private final double[] est;
	
	
	//Throw an IllegalArgumentException in the constructor if either n ≤ 0 or trials ≤ 0.
	// perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
    	
    	if(n <=0 || trials <= 0) {
    		throw new IllegalArgumentException();
    	}
    	
    	est = new double[trials];
    	
    	for(int k=0; k<trials; k++) {
    		
    		Percolation perc = new Percolation(n);
    		double count = 0;
    		
    		while(!perc.percolates()) {
    			
    			int i = StdRandom.uniform(1, n+1);
    			int j = StdRandom.uniform(1, n+1);
    			
    			if(perc.isOpen(i, j)) {
    				continue;
    			}
    			
    			perc.open(i, j);
    			count++;
    		}
    		
    		est[k] = count / (n*n);
    	}
    	
    	mean = StdStats.mean(est);
    	stddev = StdStats.stddev(est);
    	
    	confidenceLO = mean - (1.96 * stddev) / Math.sqrt(trials);
        confidenceHi = mean + (1.96 * stddev) / Math.sqrt(trials);
    }

    // sample mean of percolation threshold
    public double mean() {
    	return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
    	return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
    	return confidenceLO;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
    	return confidenceHi;
    }

   // test client (see below)
   public static void main(String[] args) {
	   
       int n = Integer.parseInt(args[0]);
       int t = Integer.parseInt(args[1]);
       PercolationStats stats = new PercolationStats(n, t);
       StdOut.println("mean                    = " + stats.mean());
       StdOut.println("stddev                  = " + stats.stddev());
       StdOut.println("95% confidence interval = " + stats.confidenceLo()
               + ", " + stats.confidenceHi());
   }
}
