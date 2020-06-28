package com;

import java.util.List;

import org.junit.Test;

import edu.princeton.cs.algs4.StdOut;
import util.Util;

/**
 * 路径压缩的带权重的并查集算法
 * @author yangzuliang
 *
 */
public class WeightedQuickUnionPathCompressionUF {

	private int[] id;
	
	private int[] size;
	
	private int count;
	
	public void init(int n) {
		
		id = new int[n];
		count = n;
		size = new int[n];
		
		for(int i=0; i<n; i++) {
			id[i] = i;
		}
	}
	
	public int count() {
		return count;
	}
	
	public int find(int p) {
		
		int root = p;
		
		while(root != id[root]) {
			root = id[root];
		}
		
		while(p != root) {
			int newp = id[p];
			id[p] = root;
			p = newp;
		}
		
		return root;
	}
	
	public void union(int p, int q) {
		
		int rootP = find(p);
		int rootQ = find(q);
		
		if(rootP == rootQ) {
			return ;
		}
		//把更小的节点指向更大的节点
		if(size[rootP] < size[rootQ]) {
			id[rootP] = rootQ;
			size[rootQ] += size[rootP];
		}else {
			//一开始应该是相同的,所以这里默认是把Q的根指向p
			id[rootQ] = rootP;
			//然后把P的权重加大
			size[rootP] += size[rootQ];
		}
		
		count--;
	}
	
	@Test
	public void mediumUFtest() {
		
		List<String> datas = Util.readMediumUF();
		int n = Integer.valueOf(datas.get(0));
		WeightedQuickUnionPathCompressionUF uf = new WeightedQuickUnionPathCompressionUF();
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
	
}
