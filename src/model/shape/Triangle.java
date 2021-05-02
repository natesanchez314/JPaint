package model.shape;

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
}
