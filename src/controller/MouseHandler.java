package controller;

import command.CreateShapeCommand;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

  private Graphics2D g;
  private Point startPoint, endPoint;
  private Boolean pressed;

  public MouseHandler(Graphics2D _g) {
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
    new CreateShapeCommand(g, startPoint, endPoint).run();
  }

  public Point getStartPoint() { return startPoint; }

  public Point getEndPoint() { return endPoint; }

  public Boolean isPressed() { return pressed; }
}
