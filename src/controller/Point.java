package controller;

public class Point {

  private int x, y;

  public Point() {
    x = 0;
    y = 0;
  }

  public Point(int _x, int _y) {
    x = _x;
    y = _y;
  }

  public int getX() { return x; }

  public int getY() { return y; }

  public void setX(int x) { this.x = x; }

  public void setY(int y) { this.y = y; }

  public void shiftX(int xOffset) { x += xOffset; }

  public void shiftY(int yOffset) { y += yOffset; }

  public Point copy() { return new Point(x, y); }
}
