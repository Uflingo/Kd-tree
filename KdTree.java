import com.sun.org.apache.regexp.internal.RE;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by Алексей on 29.04.2017.
 */
public class KdTree {

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        public Node(Point2D p, RectHV rect, Node lb, Node rt) {
            this.p = p;
            this.rect = rect;
            this.lb = lb;
            this.rt = rt;
        }

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }
        private void drawVertical(){
            StdDraw.setPenRadius(0.001);
            StdDraw.setPenColor(StdDraw.RED);
            RectHV line = new RectHV(p.x(),rect.ymin(),p.x(),rect.ymax());
            line.draw();
            StdDraw.setPenRadius(0.01);
            StdDraw.setPenColor(StdDraw.BLACK);
            p.draw();
            if (lb != null)
                lb.drawHorizontal();
            if (rt != null)
                rt.drawHorizontal();
        }
        private void drawHorizontal(){
            StdDraw.setPenRadius(0.001);
            StdDraw.setPenColor(StdDraw.BLUE);
            RectHV line = new RectHV(rect.xmin(),p.y(),rect.xmax(),p.y());
            line.draw();
            StdDraw.setPenRadius(0.01);
            StdDraw.setPenColor(StdDraw.BLACK);
            p.draw();
            if (lb != null)
                lb.drawVertical();
            if (rt != null)
                rt.drawVertical();
        }

    }


    private Node topRoot;
    private int treeSize;
    public KdTree() {                               // construct an empty set of points
        topRoot = null;
        treeSize = 0;
    }

    public boolean isEmpty() {                      // is the set empty?
        return (treeSize == 0);
    }

    public int size() {                         // number of points in the set
        return treeSize;
    }

    public void insert(Point2D p) {              // add the point to the set (if it is not already in the set)
        if (p == null)
            throw new java.lang.NullPointerException();

        if (topRoot == null) {
            topRoot = new Node(p, new RectHV(0,0,1,1));
            treeSize++;
        }
        else {
            boolean isVertical = true;
            boolean wentLeft = false;
            Node curNode = topRoot;
            Node prevNode = null;
            while ((curNode != null) && (!curNode.p.equals(p))) {
                if (isVertical) {
                    if (p.x() < curNode.p.x()) {
                        prevNode = curNode;
                        curNode = curNode.lb;
                        wentLeft = true;
                    } else {
                        prevNode = curNode;
                        curNode = curNode.rt;
                        wentLeft = false;
                    }
                    isVertical = false;
                } else {
                    if (p.y() < curNode.p.y()) {
                        prevNode = curNode;
                        curNode = curNode.lb;
                        wentLeft = true;
                    } else {
                        prevNode = curNode;
                        curNode = curNode.rt;
                        wentLeft = false;
                    }
                    isVertical = true;
                }
            }
            if (curNode == null) {
                if (wentLeft) {
                    RectHV rectHV;
                    if (isVertical)
                        rectHV = new RectHV(prevNode.rect.xmin(), prevNode.rect.ymin(), prevNode.rect.xmax(), prevNode.p.y());
                    else
                        rectHV = new RectHV(prevNode.rect.xmin(), prevNode.rect.ymin(), prevNode.p.x(), prevNode.rect.ymax());
                    prevNode.lb = new Node(p, rectHV);
                } else {
                    RectHV rectHV;
                    if (isVertical)
                        rectHV = new RectHV(prevNode.rect.xmin(), prevNode.p.y(), prevNode.rect.xmax(), prevNode.rect.ymax());
                    else
                        rectHV = new RectHV(prevNode.p.x(), prevNode.rect.ymin(), prevNode.rect.xmax(), prevNode.rect.ymax());
                    prevNode.rt = new Node(p, rectHV);
                }
                treeSize++;
            }
        }
    }

    public boolean contains(Point2D p) {            // does the set contain point p?
        if (p == null)
            throw new java.lang.NullPointerException();
        if (topRoot == null)
            return false;

        boolean isVertical = true;
        Node curNode = topRoot;
        while ((curNode != null) && (!curNode.p.equals(p))) {
            if (isVertical) {
                if (p.x() < curNode.p.x()) {
                    curNode = curNode.lb;
                } else {
                    curNode = curNode.rt;
                }
                isVertical = false;
            } else {
                if (p.y() < curNode.p.y()) {
                    curNode = curNode.lb;
                } else {
                    curNode = curNode.rt;
                }
                isVertical = true;
            }
        }
        if (curNode == null)
            return false;
        else
            return true;
    }

    public void draw() {                         // draw all points to standard draw
        if (topRoot != null)
            topRoot.drawVertical();
    }

    public Iterable<Point2D> range(RectHV rect) {             // all points that are inside the rectangle
        if (rect == null)
            throw new java.lang.NullPointerException();
        ArrayList<Point2D> res = new ArrayList<>();

        return res;
    }

    public Point2D nearest(Point2D p){             // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null)
            throw new java.lang.NullPointerException();
        Point2D res = null;

        return res;
    }
    public static void main(String[] args) {                  // unit testing of the methods (optional)
        KdTree t = new KdTree();

        t.insert(new Point2D(0.4,0.4));
        t.insert(new Point2D(0.3,0.1));
        t.insert(new Point2D(0.4,0.7));
        t.insert(new Point2D(0.9,0.6));
        t.draw();

    }
}
