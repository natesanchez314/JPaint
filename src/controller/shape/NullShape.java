package controller.shape;

import controller.Point;

import java.awt.*;

public class NullShape implements IShape{
  @Override
  public void draw() {

  }

  @Override
  public void move(int xShit, int yShift) {

  }

  @Override
  public Boolean intersects(IShape shape) {
    return null;
  }

  @Override
  public Boolean inside(Point p) {
    return null;
  }

  @Override
  public Point getStartPoint() {
    return null;
  }

  @Override
  public Point getEndPoint() {
    return null;
  }

  @Override
  public Graphics2D getGraphics() {
    return null;
  }

  @Override
  public IShape copy() {
    return null;
  }
}
