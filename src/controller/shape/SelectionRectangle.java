package controller.shape;

import controller.Point;

import java.awt.*;

public class SelectionRectangle implements IShape {

  private final Point startPoint, endPoint;
  private final Graphics2D g;

  public SelectionRectangle(Graphics2D _g, Point _startPoint, Point _endPoint) {
    g = _g;
    startPoint = _startPoint;
    endPoint = _endPoint;
  }

  @Override
  public void draw() { }

  @Override
  public void move(int xShit, int yShift) { }

  @Override
  public Boolean intersects(IShape shape) {
    if (inside(shape.getStartPoint())) return true;
    if (inside(shape.getEndPoint())) return true;
    if (inside(new Point(shape.getStartPoint().getX(), shape.getEndPoint().getY()))) return true;
    if (inside(new Point(shape.getStartPoint().getY(), shape.getEndPoint().getX()))) return true;
    return false;
  }

  @Override
  public Boolean inside(Point p) {
    boolean inX = false;
    boolean inY = false;
    if (p.getX() > startPoint.getX() && p.getX() < endPoint.getX()) inX = true;
    else if (p.getX() < startPoint.getX() && p.getX() > endPoint.getX()) inX = true;
    if (p.getY() > startPoint.getY() && p.getY() < endPoint.getY()) inY = true;
    else if (p.getY() < startPoint.getY() && p.getY() > endPoint.getY()) inY = true;
    return inX && inY;
  }

  @Override
  public Point getStartPoint() {
    return startPoint;
  }

  @Override
  public Point getEndPoint() {
    return endPoint;
  }

  @Override
  public Graphics2D getGraphics() {
    return g;
  }
}
