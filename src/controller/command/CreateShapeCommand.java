package controller.command;

import controller.Point;
import controller.shape.IShape;

import java.awt.*;

public class CreateShapeCommand implements ICommand, IUndoable {

  private final Graphics2D g;
  private final IShape shape;

  public CreateShapeCommand(IShape _shape, Graphics2D _g) {
    shape = _shape;
    g = _g;
  }

  @Override
  public void run() {
    shape.draw();
    CommandHistory.add(this);
    CommandHistory.addShape(shape);
  }

  @Override
  public void redo() {
    shape.draw();
    CommandHistory.addShape(shape);
  }

  @Override
  public void undo() {
    // clear canvas
    CommandHistory.getShapeList().remove(shape);
    CommandHistory.redrawAll();
  }
}
