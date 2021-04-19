package model.shape;

import controller.Point;

import java.awt.*;

public class Rectangle implements IShape {

  private final Point startPoint, endPoint;
  private final Graphics2D g;
  private final Color color = Color.GREEN;

  public Rectangle(Point _startPoint, Point _endPoint, Graphics2D _g) {
    this.startPoint = _startPoint;
    this.endPoint = _endPoint;
    this.g = _g;
  }

  @Override
  public void setShapeColor() {
    //TODO
  }

  @Override
  public void setShadingType() {
    //TODO
  }

  @Override
  public void draw() {
    g.setColor(color);
    int width = endPoint.getX() - startPoint.getX();
    int height = endPoint.getY() - startPoint.getY();
    g.fillRect(startPoint.getX(), startPoint.getY(), width, height);
  }
}
