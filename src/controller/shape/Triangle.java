package controller.shape;

import controller.Point;
import model.ShapeShadingType;

import java.awt.*;

public class Triangle implements IShape {

  private final Point startPoint, endPoint;
  private final Graphics2D g;
  private final Color primaryColor;
  private final Color secondaryColor;
  private final ShapeShadingType shapeShadingType;

  public Triangle(Graphics2D _g,
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
    int[] xPoints = {startPoint.getX(), startPoint.getX() + width / 2, endPoint.getX()};
    int[] yPoints = {startPoint.getY(), endPoint.getY(), startPoint.getY() };
    g.setColor(primaryColor);
    if (shapeShadingType == ShapeShadingType.FILLED_IN) {
      g.fillPolygon(xPoints, yPoints, 3);
    } else if (shapeShadingType == ShapeShadingType.OUTLINE) {
      g.drawPolygon(xPoints, yPoints, 3);
    } else {
      g.fillPolygon(xPoints, yPoints, 3);
      g.setColor(secondaryColor);
      g.drawPolygon(xPoints, yPoints, 3);
    }
  }

  @Override
  public void move(int xShit, int yShift) {
    startPoint.shiftX(xShit);
    startPoint.shiftY(yShift);
    endPoint.shiftX(xShit);
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
}
