import edu.princeton.cs.algs4.StdRandom; 
import edu.princeton.cs.algs4.StdStats; 

public class PercolationStats {
    
    private double[] sum;
    private int openGrids = 0;
    private int size;
    
    public PercolationStats(int n, int trials){
        if (n <= 0||trials<=0) throw new IllegalArgumentException();
        this.size = n;
        sum = new double[trials];
        for(int i = 0;i < trials;i++){
            sum[i] = result();
        }
    }
    
    public double result(){
        Percolation perco = new Percolation(this.size);
        while(!perco.percolates()){
            int row = StdRandom.uniform(this.size) + 1;
            int col = StdRandom.uniform(this.size) + 1;
            if(!perco.isOpen(row,col)){
                perco.open(row,col);
                this.openGrids++;
            }
        }
        return (double) openGrids/(this.size*this.size);
    }
    
    public double mean(){
        return StdStats.mean(sum);
    }
    
    public double stddev(){
        return StdStats.stddev(sum);
    }
    
    public double confidenceLo(){
        return mean() - (1.96 * stddev() / Math.sqrt(sum.length));
    }
    
    public double confidenceHi(){
        return mean() + (1.96 * stddev() / Math.sqrt(sum.length));
    }
}
            
    
    