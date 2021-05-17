package controller.shape;

import controller.Point;
import model.ShapeShadingType;

import java.awt.*;

public class SelectedShapeOutline implements IShape {

  private final IShape shape;
  private Point startPoint;
  private Point endPoint;

  public SelectedShapeOutline(IShape _shape) {
    shape = _shape;
    startPoint = _shape.getStartPoint();
    startPoint.shiftX(-2);
    startPoint.shiftY(-2);
    endPoint = _shape.getEndPoint();
    endPoint.shiftX(2);
    endPoint.shiftY(2);
  }

  @Override
  public void draw() {
    Graphics2D g = shape.getGraphics();
    int width = endPoint.getX() - startPoint.getX();
    int height = endPoint.getY() - startPoint.getY();
    g.setColor(Color.RED);
    if (width < 0) {
      if (height < 0) g.drawRect(endPoint.getX(), endPoint.getY(), -width, -height);
      else g.drawRect(endPoint.getX(), startPoint.getY(), -width, height);
    } else {
      if (height < 0) g.drawRect(startPoint.getX(), endPoint.getY(), width, -height);
      else g.drawRect(startPoint.getX(), startPoint.getY(), width, height);
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
