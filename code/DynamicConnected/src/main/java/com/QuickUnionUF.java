package com;


import java.util.List;

import org.junit.Test;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import util.Util;

public class QuickUnionUF {
	
	private int[] id;
	//组件数
	private int count;

	public void init(int n) {
		
		id = new int[n];
		count = n;
		
		for(int i=0; i<n; i++) {
			id[i] = i;
		}
	}
	
	public int count() {
		return count;
	}
	
	public void union(int p, int q) {
		
		int rootP = root(p);
		int rootQ = root(q);
		
		if(rootP == rootQ) {
			return ;
		}
		
		id[p] = q;
		count--;
	}
	
	public int find(int p) {
		
		while(p != id[p]) {
			p = id[p];
		}
		
		return p;
	}
	 
	
	private int root(int i) {
		
		while(i != id[i]) {
			i=id[i];
		}
		
		return i;
	}
	
	@Test
	public void tinyUFtest() {
		
		List<String> datas = Util.readTinyUF();
		int n = Integer.valueOf(datas.get(0));
		QuickUnionUF uf = new QuickUnionUF();
		uf.init(n);
		
		for (int i = 1; i < datas.size(); i++) {

			String s = datas.get(i);
			String[] array = s.split(" ");
			int p = Integer.valueOf(array[0]);
			int q = Integer.valueOf(array[1]);

			if(uf.find(p) == uf.find(q)) {
				continue;
			}

			uf.union(p, q);
			StdOut.println(p + " " + q);
		}
		
		StdOut.println(uf.count() + " components");
	}
	
//    public static void main(String[] args) {
//    	
//        int n = StdIn.readInt();
//        QuickUnionUF uf = new QuickUnionUF(n);
//        
//        while (!StdIn.isEmpty()) {
//        	
//            int p = StdIn.readInt();
//            int q = StdIn.readInt();
//            
//            if (uf.connected(p, q)) {
//            	continue;
//            }
//            
//            uf.union(p, q);
//            StdOut.println(p + " " + q);
//        }
//    }
}
