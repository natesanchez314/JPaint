package controller;

import controller.command.CreateShapeCommand;
import controller.command.MoveCommand;
import controller.command.SelectCommand;
import model.MouseMode;
import model.persistence.ApplicationState;
import model.shape.IShape;
import model.shape.ShapeFactory;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

  private Graphics2D g;
  private Point startPoint;
  private final ApplicationState applicationState;

  public MouseHandler(Graphics2D _g, ApplicationState _applicationState) {
    g = _g;
    applicationState = _applicationState;
  }

  public void mousePressed(MouseEvent e) {
    startPoint = new Point((int) e.getPoint().getX(), (int) e.getPoint().getY());
  }

  public void mouseReleased(MouseEvent e) {
    Point endPoint = new Point((int) e.getPoint().getX(), (int) e.getPoint().getY());
    IShape shape;
    if (applicationState.getActiveMouseMode() == MouseMode.DRAW) {
      switch (applicationState.getActiveShapeType()) {
        case ELLIPSE -> shape = ShapeFactory.getEllipse(g, applicationState, startPoint, endPoint);
        case TRIANGLE -> shape = ShapeFactory.getTriangle(g, applicationState, startPoint, endPoint);
        case RECTANGLE -> shape = ShapeFactory.getRectangle(g, applicationState, startPoint, endPoint);
        default -> throw new IllegalStateException("Unexpected value: " + applicationState.getActiveShapeType());
      }
      new CreateShapeCommand(shape, g, startPoint, endPoint).run();
    } else if (applicationState.getActiveMouseMode() == MouseMode.SELECT) {
      new SelectCommand(startPoint, endPoint).run();
    } else {
      new MoveCommand(g, startPoint, endPoint).run();
    }
  }
}
