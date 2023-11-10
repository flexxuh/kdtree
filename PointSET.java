import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.TreeSet;


public class PointSET {
    private TreeSet<Point2D> bt;
    public PointSET()  {
        bt = new TreeSet<>();
    }                             // construct an empty set of points
    public boolean isEmpty()   {
        if(!bt.isEmpty()){
            return false;
        }
        else {
            size();
            return true;
        }
    }                   // is the set empty?
    public int size() {
        return bt.size();
    }                        // number of points in the set
    public void insert(Point2D p) {
        if(p!=null) {
            if(!contains(p))
            bt.add(p);
        }
        else {
            throw new IllegalArgumentException("Point is null");
        }


    }// add the point to the set (if it is not already in the set)
    public boolean contains(Point2D p){
        if(p!=null) {
            if (bt.contains(p)) {
                isEmpty();
                return true;
            }
            return false;
        }
        else {
            throw new IllegalArgumentException("Point is null");
        }
    }            // does the set contain point p?
    public void draw(){
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(.01);
        for(Point2D i: bt){
            i.draw();
        }
        StdDraw.show();
    }                   // draw all points to standard draw
    public Iterable<Point2D> range(RectHV rect)   {
        if(rect!=null) {
            ArrayList<Point2D> tt = new ArrayList<>();
            for (Point2D i : bt) {
                if (rect.contains(i)) {
                    tt.add(i);
                }
            }
            return tt;
        }
        else {
            throw new IllegalArgumentException("Point is null");
        }
    }          // all points that are inside the rectangle (or on the boundary)


    public Point2D nearest(Point2D arg) {
        if (arg != null) {
            double max = 10000;
            Point2D pp=null;
            for (Point2D i : bt) {
                if (i.distanceSquaredTo(arg) < max) {
                    max = i.distanceSquaredTo(arg);
                    pp = i;
                }
            }
            return pp;
        }
        else {
            throw new IllegalArgumentException("Point is null");
        }
    }
// a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {
//            KdTree st = new KdTree();
//        st.insert(new Point2D(0.7, .2));//a
//        st.insert(new Point2D(0.5, 0.4));//b
//        st.insert(new Point2D(0.2, 0.3));//c
//        st.insert(new Point2D(0.4, 0.7));//d
//        st.insert(new Point2D(.9, .6));//e
//
////        st.insert(new Point2D(0.25, 0.0));//a
////        st.insert(new Point2D(0.625, 0.25));//b
////        st.insert(new Point2D(1.0, 0.375));//c
////        st.insert(new Point2D(0.875, 0.875));//d
////        st.insert(new Point2D(0.0, 0.125));//e
//
////        st.insert(new Point2D(.1875, 0.125));
////        st.insert(new Point2D(.0625, 0.375));
////        st.nearest(new Point2D(0.625, 0.625));
////        st.insert(new Point2D(1,0));
////        st.insert(new Point2D(.375, 0.25));
//
////            st.insert(new Point2D(0.372, 0.497));
////            st.insert(new Point2D(0.564, 0.413));
////            st.nearest(new Point2D(0.226, 0.577));
////            st.insert(new Point2D(0.144, 0.179));
////            st.insert(new Point2D(0.083, 0.51));
////            st.insert(new Point2D(0.32, 0.708));
////            st.insert(new Point2D(0.417, 0.362));
////            st.insert(new Point2D(0.862, 0.825));
////            st.insert(new Point2D(0.785, 0.725));
////            st.insert(new Point2D(0.499, 0.208));
//
//
////        st.isEmpty();
////        st.isEmpty();
////        st.insert(new Point2D( 0.3125, 0.625));
////        st.nearest(new Point2D(0.25, 0.625));
//    st.isEmpty();
//    st.size();
//    System.out.println(st.nearest(new Point2D(0.828, 0.245)));
//    System.out.println(st.nearest(new Point2D(0.828, 0.245)));
//    System.out.println(st.nearest(new Point2D(0.828, 0.245)));
//    System.out.println(st.nearest(new Point2D(0.828, 0.245)));
//
//        st.draw();

        Picture pp = new Picture("logo.png");
        pp.show();
    }// unit testing of the methods (optional)
}