package model.shape;

import controller.Point;
import model.ShapeShadingType;

import java.awt.*;

public class Rectangle implements IShape {

  private final Point startPoint, endPoint;
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
    g.setColor(primaryColor);
    int width = endPoint.getX() - startPoint.getX();
    int height = endPoint.getY() - startPoint.getY();
    g.fillRect(startPoint.getX(), startPoint.getY(), width, height);
  }
}
