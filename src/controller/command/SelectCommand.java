package controller.command;

import controller.Point;
import controller.shape.IShape;
import controller.shape.SelectionRectangle;

public class SelectCommand implements ICommand, IUndoable {

  private final IShape selectionRectangle;

  public SelectCommand(Point startPoint, Point endPoint) {
    selectionRectangle = new SelectionRectangle(startPoint, endPoint);
  }

  @Override
  public void run() {
    select();
    CommandHistory.add(this);
  }

  @Override
  public void redo() {
    select();
  }

  @Override
  public void undo() {
    CommandHistory.clearSelectedShapes();
  }

  private void select() {
    CommandHistory.clearSelectedShapes();
    for (IShape shape : CommandHistory.getShapeList()) {
      if (shape.intersects(selectionRectangle) || selectionRectangle.intersects(shape)) CommandHistory.selectShape(shape);
    }
  }
}
