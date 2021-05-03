package controller;

import controller.MouseState.DrawState;
import controller.MouseState.IState;
import controller.MouseState.MoveState;
import controller.MouseState.SelectState;
import model.persistence.ApplicationState;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

  private Graphics2D g;
  private Point startPoint;
  private final ApplicationState applicationState;
  private IState mouseHandlerState;
  private final IState DrawState;
  private final IState SelectState;
  private final IState MoveState;

  public MouseHandler(Graphics2D _g, ApplicationState _applicationState) {
    g = _g;
    applicationState = _applicationState;
    DrawState = new DrawState(applicationState);
    SelectState = new SelectState();
    MoveState = new MoveState();
    updateState();
  }

  public void updateState() {
    switch (applicationState.getActiveMouseMode()) {
      case DRAW -> mouseHandlerState = DrawState;
      case SELECT -> mouseHandlerState = SelectState;
      case MOVE -> mouseHandlerState = MoveState;
    }
  }

  public void mousePressed(MouseEvent e) {
    startPoint = new Point((int) e.getPoint().getX(), (int) e.getPoint().getY());
  }

  public void mouseReleased(MouseEvent e) {
    Point endPoint = new Point((int) e.getPoint().getX(), (int) e.getPoint().getY());
    mouseHandlerState.makeCommand(g, startPoint, endPoint);
  }

  /*public void mouseReleased(MouseEvent e) {
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
  }*/
}
