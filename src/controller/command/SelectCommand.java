package controller.command;

import controller.Point;
import controller.shape.IShape;
import controller.shape.SelectionRectangle;

public class SelectCommand implements ICommand {

  private final IShape selectionRectangle;

  public SelectCommand(Point startPoint, Point endPoint) {
    selectionRectangle = new SelectionRectangle(startPoint, endPoint);
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