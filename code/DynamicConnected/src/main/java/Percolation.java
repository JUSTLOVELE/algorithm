import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * 给定一个由随机分布的绝缘和金属材料组成的复合系统:有多少材料需要是金属的，
 * 这样复合系统才是电导体？给定一个表面有水(或下面有油)的多孔景观，
 * 在什么条件下水能够排到底部(或油喷到表面)？
 * 科学家已经定义了一个被称为渗流的抽象过程来模拟这种情况。
 * 
 * 模型。
 * 		我们用一个n乘n的网格来模拟一个渗流系统。每个网格不是开放就是被封锁(blocked)。
 * 一个完整站点是一个开放站点，可以通过一系列相邻(左、右、上、下)开放站点连接到顶部行的开放站点。
 * 我们说，如果在最下面一行有一个完整的站点，系统就会渗透。
 * 换句话说，如果我们填充了所有连接到顶行的开放站点，并且这个过程填充了底行的一些开放站点，那么系统就会渗透。
 * (对于绝缘/金属材料示例，开放位置对应于金属材料，因此渗滤系统具有从顶部到底部的金属路径，全位置导电。
 * 对于多孔物质的例子，开放的位置对应于水可能流过的空的空间，因此渗透的系统让水充满开放的位置，从上到下流动。)
 * 
 * 问题是。
 * 		 在一个著名的科学问题中，研究人员对以下问题感兴趣:如果站点被独立设置为以概率p开放(因此以概率1-p封闭)，系统渗透的概率是多少？
 * 当p等于0时，系统不渗透；当p等于1时，系统渗透。下图显示了20×20随机网格(左)和100×100随机网格(右)的场地空置概率p与渗透概率的关系。
 * 
 * 当n足够大时，存在阈值p*使得当p < p*时，随机n×n网格几乎从不渗透，而当p > p*时，随机n×n网格几乎总是渗透。还没有推导出确定逾渗阈值p*的数学解。
 * 你的任务是写一个计算机程序来估计p*。
 * 
 * @author yangzuliang
 *  地址:https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php
 	解析:https://www.jianshu.com/p/667e0bb3ef88
 */
public class Percolation {
	
	private final WeightedQuickUnionUF uf;
	//0:block;1:open;2:connect to the bottom;只要大于0都是open的
	private byte[][] grid;
	
	private int numberOfOpenSite = 0;
	
	private final int n;
	
	/**
	 * 上下各加一个虚拟节点,最上层只有一个点和第一行所有元素相通,
	 * 同理,最下层只有一个点和最后一行所有元素相通
	 * @param n
	 */
	// creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
    	
    	if(n<=0) {
    		throw new IllegalArgumentException();
    	}
    	
    	uf = new WeightedQuickUnionUF((n+1)*(n+1));
    	grid = new byte[n+1][n+1];
    	this.n = n+1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
    	
    	if(isOpen(row, col)) {
    		return ;
    	}
    	
    	grid[row][col] = 1;
    	numberOfOpenSite++;
    	
    	if(row == n-1) {
    		//底部
    		grid[row][col] = 2;
    	}
    	
    	if(row == 1) {
    		//顶部
    		uf.union(0, row*n + col);
    		
    		if(grid[row][col] == 2) {
    			//最上层仅有一个点
    			grid[0][0] = 2;
    		}
    	}
    	//上
        if (row - 1 > 0 && isOpen(row - 1, col)) {
            update(row - 1, col, row, col);
        }
        // below site is open
        if (row + 1 < n && isOpen(row + 1, col)) {
            update(row + 1, col, row, col);
        }
        // left site is open
        if (col - 1 > 0 && isOpen(row, col - 1)) {
            update(row, col - 1, row, col);
        }
        // right site is open
        if (col + 1 < n && isOpen(row, col + 1)) {
            update(row, col + 1, row, col);
        }
    }
    
    private void update(int i1, int j1, int i2, int j2) {
    	
    	int p = uf.find(i1*n + j1);
    	int q = uf.find(i2*n + j2);
    	
    	uf.union(i1*n + j1, i2*n + j2);
    	
    	if(grid[p/n][p%n] == 2 || grid[q/n][q%n] == 2) {
    		//如果其中一个连接到底部，则更新的组件也连接到底部。
    		int t = uf.find(i2*n + j2);
    		grid[t/n][t%n] = 2;
    	}
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
    	boolean o = grid[row][col] > 0;
    	return o;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
//    	boolean f = grid[row][col] > 0 && (uf.find(row) == uf.find(col));
//    	return f;
    	return grid[row][col] > 0 && uf.connected(0, row * n + col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
    	return numberOfOpenSite;
    }

    // does the system percolate?
    public boolean percolates() {
    	
    	int root = uf.find(0);
    	boolean p = grid[root/n][root%n] == 2;
    	return p;
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Percolation percolation = new Percolation(n);
        boolean isPercolated = false;
        int count = 0;
        while (!in.isEmpty()) {
            int row = in.readInt();
            int col = in.readInt();
            if (!percolation.isOpen(row, col)) {
                count++;
            }
            percolation.open(row, col);
            isPercolated = percolation.percolates();
            if (isPercolated) {
                break;
            }
        }
        StdOut.println(count + " open sites");
        if (isPercolated) {
            StdOut.println("percolates");
        } else {
            StdOut.println("does not percolate");
        }
    }
}
