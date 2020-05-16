package com;

import org.junit.Test;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF {

	private int[] id;

	private int count;

	public int count() {
		return count;
	}

	private void validate(int p) {

		int n = id.length;

		if (p < 0 || p >= n) {
			throw new IllegalArgumentException("index" + p + " is not between 0 and " + (n - 1));
		}
	}

	/**
	 * 初始化一个数组让其索引对应的值为索引值
	 * 
	 * @param N
	 */
	public QuickFindUF(int N) {

		count = N;
		id = new int[N];

		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}
	
	public int find(int p) {
		validate(p);
		return id[p];
	}

	/**
	 * 检查q和p是否有相同的根需要2次访问
	 * 
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}

	/**
	 * 改变所有的id[q] = id[p] 最多需要2N+2次访问
	 * 
	 * @param p
	 * @param q
	 */
	public void union(int p, int q) {

		validate(p);
		validate(q);

		int pid = id[p];
		int qid = id[q];

		if (pid == qid) {
			return;
		}

		for (int i = 0; i < id.length; i++) {

			if (id[i] == pid) {
				id[i] = qid;
			}
		}

		count--;
	}

	public static void main(String[] args) {
		//运行之后要把resources/file/tinyUF.txt 或 meduimUF.txt的内容拷贝进控制台
		int n = StdIn.readInt();
		QuickFindUF uf = new QuickFindUF(n);

		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (uf.find(p) == uf.find(q))
				continue;
			uf.union(p, q);
			StdOut.println(p + " " + q);
			StdOut.println(uf.count() + " components");
		}
		StdOut.println(uf.count() + " components");
	}
	
	@Test
	public void tt() {
		System.out.println("sds");
	}
}
