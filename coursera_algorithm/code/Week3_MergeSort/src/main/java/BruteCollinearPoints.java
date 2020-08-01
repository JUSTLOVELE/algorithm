import java.util.ArrayList;
import java.util.List;

/**
 * 写一个BruteCollinearPoints.java程序，一次检查4个点，
 * 检查它们是否都在同一线段上，返回所有这样的线段。
 * 要检查点p、q、r和s是否共线，请检查p和q之间、p和r之间以及p和s之间的三个斜率是否都相等。
 */
public class BruteCollinearPoints {

    private Point[] points;

    private int number;
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

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
    public           int numberOfSegments()    {
        return number;
    }
    public LineSegment[] segments() {

        List<LineSegment> lineSegments = new ArrayList<LineSegment>();

        for(int i=0; i<points.length; i++) {

            Point p = points[i];

            for(int j=i+1; j<points.length; j++) {

                Point q = points[j];

                for(int n=j+1; n<points.length; n++) {

                    Point r = points[n];

                    for(int m=n+1; m<points.length; m++) {

                        Point s = points[m];
                        Double pq = p.slopeTo(q);
                        Double pr = p.slopeTo(r);
                        Double ps = p.slopeTo(s);
                        if(pq == pr && pq == ps) {
                            //四点共线
                            number++;
                            lineSegments.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }

        LineSegment[] ls = new LineSegment[lineSegments.size()];
        for(int i=0; i<lineSegments.size(); i++) {
            ls[i] = lineSegments.get(i);
        }
        return ls;
    }


}
