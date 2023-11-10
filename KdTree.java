import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.Comparator;

public class KdTree {

    private final class Node{
        /*Node class with the following API and variables
        rect: represents the

         */
        private int tt;
        boolean oriantationX;
        private Point2D p;
        // the point
        private RectHV rect;
        // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;
        private double distance;

        public Node(Point2D p,RectHV rectangle,Boolean oriantX){
            rect = rectangle;
            this.p = p;
            oriantationX = oriantX;
        }


        public void setTt(int letter) {
            tt = letter;
        }


        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }


        public Node(Point2D p) {
            this.p = p;
        }



        public void setP(Point2D p) {
            this.p = p;
        }

        public Point2D getP() {
            return p;
        }

        public void setRt(Node rt) {
            this.rt = rt;
        }

        public void setLb(Node lb) {
            this.lb = lb;
        }

        public Node getLb() {
            return lb;
        }

        public Node getRt() {
            return rt;
        }

        public RectHV getRect() {
            return rect;
        }
















                                    
                                    
                                    
                                    
                                    






        public class HorizOrder implements Comparator<Node> {
            public int compare(Node q1, Node q2) {
                double dx1 = q1.getP().x() - q2.getP().x();
                double dy1 = q1.getP().y() - q2.getP().y();

                if(dy1>0) return 1;    // q1 above; q2 below
                else if (dy1<0) return -1;    // q1 below; q2 above
                else {
                    if (dx1 > 0) {
                        return 1;
                    }
                    else if(dx1<0) {
                        return -1;
                    }
                    return 0;
                    // both above or below
                }
                // Note: ccw() recomputes dx1, dy1, dx2, and dy2
            }
        }
        public class VertOrder implements Comparator<Node> {
            public int compare(Node q1, Node q2) {
                double dx1 = q1.getP().x() - q2.getP().x();
                double dy1 = q1.getP().y() - q2.getP().y();

                if(dx1>0) return 1;    // q1 above; q2 below
                else if (dx1<0) return -1;    // q1 below; q2 above
                else {
                    if (dy1 > 0) {
                        return 1;
                    }
                    else if(dy1<0) {
                        return -1;
                    }
                    return 0;// both above or below
                }

                // Note: ccw() recomputes dx1, dy1, dx2, and dy2
            }
        }

    }

    private Node KdTree;
    private int count = 0;
    private int c =0;

    public KdTree() {

    }                               // construct an empty set of points

    public boolean isEmpty() {
        if (KdTree == null) {
            return true;
        }
        return false;
    }                      // is the set empty?

    public int size() {
        return count;
    }                         // number of points in the set

    public void insert(Point2D p) {
        if(p!=null) {
            if (!contains(p)) {
                if (isEmpty()) {
                    KdTree = new Node(p, new RectHV(0, 0, 1, 1), true);
                    KdTree.setTt(c++);
                } else {
                    searchInsertTree(KdTree, p);

                }
                count++;
            }
        }
        else throw new IllegalArgumentException("Point is null");
    }

    private boolean searchTree(Node node, Point2D point) {
        boolean bol2=false;
        if(node!=null) {
            if (node.getP() == null) {
                return false;
            } else if (node.getP().equals(point)) {
                return true;
            } else {
                if (node.oriantationX) {
                    if (point.x() > node.getP().x()) {
                        if (node.getRt() == null) {
                            bol2 |=  false;
                        } else {
                            bol2 |= searchTree(node.getRt(), point);
                        }
                    } else if(point.x() < node.getP().x()){
                        if (node.getLb() == null) {
                            bol2 |= false;
                        } else {
                            bol2 |= searchTree(node.getLb(), point);
                        }
                    }
                    else{
                        if(node.getLb() != null){
                            bol2|= searchTree(node.getLb(),point);
                        }
                        if(node.getRt()!=null){
                            bol2|= searchTree(node.getRt(),point);
                        }
                    }
                } else {
                    if (point.y() > node.getP().y()) {
                        if (node.getRt() == null) {
                            bol2|= false;
                        } else {
                            return searchTree(node.getRt(), point);
                        }
                    } else if(point.y() < node.getP().y()){
                        if (node.getLb() == null) {
                            bol2|= false;
                        } else {
                            bol2|= searchTree(node.getLb(), point);
                        }
                    }
                    else{
                        if(node.getLb() != null){
                            bol2|= searchTree(node.getLb(),point);
                        }
                        if(node.getRt()!=null){
                            bol2|= searchTree(node.getRt(),point);
                        }
                    }
                }
            }
        }
        return bol2;
    }

        private void searchInsertTree(Node node,Point2D point){
            if(node.getP()==null){
                node.setP(point);
                node.oriantationX = !node.oriantationX;
            }
            else if(node.oriantationX){
                if(point.x()>node.getP().x()){
                    if(node.getRt()==null) {
                        node.setRt(new Node(point,new RectHV(node.getP().x(),node.getRect().ymin(),node.getRect().xmax(),node.getRect().ymax()),!node.oriantationX));
                        node.getRt().setTt(c++);
                    }
                    else if(node.getRt().getP()==null){
                        node.getRt().setP(point);
                        node.getRt().setTt(c++);
                    }
                    else{
                        searchInsertTree(node.getRt(),point);
                    }
                }
                else {
                    if (node.getLb() == null) {
                        node.setLb(new Node(point, new RectHV(node.getRect().xmin(), node.getRect().ymin(), node.getP().x(), node.getRect().ymax()), !node.oriantationX));
                        node.getLb().setTt(c++);
                    } else if (node.getLb().getP() == null) {
                        node.getLb().setP(point);
                        node.getLb().setTt(c++);
                    } else {
                        searchInsertTree(node.getLb(), point);
                    }
                }
            }
            else{
                if(point.y()>node.getP().y()){
                    if(node.getRt()==null) {
                        node.setRt(new Node(point,new RectHV(node.getRect().xmin(),node.getP().y(),node.getRect().xmax(),node.getRect().ymax()),!node.oriantationX));
                        node.getRt().setTt(c++);
                    }
                    else if(node.getRt().getP()==null){
                        node.getRt().setP(point);
                        node.getRt().setTt(c++);
                    }
                    else{
                        searchInsertTree(node.getRt(),point);
                    }
                }
                else {
                    if (node.getLb() == null) {
                        node.setLb(new Node(point, new RectHV(node.getRect().xmin(), node.getRect().ymin(), node.getRect().xmax(), node.getP().y()), !node.oriantationX));
                        node.getLb().setTt(c++);
                    } else if (node.getLb().getP() == null) {
                        node.getLb().setP(point);
                        node.getLb().setTt(c++);
                    } else {
                      searchInsertTree(node.getLb(), point);
                    }
                }
            }
        }// add the point to the set (if it is not already in the set)
        public boolean contains(Point2D p){
        if(p!=null) {
            if(KdTree!=null) {
                return searchTree(KdTree, p);
            }
            else return false;
        }
        else throw new IllegalArgumentException("Point is null");
        }            // does the set contain point p?

    private void drawSubtree(Node node){
        if(node!=null&&node.getP()!=null) {
            node.getP().draw();
            double x = node.getP().x();
            double y = node.getP().y();
            RectHV rect = node.getRect();
            if(node.oriantationX){
                StdDraw.setPenColor(255,0,0);
                StdDraw.line(x,rect.ymax(),x,rect.ymin());
            }
            else{
                StdDraw.setPenColor(0,0,255);
                StdDraw.line(rect.xmin(),y,rect.xmax(),y);
            }
            if (node.getLb() != null && node.getLb().getP() != null) {
                drawSubtree(node.getLb());
            }
            if (node.getRt() != null && node.getRt().getP() != null) {
                drawSubtree(node.getRt());
            }
        }

    }
        public void draw(){
            StdDraw.setPenColor(StdDraw.RED);
           StdDraw.setPenRadius(.01);

            drawSubtree(KdTree);
            StdDraw.show();

        }
        private void arrLis(ArrayList lis,Node temp,RectHV rect){
        if(rect!=null) {
            if (rect.contains(temp.getP())) {
                lis.add(temp.getP());
            }
            if (temp.getLb() != null) {
                if (rect.intersects(temp.getLb().getRect())) {
                    arrLis(lis, temp.getLb(), rect);
                }
            }
            if (temp.getRt() != null) {
                if (rect.intersects(temp.getRt().getRect())) {
                    arrLis(lis, temp.getRt(), rect);
                }
            }
        }
        }// draw all points to standard draw
        public Iterable<Point2D> range(RectHV rect) {
            if (rect != null) {
                Node temp = KdTree;
                ArrayList<Point2D> pp = new ArrayList<>();
                if (temp != null) {
                    arrLis(pp, temp, rect);
                    return pp;
                }
                else return pp;
            } else {
                throw new IllegalArgumentException("Rect is null");
            }
        }
                    // all points that are inside the rectangle (or on the boundary)
        public Point2D nearest(Point2D p){
        if(p!=null) {
            if(KdTree!=null) {
                KdTree.setDistance(p.distanceSquaredTo(KdTree.getP()));
                Node champ = new Node(new Point2D(0,0));
                champ.setDistance(Double.POSITIVE_INFINITY);
                return nearestHelp(KdTree,champ,p).getP();
            }
            else return null;
        }
        else throw new IllegalArgumentException("Point is Null");
        }
        private Node nearestHelp(Node node, Node champ, Point2D point){
            if(node!=null){
                double t = node.getP().distanceSquaredTo(point);
                if(t<champ.getDistance()){
                    champ.setP(node.getP());
                    champ.setDistance(t);
                }
                if(node.getLb()!=null&&node.getRt()==null&&node.getLb().getRect().distanceSquaredTo(point)<champ.getDistance()){
                    nearestHelp(node.getLb(),champ,point);
                }
                else if(node.getRt()!=null&&node.getLb()==null&&node.getRt().getRect().distanceSquaredTo(point)<champ.getDistance()){
                    nearestHelp(node.getRt(),champ,point);
                }
                else{
                    if(node.getLb()!=null&&node.getRt()!=null) {
                        if (node.oriantationX) {
                            if (node.getP().x() > point.x() ) {
                                if(node.getLb().getRect().distanceSquaredTo(point) < champ.getDistance()) {
                                    nearestHelp(node.getLb(), champ, point);
                                    if (node.getRt().getRect().distanceSquaredTo(point) < champ.getDistance()) {
                                        nearestHelp(node.getRt(), champ, point);
                                    }
                                }
                            }
                            else {
                                if(node.getRt().getRect().distanceSquaredTo(point) < champ.getDistance()) {
                                    nearestHelp(node.getRt(), champ, point);
                                    if (node.getLb().getRect().distanceSquaredTo(point) < champ.getDistance()) {
                                        nearestHelp(node.getLb(), champ, point);
                                    }
                                }
                            }
                        } else {
                                if (node.getP().y() > point.y() ) {
                                    if(node.getLb().getRect().distanceSquaredTo(point) < champ.getDistance()) {
                                        nearestHelp(node.getLb(), champ, point);
                                        if (node.getRt().getRect().distanceSquaredTo(point) < champ.getDistance()) {
                                            nearestHelp(node.getRt(), champ, point);
                                        }
                                    }
                                }
                                else {
                                    if(node.getRt().getRect().distanceSquaredTo(point) < champ.getDistance()) {
                                        nearestHelp(node.getRt(), champ, point);
                                        if (node.getLb().getRect().distanceSquaredTo(point) < champ.getDistance()) {
                                            nearestHelp(node.getLb(), champ, point);
                                        }
                                    }
                                }
                            }
                    }
                }
            }
            return champ;

        }
public static void main(String[] args) {
//    A  0.25 1.0
//    B  0.125 0.25
//    C  0.875 0.125
//    D  0.75 0.5
//    E  0.0 0.875

//    A  0.7 0.2
//    B  0.5 0.4
//    C  0.2 0.3
//    D  0.4 0.7
//    E  0.9 0.6

//    A  0.0 0.75
//    B  0.75 0.25
//    C  0.25 0.75
//    D  0.75 0.5
//    E  0.0 0.0
//    F  0.0 1.0
//    G  1.0 0.0
//    H  0.5 0.25
//    I  0.25 0.5
//    J  0.75 0.0

        KdTree st = new KdTree();

        st.insert(new Point2D(0.5, 0.4));//b
        st.insert(new Point2D(0.2, 0.3));//c
        st.insert(new Point2D(0.4, 0.7));//d
        st.insert(new Point2D(0.9, 0.6));//e
        st.insert(new Point2D(0.7, 0.2));//a
//    st.insert(new Point2D(0.0, 0.75));//a
//    st.insert(new Point2D(0.75, 0.25));//b
//    st.insert(new Point2D(0.25, 0.75));//c
//    st.insert(new Point2D(0.75, 0.5));//d
//    st.insert(new Point2D(0.0, 0.0));//e
//    st.insert(new Point2D(0.0, 1.0));//e
//    st.insert(new Point2D(1.0, 0.0));//b
//    st.insert(new Point2D(1.0, 0.0));//b
//    st.insert(new Point2D(0.5, 0.25));//c
//    st.insert(new Point2D(0.25, 0.5));//d
//    st.insert(new Point2D(0.75, 0));//e
//    System.out.println(st.contains(new Point2D(0.0, 1.0)));//e
//    System.out.println(st.size());//e
        System.out.println(st.nearest(new Point2D(0.619, 0.286)));

}
}
