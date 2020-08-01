import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private Point[] points;

    private int number;
    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {

        if(points == null || points.length == 0)
            throw new IllegalArgumentException();

        for(Point point: points) {
            if(point == null)
                throw new IllegalArgumentException();
        }

        this.points = points;
        this.number = 0;
    }
    // the number of line segments
    public           int numberOfSegments() {
        return number;
    }
    // the line segments
    public LineSegment[] segments(){

        List<Double> slopes = new ArrayList<Double>();
        Point p = points[0];

        for(int i=1; i<points.length; i++) {
            slopes.add(p.slopeTo(points[i]));
        }

        Double[] arrays = new Double[slopes.size()];
        Double[] backup = arrays.clone();

        for(int i=0; i<arrays.length; i++) {
            arrays[i] = slopes.get(i);
        }

        Arrays.sort(arrays);
        int equalSlope= 0;//相同的斜率数目
        Point end = null;
        List<Integer> indexes = new ArrayList<Integer>();
        List<LineSegment> lineSegments = new ArrayList<LineSegment>();

        for(int i=0; i<arrays.length; i++) {

            for(int j=0; j<backup.length; j++) {

                if(i==0 && j==0)
                    continue;
                if(arrays[i] == arrays[j]) {
                    equalSlope++;
                    end = points[j];
                    indexes.add(j);
                }
            }

            if(equalSlope >= 3) {

                number++;
                lineSegments.add(new LineSegment(p, end));
            }
            //重置
            equalSlope = 0;
            indexes.clear();

            if(indexes.size() != 0) {

                for(Integer index: indexes) {

                    backup[index] = null;
                }

                List<Double> list = new ArrayList<Double>();

                for(int n=0; n<backup.length; n++) {
                    if(backup[n] != null) {
                        list.add(backup[n]);
                    }
                }

                backup = new Double[list.size()];

                for(int n=0; n<backup.length; n++) {
                    backup[n] = list.get(n);
                }
            }
        }

//        for(int i=1; i<arrays.length; i++) {
//
//            if(slope == arrays[i]) {
//                equalSlope++;
//            }else{
//
//                if(equalSlope >= 3) {
//                    //超过三个
//                    number++;
//                    //lineSegments.add(new LineSegment(p, s));
//                }
//                //斜率不同设为0重新开始
//                equalSlope = 0;
//                slope = arrays[i];
//
//            }
//        }


        LineSegment[] ls = new LineSegment[lineSegments.size()];
        for(int i=0; i<lineSegments.size(); i++) {
            ls[i] = lineSegments.get(i);
        }
        return ls;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In("/input6.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}