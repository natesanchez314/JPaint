package model.shape;

import controller.Point;
import model.ShapeShadingType;

import java.awt.*;

public class Ellipse implements IShape {

  private final Point startPoint, endPoint;
  private final Graphics2D g;
  private final Color primaryColor;
  private final Color secondaryColor;
  private final ShapeShadingType shapeShadingType;

  public Ellipse(Graphics2D _g,
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
    g.setColor(primaryColor);
    int width = endPoint.getX() - startPoint.getX();
    int height = endPoint.getY() - startPoint.getY();
    if (width < 0) {
      if (height < 0) {
        g.fillOval(endPoint.getX(), endPoint.getY(), -width, -height);
      } else {
        g.fillOval(endPoint.getX(), startPoint.getY(), -width, height);
      }
    } else {
      if (height < 0) {
        g.fillOval(startPoint.getX(), endPoint.getY(), width, -height);
      } else {
        g.fillOval(startPoint.getX(), startPoint.getY(), width, height);
      }
    }
  }
}
