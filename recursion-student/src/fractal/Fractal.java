package fractal;

import fractal.Turtle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;


/**
 * A class that implements some basic fractal drawing methods using recursion.
 * These methods include the Koch curve, tree, Sierpinski triangle and carpet.
 */
public class Fractal {
  private Graphics2D g2d = null;  // a Graphics2D object as canvas for drawing
  private int width = 0;
  private int height = 0;  // image width and height
  private int maxRecursionLevel = 0;  // maximum recursion level
  private String fractalType = "Koch Curve";  // type of fractal
  private Color color = Color.green;  // draw color

  /**
   * setGraphics.
   * @param g graph
   * @param w width
   * @param h  height
   */
  public void setGraphics(Graphics g, int w, int h) {
    g2d = (Graphics2D)g; 
    width = w; 
    height = h;
  }
  
  public void setFractalType(String t) { 
    fractalType = t; 
  }
  
  public void setColor(Color c) { 
    color = c; 
  }
  
  public void setMaxRecursion(int n) { 
    maxRecursionLevel = n;
  }

  // Recursive method for drawing the Koch curve given two points and the recursion level
  private void drawKochCurve(Point2D p1, Point2D p2, int level) {
    if (level == 0) { // base case
      drawLine(p1, p2);
      return;
    }
      else {
    	  double distance = p2.distance(p1)/3;
    	  Turtle mover = new Turtle(p1,p2);
    	  mover.move(distance);
    	  Point2D p3 = mover.getPosition();
    	  mover.turnLeft(60);
    	  mover.move(distance);
    	  Point2D p4 = mover.getPosition();
    	  mover.turnRight(120);
    	  mover.move(distance);
    	  Point2D p5 = mover.getPosition();
    	  drawKochCurve(p1, p3, level - 1);
    	  drawKochCurve(p3, p4, level - 1);
    	  drawKochCurve(p4, p5, level - 1);
    	  drawKochCurve(p5, p2, level - 1); 
      }
    }
        // TODO
        // Koch subdivision rule: ___ ->  _/\_
  // Recursive method for drawing a fractal Tree given two points and the recursion level
  private void drawTree(Point2D p1, Point2D p2, int level) {
    if (level == 0) { // base case
      drawLine(p1, p2);
      return;
    }
    else {
    	double distance = p2.distance(p1)/3;
    	Turtle mover = new Turtle(p1,p2);
    	mover.move(distance);
    	Point2D p3 = mover.getPosition();
    	distance = 2 * distance;
    	mover.turnLeft(60);
    	mover.move(distance);
    	Point2D p4 = mover.getPosition();
    	mover.turnLeft(180);
    	mover.move(distance);
    	mover.turnLeft(105);
    	mover.move(distance);
    	Point2D p5 = mover.getPosition();
    	drawTree(p1,p3,0);
    	drawTree(p3, p4, level-1);
    	drawTree(p3, p5, level-1);
    }
        // TODO
  }

  // Recursive method for drawing the Sierpinski Triangle given the three points
  // that define the triangle and the recursion level
  private void drawSierpinskiTriangle(Point2D p1, Point2D p2, Point2D p3, int level) {
    if (level == 0) { // base case
      drawTriangle(p1, p2, p3);
      return;
    }
      else {
    	  double distance = p1.distance(p2)/2;
    	  Turtle moverp1p2 = new Turtle(p1, p2);
    	  Turtle moverp1p3 = new Turtle(p1, p3);
    	  Turtle moverp2p3 = new Turtle(p2, p3);
    	  moverp1p2.move(distance);
    	  Point2D p4 = moverp1p2.getPosition();
    	  moverp1p3.move(distance);
    	  Point2D p5 = moverp1p3.getPosition();
    	  moverp2p3.move(distance);
    	  Point2D p6 = moverp2p3.getPosition();
    	  drawSierpinskiTriangle(p1, p4, p5, level-1);
    	  drawSierpinskiTriangle(p2, p4, p6, level-1);
    	  drawSierpinskiTriangle(p3, p6, p5, level-1);  
      }
    }
        // TODO

  // Recursive method for drawing the Sierpinski Carpet given the lower-left corner
  // of the square (p), the side length (a) of the square, and the recursion level
  private void drawSierpinskiCarpet(Point2D p, double a, int level) {
    if (level == 0) { // base case
      drawRectangle(p, a, a);
      return;
    }
      else {
    	  double distance = a/3;
    	  drawSierpinskiCarpet(p, distance, level-1);
    	  Point2D p1 = new Point2D.Double();
    	  p1.setLocation(p.getX(),p.getY()+distance);
    	  drawSierpinskiCarpet(p1, distance, level-1);
    	  Point2D p2 = new Point2D.Double();
    	  p2.setLocation(p.getX(),p.getY()+(2*distance));
    	  drawSierpinskiCarpet(p2, distance, level-1);
    	  Point2D p3 = new Point2D.Double();
    	  p3.setLocation(p.getX()+distance, p.getY());
    	  drawSierpinskiCarpet(p3, distance, level-1);
    	  Point2D p4 = new Point2D.Double();
    	  p4.setLocation(p.getX()+distance,p.getY()+(2*distance));
    	  drawSierpinskiCarpet(p4, distance, level-1);
    	  Point2D p5 = new Point2D.Double();
    	  p5.setLocation(p.getX()+(2*distance), p.getY());
    	  drawSierpinskiCarpet(p5, distance, level-1);
    	  Point2D p6 = new Point2D.Double();
    	  p6.setLocation(p.getX()+(2*distance), p.getY()+distance);
    	  drawSierpinskiCarpet(p6, distance, level-1);
    	  Point2D p7 = new Point2D.Double();
    	  p7.setLocation(p.getX()+(2*distance), p.getY()+(2*distance));
    	  drawSierpinskiCarpet(p7, distance, level-1);
      }
    }
        // TODO


  // This method is left for you to experiment with creative fractals
  // designed by yourself. You will NOT be graded on this method 
  void drawMyFractal(/* other parameters that you may need */ int level) {
    if (level == 0) { // base case
      return;
    }
    /* Your creative fractal shape */
  }

  /** The code below provides utility methods.
   *  You should NOT need to modify any code below.
   */
  public void draw() {
    if (g2d == null || width == 0 || height == 0) {
      return;
    }
    g2d.setBackground(Color.black);
    g2d.clearRect(0, 0, width, height);
    g2d.setColor(color);
    if (fractalType.equalsIgnoreCase("Koch Curve")) {
      drawKochCurve(new Point2D.Double(0.0, 0.4), new Point2D.Double(1.0, 0.4), maxRecursionLevel);
    } else if (fractalType.equalsIgnoreCase("Snowflake")) {
      double r = 0.5;
      Point2D p1 = new Point2D.Double(r * Math.cos(Math.toRadians(150)) + 0.5,
          r * Math.sin(Math.toRadians(150)) + 0.5);
      Point2D p2 = new Point2D.Double(r * Math.cos(Math.toRadians(30)) + 0.5,
          r * Math.sin(Math.toRadians(30)) + 0.5);
      Point2D p3 = new Point2D.Double(r * Math.cos(Math.toRadians(-90)) + 0.5,
          r * Math.sin(Math.toRadians(-90)) + 0.5);
      // Snowflake is made of three Koch curves segments
      //  p1____p2
      //    \  /
      //     \/
      //     p3
      drawKochCurve(p1, p2, maxRecursionLevel);
      drawKochCurve(p2, p3, maxRecursionLevel);
      drawKochCurve(p3, p1, maxRecursionLevel);
    } else if (fractalType.equalsIgnoreCase("Tree")) {
      // double the maximum recursion level because tree subdivides very slowly
      drawTree(new Point2D.Double(0.6, 0.1), new Point2D.Double(0.6, 0.9),
          maxRecursionLevel * 2);
    } else if (fractalType.equalsIgnoreCase("Sp Triangle")) {
      double r = 0.5;
      Point2D p1 = new Point2D.Double(r * Math.cos(Math.toRadians(90)) + 0.5,
          r * Math.sin(Math.toRadians(90)) + 0.5);
      Point2D p2 = new Point2D.Double(r * Math.cos(Math.toRadians(-150)) + 0.5,
          r * Math.sin(Math.toRadians(-150)) + 0.5);
      Point2D p3 = new Point2D.Double(r * Math.cos(Math.toRadians(-30)) + 0.5,
          r * Math.sin(Math.toRadians(-30)) + 0.5);
      drawSierpinskiTriangle(p1, p2, p3, maxRecursionLevel);
    } else if (fractalType.equalsIgnoreCase("Sp Carpet")) {
      drawSierpinskiCarpet(new Point2D.Double(0, 0), 1, maxRecursionLevel);
    } else {
      drawMyFractal(/* other parameters that you may need */ maxRecursionLevel);
    }
  }
  
  /** Draw a straight line between two points P1 and P2.
   * The given x and y values of p1 and p2 are assumed to be within [0, 1] (i.e. normalized).
   * This allows our fractals to be resolution-independent. The method below converts the normalized
   * coordinates to integer coordinates based on the actual image resolution. 
   */
  private void drawLine(Point2D p1, Point2D p2) {
    int x1 = (int)(p1.getX() * width);
    int x2 = (int)(p2.getX() * width);
    // flip the Y coordinate
    // because screen Y axis is flipped from mathematical Y axis
    int y1 = (int)((1.0 - p1.getY()) * height);
    int y2 = (int)((1.0 - p2.getY()) * height);
    g2d.drawLine(x1, y1, x2, y2);
  }

  // Draw a solid rectangle given its lower left corner and its size
  private void drawRectangle(Point2D p, double w, double h) {
    int x0 = (int)(p.getX() * width);
    int y0 = (int)((1.0 - p.getY()) * height);
    int x1 = (int)((p.getX() + w) * width);
    int y1 = (int)((1.0 - (p.getY() + h)) * height);
    int[] xpoints = {x0, x0, x1, x1};
    int[] ypoints = {y0, y1, y1, y0};
    g2d.fillPolygon(xpoints, ypoints, 4);
  }

  // Draw a solid triangle given its three vertices
  private void drawTriangle(Point2D p1, Point2D p2, Point2D p3) {
    int[] xpoints = {(int)(p1.getX() * width),
        (int)(p2.getX() * width),
        (int)(p3.getX() * width)};
    int[] ypoints = {(int)((1.0 - p1.getY()) * height),
        (int)((1.0 - p2.getY()) * height),
        (int)((1.0 - p3.getY()) * height)};
    g2d.fillPolygon(xpoints, ypoints, 3);
  }
}
