package controller.shape;

import controller.Point;
import model.ShapeShadingType;

import java.awt.*;

public class SelectedShapeOutline implements IShape {

  private IShape shape;
  private Point startPoint;
  private Point endPoint;

  public SelectedShapeOutline(IShape _shape) {
    shape = _shape;
    startPoint = _shape.getStartPoint().copy();
    endPoint = _shape.getEndPoint().copy();

    if (startPoint.getX() < endPoint.getX()) {
      startPoint.shiftX(-5);
      endPoint.shiftX(5);
    } else {
      startPoint.shiftX(5);
      endPoint.shiftX(-5);
    }
    if (startPoint.getY() < endPoint.getY()) {
      startPoint.shiftY(-5);
      endPoint.shiftY(5);
    } else {
      startPoint.shiftY(5);
      endPoint.shiftY(-5);
    }
  }

  @Override
  public void draw() {
    System.out.println(shape.getClass());
    Graphics2D g = shape.getGraphics();
    if (shape instanceof controller.shape.Rectangle) {
      int width = endPoint.getX() - startPoint.getX();
      int height = endPoint.getY() - startPoint.getY();
      g.setColor(Color.DARK_GRAY);
      if (width < 0) {
        if (height < 0) g.drawRect(endPoint.getX(), endPoint.getY(), -width, -height);
        else g.drawRect(endPoint.getX(), startPoint.getY(), -width, height);
      } else {
        if (height < 0) g.drawRect(startPoint.getX(), endPoint.getY(), width, -height);
        else g.drawRect(startPoint.getX(), startPoint.getY(), width, height);
      }
    } else if (shape instanceof controller.shape.Ellipse) {
      int width = endPoint.getX() - startPoint.getX();
      int height = endPoint.getY() - startPoint.getY();
      if (width < 0) {
        if (height < 0) {
          g.drawOval(endPoint.getX(), endPoint.getY(), -width, -height);
        } else {
          g.drawOval(endPoint.getX(), startPoint.getY(), -width, height);
        }
      } else {
        if (height < 0) {
          g.drawOval(startPoint.getX(), endPoint.getY(), width, -height);
        } else {
          g.drawOval(startPoint.getX(), startPoint.getY(), width, height);
        }
      }
    } else {
      int width = endPoint.getX() - startPoint.getX();
      int[] xPoints = {startPoint.getX(), startPoint.getX() + width / 2, endPoint.getX()};
      int[] yPoints = {startPoint.getY(), endPoint.getY(), startPoint.getY() };
      g.drawPolygon(xPoints, yPoints, 3);
    }
  }

  @Override
  public void move(int xShift, int yShift) {
    startPoint.shiftX(xShift);
    startPoint.shiftY(yShift);
    endPoint.shiftX(xShift);
    endPoint.shiftY(yShift);
    shape.move(xShift, yShift);
  }

  @Override
  public Boolean intersects(IShape shape) {
    return false;
  }

  @Override
  public Boolean inside(Point p) {
    return false;
  }

  @Override
  public Point getStartPoint() {
    return shape.getStartPoint();
  }

  @Override
  public Point getEndPoint() {
    return shape.getEndPoint();
  }

  @Override
  public Graphics2D getGraphics() {
    return shape.getGraphics();
  }

  @Override
  public IShape copy() {
    return null;
  }

  public IShape getShape() {
    return shape;
  }
}
