package controller.command;

import controller.Point;
import controller.shape.IShape;
import controller.shape.SelectionRectangle;

import java.awt.*;

public class SelectCommand implements ICommand {

  private final IShape selectionRectangle;

  public SelectCommand(Graphics2D g, Point startPoint, Point endPoint) {
    selectionRectangle = new SelectionRectangle(g, startPoint, endPoint);
  }

  @Override
  public void run() {
    select();
  }

  private void select() {
    SelectedShapes.deselectShapes();
    for (IShape shape : CommandHistory.getShapeList()) {
      if (shape.intersects(selectionRectangle) || selectionRectangle.intersects(shape)) SelectedShapes.selectShape(shape);
    }
  }
}