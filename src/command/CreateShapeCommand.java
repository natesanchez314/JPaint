package command;

import controller.Point;
import model.persistence.ApplicationState;
import model.shape.IShape;
import model.shape.Rectangle;

import java.awt.*;
import java.util.Iterator;

public class CreateShapeCommand implements ICommand, IUndoable {


  private final IShape shape;

  public CreateShapeCommand(Graphics2D g, Point startPoint, Point endPoint) {
    shape = new Rectangle(startPoint, endPoint, g);
  }

  @Override
  public void run() {
    //TODO Refactor to use a shapelist and shape interface in place of drawRectangle
    shape.draw();
    CommandHistory.add(this);
    CommandHistory.addShape(shape);
  }

  @Override
  public void redo() {
    run();
  }

  @Override
  public void undo() {
    CommandHistory.undo();
  }

  private void drawShape() {
    //TODO
  }
}
