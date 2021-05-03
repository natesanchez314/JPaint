package controller.shape;

import controller.Point;
import model.persistence.ApplicationState;

import java.awt.*;

public class ShapeFactory {

  public static IShape getRectangle(Graphics2D g, ApplicationState appState, Point startPoint, Point endPoint) {
    return new Rectangle(g, startPoint, endPoint, appState.getActivePrimaryColor().getColor(), appState.getActiveSecondaryColor().getColor(), appState.getActiveShapeShadingType());
  }

  public static IShape getTriangle(Graphics2D g, ApplicationState appState, Point startPoint, Point endPoint) {
    return new Triangle(g, startPoint, endPoint, appState.getActivePrimaryColor().getColor(), appState.getActiveSecondaryColor().getColor(), appState.getActiveShapeShadingType());
  }

  public static IShape getEllipse(Graphics2D g, ApplicationState appState, Point startPoint, Point endPoint) {
    return new Ellipse(g, startPoint, endPoint, appState.getActivePrimaryColor().getColor(), appState.getActiveSecondaryColor().getColor(), appState.getActiveShapeShadingType());
  }
}
