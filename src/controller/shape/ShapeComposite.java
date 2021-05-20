package controller.shape;

import controller.Point;
import controller.command.CommandHistory;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

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
    ArrayList<Integer> xPoints = new ArrayList<>();
    ArrayList<Integer> yPoints = new ArrayList<>();
    children.add(shape);
    if (startPoint == null) {
      startPoint = shape.getStartPoint().copy();
      endPoint = shape.getEndPoint().copy();
    }
    xPoints.add(startPoint.getX());
    xPoints.add(endPoint.getX());
    xPoints.add(shape.getStartPoint().getX());
    xPoints.add(shape.getEndPoint().getX());
    startPoint.setX(Collections.min(xPoints));
    endPoint.setX(Collections.max(xPoints));

    yPoints.add(startPoint.getY());
    yPoints.add(endPoint.getY());
    yPoints.add(shape.getStartPoint().getY());
    yPoints.add(shape.getEndPoint().getY());
    startPoint.setY(Collections.min(yPoints));
    endPoint.setY(Collections.max(yPoints));
  }

  public void removeShape(IShape shape) {
    children.remove(shape);
  }

  public void ungroup() {
    for (IShape groupedShape : children) {
      CommandHistory.addShape(groupedShape);
    }
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
    System.out.println(children);
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
