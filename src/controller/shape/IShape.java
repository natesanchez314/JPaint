package controller.shape;

import controller.Point;

public interface IShape {
  void draw();
  void move(int xShit, int yShift);
  Boolean intersects(IShape shape);
  Boolean inside(Point p);
  Point getStartPoint();
  Point getEndPoint();
}
