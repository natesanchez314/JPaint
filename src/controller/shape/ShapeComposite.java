package controller.shape;

import controller.Point;

import java.awt.*;
import java.util.ArrayList;

public class ShapeComposite implements IShape {

  private final ArrayList<IShape> children;
  private Point startPoint;
  private Point endPoint;

  public ShapeComposite() {
    children = new ArrayList<>();
    startPoint = null;
    endPoint = null;
  }

  public void addShape(IShape shape) {
    children.add(shape);
    if (startPoint == null) {
      startPoint = shape.getStartPoint().copy();
      endPoint = shape.getEndPoint().copy();
    } else {
      if (shape.getStartPoint().getX() < startPoint.getX()) startPoint.setX(shape.getStartPoint().getX());
      if (shape.getStartPoint().getY() < startPoint.getY()) startPoint.setX(shape.getStartPoint().getY());
      if (shape.getEndPoint().getX() > endPoint.getX()) endPoint.setX(shape.getEndPoint().getX());
      if (shape.getEndPoint().getY() > endPoint.getY()) endPoint.setX(shape.getEndPoint().getY());
    }
  }

  public void removeShape(IShape shape) {
    children.remove(shape);
  }

  public ArrayList<IShape> getChildren() {
    return children;
  }

  @Override
  public void draw() {
    for (IShape shape : children) {
      shape.draw();
    }
  }

  @Override
  public void move(int xShift, int yShift) {
    startPoint.shiftX(xShift);
    startPoint.shiftY(yShift);
    endPoint.shiftX(xShift);
    endPoint.shiftY(yShift);
    for (IShape shape : children) {
      shape.move(xShift, yShift);
    }
  }

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
    return children.get(0).getGraphics();
  }

  @Override
  public IShape copy() {
    ShapeComposite newShapeComposite = new ShapeComposite();
    for (IShape shape : children) {
      newShapeComposite.addShape(shape.copy());
    }
    return newShapeComposite;
  }
}
