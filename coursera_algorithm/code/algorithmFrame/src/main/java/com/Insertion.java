package com;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.util.Comparator;

/**
 * 插入排序
 */
public class Insertion {

    private Insertion() {

    }

    public static void showSort(Double[]a){//绘制double类型
        Double MAX= Double.MIN_VALUE;
        Double MIN= Double.MAX_VALUE;
        for (int i=0;i<a.length;i++){
            if (a[i]<MIN) MIN=a[i];
            if (a[i]>MAX) MAX=a[i];
        }
        DrawArray(a,0,0,MAX,MIN);//先画第一个
        int N=a.length;
        for (int i=1;i<N;i++){
            //Insert a[i] among a[i-1],a[i-2],a[i-3]...
            int j=i;
            for (;j>0;j--)
                if (!less(a[j],a[j-1]))//一旦不小于表明该元素已经到了合适的位置，不用再和前面的比较，因为前面已经排过序了
                    break;
                else
                    exch(a,j,j-1);
            DrawArray(a,j,i,MAX,MIN);
        }
    }


    public static void DrawArray(Double[]a,int i,int j,Double MAX,Double MIN){
        //i是insertion sort被新排序的位置，高亮显示Page253，j是当前位置，i+1到j为黑色，其余位置为灰色
        StdDraw.setXscale(-1,a.length+1);
        StdDraw.setYscale(MIN-(MAX-MIN)*0.4,MAX+(MAX-MIN)*0.4);
        for (int index=0;index<a.length;index++){
            if (index<i||index>j)
                StdDraw.setPenColor(Color.gray);
            else if (index>i&&index<=j)
                StdDraw.setPenColor(Color.BLACK);
            else //index==i
                StdDraw.setPenColor(Color.red);
            StdDraw.filledRectangle(index,a[index]/2.0,0.3,a[index]/2.0);
        }
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis()-start<1000);//  wait 1s
        StdDraw.clear();
    }


    //保证数组当前索引的左边有序
    public static void sort(Comparable[] a) {
        int n = a.length;
        for(int i=1; i<n; i++){
            for(int j=i; j>0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        for(int i=lo+1; i<hi; i++) {
            for(int j=i; j>lo && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
        assert isSorted(a, lo, hi);
    }

    public static void sort(Object[] a, Comparator comparator) {
        int n = a.length;
        for(int i=1; i<n; i++) {
            for(int j=i; j>0 && less(a[j], a[j-1], comparator); j--) {
                exch(a, j, j-1);
            }
            assert isSorted(a, 0, i, comparator);
        }
        assert isSorted(a, comparator);
    }

    public static void sort(Object[] a, int lo, int hi, Comparator comparator) {
        for(int i=lo+1; i<hi; i++) {
            for(int j=i; j>lo && less(a[j], a[j-1], comparator); j--) {
                exch(a, j, j-1);
            }
        }
        assert isSorted(a, lo, hi, comparator);
    }

    public static int[] indexSort(Comparable[] a) {
        int n = a.length;
        int [] index = new int[n];

        for(int i=0; i<n; i++){
            index[i] = i;
        }

        for(int i=1; i<n; i++)
            for(int j=i; j>0 && less(a[index[j]], a[index[j-1]]); j--)
                exch(index, j, j-1);

        return index;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean less(Object v, Object w, Comparator comparator) {
        return comparator.compare(v, w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length);
    }

    // is the array a[lo..hi) sorted
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    private static boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, 0, a.length, comparator);
    }

    // is the array a[lo..hi) sorted
    private static boolean isSorted(Object[] a, int lo, int hi, Comparator comparator) {
        for (int i = lo + 1; i < hi; i++)
            if (less(a[i], a[i-1], comparator)) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; insertion sorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
//        String[] a = StdIn.readAllStrings();
//        Insertion.sort(a);
//        show(a);
        int N=20;
        Double[]a=new Double[N];
        for (int i=0;i<N;i++)
            a[i]= StdRandom.uniform(0.0,10.0);
        Insertion.showSort(a);
        assert  Insertion.isSorted(a);
        Insertion.show(a);

    }
}
