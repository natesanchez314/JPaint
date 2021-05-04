package controller.shape;

import controller.Point;

import java.awt.*;

public interface IShape {
  void draw();
  void move(int xShit, int yShift);
  Boolean intersects(IShape shape);
  Boolean inside(Point p);
  Point getStartPoint();
  Point getEndPoint();
  Graphics2D getGraphics();
}
