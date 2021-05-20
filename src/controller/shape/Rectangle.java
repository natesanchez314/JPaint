package controller.shape;

import controller.Point;
import model.ShapeShadingType;

import java.awt.*;

public class Rectangle implements IShape {

  private final Point startPoint;
  private final Point endPoint;
  private final Graphics2D g;
  private final Color primaryColor;
  private final Color secondaryColor;
  private final ShapeShadingType shapeShadingType;

  public Rectangle(Graphics2D _g,
                   Point _startPoint,
                   Point _endPoint,
                   Color _primaryColor,
                   Color _secondaryColor,
                   ShapeShadingType _shapeShadingType) {
    g = _g;
    startPoint = _startPoint;
    endPoint = _endPoint;
    primaryColor = _primaryColor;
    secondaryColor = _secondaryColor;
    shapeShadingType = _shapeShadingType;
  }

  @Override
  public void draw() {
    int width = endPoint.getX() - startPoint.getX();
    int height = endPoint.getY() - startPoint.getY();
    g.setColor(primaryColor);
    if (shapeShadingType == ShapeShadingType.FILLED_IN) {
      g.fillRect(startPoint.getX(), startPoint.getY(), width, height);
    } else if (shapeShadingType == ShapeShadingType.OUTLINE) {
      if (width < 0) {
        g.setColor(primaryColor);
        if (height < 0) g.drawRect(endPoint.getX(), endPoint.getY(), -width, -height);
        else g.drawRect(endPoint.getX(), startPoint.getY(), -width, height);
      } else {
        if (height < 0) g.drawRect(startPoint.getX(), endPoint.getY(), width, -height);
        else g.drawRect(startPoint.getX(), startPoint.getY(), width, height);
      }
    } else {
      if (width < 0) {
        if (height < 0) {
          g.fillRect(endPoint.getX(), endPoint.getY(), -width, -height);
          g.setColor(secondaryColor);
          g.drawRect(endPoint.getX(), endPoint.getY(), -width, -height);
        } else {
          g.fillRect(endPoint.getX(), startPoint.getY(), -width, height);
          g.setColor(secondaryColor);
          g.drawRect(endPoint.getX(), startPoint.getY(), -width, height);
        }
      } else {
        if (height < 0) {
          g.fillRect(startPoint.getX(), endPoint.getY(), width, -height);
          g.setColor(secondaryColor);
          g.drawRect(startPoint.getX(), endPoint.getY(), width, -height);
        } else {
          g.fillRect(startPoint.getX(), startPoint.getY(), width, height);
          g.setColor(secondaryColor);
          g.drawRect(startPoint.getX(), startPoint.getY(), width, height);
        }
      }
    }
  }

  @Override
  public void move(int xShift, int yShift) {
    startPoint.shiftX(xShift);
    startPoint.shiftY(yShift);
    endPoint.shiftX(xShift);
    endPoint.shiftY(yShift);
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
    return g;
  }

  @Override
  public IShape copy() {
    return new Rectangle(g, startPoint.copy(), endPoint.copy(), primaryColor, secondaryColor, shapeShadingType);
  }
}
