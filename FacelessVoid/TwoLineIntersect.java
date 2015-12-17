Given two line segments (p1, q1) and (p2, q2), find if the given line segments intersect with each other.

import java.io.*;
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
 
  public static void main(String[] args) {
    Point p1 = new Point(1, 1);
    Point q1 = new Point(10, 1);
    Point p2 = new Point(1, 2);
    Point q2 = new Point(10, 2);
    Solution s = new Solution();

    System.out.println(s.doIntersect(p1, q1, p2, q2));

    p1 = new Point(10, 0);
    q1 = new Point(0, 10);
    p2 = new Point(0, 0);
    q2 = new Point(10, 10);
    System.out.println(s.doIntersect(p1, q1, p2, q2));

    p1 = new Point(-5, -5);
    q1 = new Point(0, 0);
    p2 = new Point(1, 1);
    q2 = new Point(10, 10);
    System.out.println(s.doIntersect(p1, q1, p2, q2));
  }
}