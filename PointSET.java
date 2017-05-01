import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by Алексей on 29.04.2017.
 */
public class PointSET {
    private TreeSet<Point2D> set;
    public PointSET() {                               // construct an empty set of points
        set = new TreeSet<>();
    }

    public boolean isEmpty() {                      // is the set empty?
        return set.isEmpty();
    }

    public int size() {                         // number of points in the set
        return set.size();
    }

    public void insert(Point2D p) {              // add the point to the set (if it is not already in the set)
        if (p == null)
            throw new java.lang.NullPointerException();
        set.add(p);
    }

    public boolean contains(Point2D p) {            // does the set contain point p?
        if (p == null)
            throw new java.lang.NullPointerException();
        return set.contains(p);
    }

    public void draw() {                         // draw all points to standard draw
        for (Point2D p: set){
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {             // all points that are inside the rectangle
        if (rect == null)
            throw new java.lang.NullPointerException();
        ArrayList<Point2D> res = new ArrayList<>();
        for (Point2D p: set){
            if (rect.contains(p))
                    res.add(p);
        }
        return res;
    }

    public Point2D nearest(Point2D p){             // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null)
            throw new java.lang.NullPointerException();
        double minDist = Double.MAX_VALUE;
        Point2D res = null;
        for(Point2D point: set){
            if (point.distanceTo(p) < minDist){
                minDist = point.distanceTo(p);
                res = point;
            }
        }
        return res;
    }
    public static void main(String[] args) {                  // unit testing of the methods (optional)

    }
}
