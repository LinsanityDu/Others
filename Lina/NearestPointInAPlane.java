public interface PointsOnAPlane {

    /**
     * Stores a given point in an internal data structure
     */
    void addPoint(Point point);

    /**
     * For given 'center' point returns a subset of 'm' stored points that are
     * closer to the center than others.
     *
     * E.g. Stored: (0, 1) (0, 2) (0, 3) (0, 4) (0, 5)
     *
     * findNearest(new Point(0, 0), 3) -> (0, 1), (0, 2), (0, 3)
     */
    Collection<Point> findNearest(Point center, int m);

}
这个题是否需要写一个distance类？

public static class Point {
    private int x;
    private int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDistanceTo(Point p) {
        int dx = this.x - p.x;
        int dy = this.y - p.y;
        return dx * dx + dy * dy;
    }
}

public class PointOnAPlaneImp implements PointsOnAPlane {
    private List<Point> points = new ArrayList<Point>();

    @Override
    void addPoint(Point point) {
        points.add(point);
    }

    @Override
    Collection<Point> findNearest(Point center, int m) {
        if (center == null || m <= 0) return null;
        // MaxHeap
        Queue<Point> maxHeap = new PriorityQueue<Point>(m, new Comparator<Point>() {
            @Override
            public int compare(Point a, Point b) {
                return b.getDistanceTo(center) - a.getDistanceTo(center);
            }
        });

        for(int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            if (p == center) continue;
            if (maxHeap.size() < m) {
                maxHeap.add(p);
            } else {
                if (p.getDistanceTo(center) < maxHeap.peek().getDistanceTo(center)) {
                    maxHeap.poll();
                    maxHeap.add(p);
                }
            }
        }
        return maxHeap;
        // Or print....

}



/*
public class Point {
    public int x;
    public int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
*/
 
public List<Point> findKClosest(Point[] p, int k) {
    PriorityQueue<Point> pq = new PriorityQueue<>(10, new Comparator<Point>() {
        @Override
        public int compare(Point a, Point b) {
            return (b.x * b.x + b.y * b.y) - (a.x * a.x + a.y * a.y);
        }
    });
     
    for (int i = 0; i < p.length; i++) {
        if (i < k)
            pq.offer(p[i]);
        else {
            Point temp = pq.peek();
            if ((p[i].x * p[i].x + p[i].y * p[i].y) - (temp.x * temp.x + temp.y * temp.y) < 0) {
                pq.poll();
                pq.offer(p[i]);
            }
        }
    }
     
    List<Point> x = new ArrayList<>();
    while (!pq.isEmpty())
        x.add(pq.poll());
     
    return x;
}

