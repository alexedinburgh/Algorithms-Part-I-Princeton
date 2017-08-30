
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
    
public class Percolation{
    
    private int size;
    private WeightedQuickUnionUF wq;
    private boolean[] openGrid;
    
    public Percolation(int n){
        if (n <= 0) throw new IllegalArgumentException();   
        this.size = n;
        wq = new WeightedQuickUnionUF(n*n + 2);
        openGrid = new boolean[n*n + 2];
        for(int i = 0;i < (n*n + 2);i++){
            openGrid[i] = false;
        }
        
    }
    
    public int xyTo(int row,int col){
        if(row < 1||row > this.size||col < 1||col>this.size) throw new IndexOutOfBoundsException();
        return (row-1)*this.size + col;
    }
        
    
    public void open(int row,int col){
        if(row < 1||row > this.size||col < 1||col>this.size) throw new IndexOutOfBoundsException();
        int index = xyTo(row,col);
        openGrid[index] = true;
        if(row == 1){
            wq.union(index,0);
        }
        if(row == this.size){
            wq.union(index,this.size*this.size+2);
        }
        if(row > 1 && isOpen(row - 1,col)){
            wq.union(index,index - this.size);
        }
        if(col > 1 && isOpen(row,col - 1)){
            wq.union(index,index - 1);
        }
        if(row < this.size && isOpen(row + 1,col)){
            wq.union(index,index + this.size);
        }
        if(col < this.size && isOpen(row,col + 1)){
            wq.union(index,index + 1);
        }
    }
    
    public boolean isOpen(int row,int col){
        if(row < 1||row > this.size||col < 1||col>this.size) throw new IndexOutOfBoundsException();
        return openGrid[xyTo(row,col)];
    }
    
    public boolean isFull(int row,int col){
        if(row < 1||row > this.size||col < 1||col>this.size) throw new IndexOutOfBoundsException();
        return wq.connected(xyTo(row,col),0);
        
    }
    
    public int numberOfOpenSites(){
        int count = 0;
        for(int i = 0;i < this.size*this.size + 2;i++){
            if (openGrid[i] == true){
                count++;
            }
        }
        return count;
            
    }
    
    
    public boolean percolates(){
        return wq.connected(0,this.size*this.size+1);
    }
}