package com;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.util.Comparator;

public class Selection {

    private Selection() {
        //这个类不能被实例化
    }

    public static void showSort(Double[] a) {
        //从左到右扫描,每次拿最左边的元素和右边的所有元素中最小的那个调换位置。
        Double MAX= Double.MIN_VALUE;
        Double MIN= Double.MAX_VALUE;
        for (int i=0;i<a.length;i++){
            if (a[i]<MIN) MIN=a[i];
            if (a[i]>MAX) MAX=a[i];
        }

        DrawArray(a,0,0,MAX,MIN);//先画第一个
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
            DrawArray(a,i,min,MAX,MIN);
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
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

    public static void sort(Comparable[] a) {
        //从左到右扫描,每次拿最左边的元素和右边的所有元素中最小的那个调换位置。
        int n = a.length;

        for(int i=0; i<n; i++) {

            int minIndex = i;

            for(int j=i+1; i<n; j++) {

                if(less(a[j], a[minIndex])) minIndex = j;
            }

            exch(a, i, minIndex);
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }

    public static void sort(Object[] a, Comparator comparator) {

        int n = a.length;

        for(int i=0; i<n; i++) {
            int min = i;
            for(int j=i+1; j<n; j++) {
                if(less(comparator, a[j], a[min])) min = j;
            }
            exch(a, i, min);
            assert isSorted(a, comparator, 0, i);
        }

        assert isSorted(a, comparator);
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean less(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) < 0;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }
    // is the array sorted from a[lo] to a[hi]
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // is the array a[] sorted?
    private static boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, comparator, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    private static boolean isSorted(Object[] a, Comparator comparator, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(comparator, a[i], a[i-1])) return false;
        return true;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    public static void main(String[] args) {
//        String[] a = StdIn.readAllStrings();
//        Selection.sort(a);
//        show(a);
        int N=20;
        Double[]a=new Double[N];
        for (int i=0;i<N;i++)
            a[i]= StdRandom.uniform(0.0,10.0);
        Selection.showSort(a);
    }

}
