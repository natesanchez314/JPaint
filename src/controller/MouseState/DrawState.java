package controller.MouseState;

import controller.Point;
import controller.command.CreateShapeCommand;
import model.persistence.ApplicationState;
import controller.shape.IShape;
import controller.shape.ShapeFactory;

import java.awt.Graphics2D;


public class DrawState implements IState{

  ApplicationState appState;

  public DrawState(ApplicationState _appState) {
    appState = _appState;
  }

  @Override
  public void makeCommand(Graphics2D g, Point startPoint, Point endPoint) {
    IShape shape;
    switch (appState.getActiveShapeType()) {
      case ELLIPSE -> shape = ShapeFactory.getEllipse(g, appState, startPoint, endPoint);
      case TRIANGLE -> shape = ShapeFactory.getTriangle(g, appState, startPoint, endPoint);
      case RECTANGLE -> shape = ShapeFactory.getRectangle(g, appState, startPoint, endPoint);
      default -> throw new IllegalStateException("Unexpected value: " + appState.getActiveShapeType());
    }
    new CreateShapeCommand(shape, g, startPoint, endPoint).run();
  }
}
