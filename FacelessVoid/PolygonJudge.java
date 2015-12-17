/*    给一系列点，然后判断能不能构成多边形，面试官 刷刷的写了个类出来（真想哭，别人都是把题目先在colledit上粘出来了，为毛我这种待遇哎），看到这个题我第一反应是要求求凸包，于是我问面试官给我一些样例 来clarify 一下这个问题，坑爹面试官拒绝给，而让我自己想，我先列举了两个点情况，然后3个点，共线情况，然后说 给的点至少要能构成一个三角形，然后他说为啥（囧 我纳闷，这不是很显然么），我又写了一个直线的一般情况来说明，还有重点的情况来说明，他说这确实不能构成多边形，难道还有啥情况我没考虑的么，我想了下没做声， 面试官列了一个中间包了一个点的图形，问我能不能构成，我说可以啊，除了中间的点，能构成个凸包，面试官说不是要求一个凸包，囧，我郁闷了，那是要求啥，我停顿了一分钟，他又写了个样例，我一看是个四边形，说可以，他说不可以，我又看了一下，我说按这个连接方法可以，然后他说按照顺序连，他不认为用户会想要知道怎么练的细节。我想哭，你咋不早说啊，这时都讨论了15分钟了！！！.我说要一个个点判断新加的线段是不是与前面相交，我急着编码，还没完全考虑清楚全部共线和存在重合点的情况，先把主体的先写出来吧，两层循环暴利之，码了一会儿，留个了判断相交的函数isIntersect(),写完准备开始处理共线情况，这时面试官说话了，存在bug,我慌了找了下，貌似循环边界条件不对（大概思索了两分钟，现在想起来当时反应怎么那么慢），我改了下，解释了下，然后面试官说 ，这是个bug，还有，我继续查循环，怎么看都没有了啊，面试官提示我X行，我又看了会（真是弱到极致），边界用例少=号。时间剩下3分钟，面试官叫停，开始聊天，我本来准备问很多的，就问了两个问题，扯了下他关于Thrift,Rest经验。FB就这样跪了，没想过FB会出计算几何题，没想到面试官会是这种面试形式，总之跪得非常不爽，努力刷题，复习基础哎，实力还是太弱了
最后附上 我后面写的代码
*/

http://www.meetqun.com/thread-596-1-1.html

mport java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
 
  public static class Point {
    double x;
    double y;
    public Point(double x, double y) {
      this.x = x;
      this.y = y;
    }
  }
 
  private int orientation(Point p, Point q, Point r) {
    double dx1 = q.x - p.x;
    double dy1 = q.y - p.y;
    double dx2 = r.x - q.x;
    double dy2 = r.y - q.y;
   
    double val = dx1 * dy2 - dx2 * dy1;
    if (val == 0) return 0;
    else return val > 0 ? 1 : -1;
  }
 
  private boolean onLine(Point p1, Point p2, Point q) {
    if (q.x >= Math.min(p1.x, p2.x) && q.y <= Math.max(p1.y, p2.y)) {
      return true;
    } else {
      return false;
    }
  }
 
  private boolean doIntersect(Point p1, Point p2, Point q1, Point q2) {
    int o1 = orientation(p1, p2, q1);
    int o2 = orientation(p1, p2, q2);
    int o3 = orientation(q1, q2, p1);
    int o4 = orientation(q1, q2, p2);
    if (o1 != o2 && o3 != o4) {
      return true;
    }
    if (o1 == 0 && onLine(p1, p2, q1)) return true;
    if (o2 == 0 && onLine(p1, p2, q2)) return true;
    if (o3 == 0 && onLine(q1, q2, p1)) return true;
    if (o4 == 0 && onLine(q1, q2, p2)) return true;
    return false;
  }
 
  private boolean isValidPolygon(List<Point> points) {
    // if point overlap
    List<Point> newPoints = new ArrayList<Point>();
    for (int i = 0; i < points.size(); i++) {
      if (i != 0 && points.get(i).x == points.get(i - 1).x
         && points.get(i).y == points.get(i - 1).y) {
        continue;
      }
      newPoints.add(points.get(i));
    }
    if (newPoints.size() <= 2) {
      return false;
    }
    for (int i = 0; i < newPoints.size(); i++) {
      for (int j = 0; j < i; j++) {
        Point p1 = newPoints.get(i);
        Point p2 = newPoints.get(j);
        if (p1.x == p2.x && p1.y == p2.y) {
          return false;
        }
      }
    }
    // check if straight line
    for (int i = 2; i < newPoints.size(); i++) {
      Point p1 = newPoints.get(i - 2);
      Point p2 = newPoints.get(i - 1);
      Point p3 = newPoints.get(i);
      int o = orientation(p1, p2, p3);
      if (o == 0) return false;
    }
    // check if line overlap
    for (int i = 1; i < newPoints.size(); i++) {
      Point p1 = newPoints.get(i - 1);
      Point p2 = newPoints.get(i);
      for (int j = 1; j < i - 1; j++) {
        Point q1 = newPoints.get(j - 1);
        Point q2 = newPoints.get(j);
        if (doIntersect(p1, p2, q1, q2)) {
          return false;
        }
      }
    }
    Point p1 = newPoints.get(newPoints.size() - 1);
    Point p2 = newPoints.get(0);
    for (int i = 2; i < newPoints.size() - 1; i++) {
      Point q1 = newPoints.get(i - 1);
      Point q2 = newPoints.get(i);
      if (doIntersect(p1, p2, q1, q2)) {
        return false;
      }
    }
    return true;
  }
 
  public static void main(String[] args) {
    Solution s = new Solution();
    List<Point> points = new ArrayList<>();
    points.add(new Point(0, 0));
    points.add(new Point(0, 1));
    points.add(new Point(1, 0));
    points.add(new Point(1, 1));
    System.out.println(s.isValidPolygon(points));
   
    points.clear();
    points.add(new Point(0, 0));
    points.add(new Point(0, 1));
    points.add(new Point(1, 1));
    points.add(new Point(1, 0));
    System.out.println(s.isValidPolygon(points));
   
    points.clear();
    points.add(new Point(0, 0));
    points.add(new Point(1, 2));
    points.add(new Point(2, 4));
    System.out.println(s.isValidPolygon(points));
   
    points.clear();
    points.add(new Point(0, 0));
    points.add(new Point(1, 2));
    points.add(new Point(1, 2));
    System.out.println(s.isValidPolygon(points));
   
    points.clear();
    points.add(new Point(0, 0));
    points.add(new Point(1, 2));
    points.add(new Point(1, 2));
    points.add(new Point(2, 3));
    points.add(new Point(2, 3));
    System.out.println(s.isValidPolygon(points));
  }
}