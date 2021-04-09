package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

  private Graphics2D g;
  private Point startPoint, endPoint;
  private Boolean pressed;

  MouseHandler(Graphics2D _g) {
    g = _g;
    pressed = false;
  }

  public void mousePressed(MouseEvent e) {
    startPoint = new Point((int) e.getPoint().getX(), (int) e.getPoint().getY());
    pressed = true;
  }

  public void mouseReleased(MouseEvent e) {
    endPoint = new Point((int) e.getPoint().getX(), (int) e.getPoint().getY());
    pressed = false;
    drawRectangle();
  }

  private void drawRectangle() {
    g.setColor(Color.GREEN);
    int width = getEndPoint().getX() - getStartPoint().getX();
    int height = getEndPoint().getY() - getStartPoint().getY();
    g.fillRect(getStartPoint().getX(), getStartPoint().getY(), width, height);
  }

  public Point getStartPoint() { return startPoint; }

  public Point getEndPoint() { return endPoint; }

  public Boolean isPressed() { return pressed; }
}
