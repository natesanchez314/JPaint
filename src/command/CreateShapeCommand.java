package command;

import controller.Point;
import model.shape.IShape;
import model.shape.Rectangle;

import java.awt.*;

public class CreateShapeCommand implements ICommand, IUndoable {

  private final Graphics2D g;
  private final IShape shape;

  public CreateShapeCommand(IShape _shape, Graphics2D g, Point startPoint, Point endPoint) {
    shape = _shape;
    this.g = g;
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
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, (int) g.getDeviceConfiguration().getBounds().getWidth(), (int) g.getDeviceConfiguration().getBounds().getHeight());
    // redraw all the shapes
    for (IShape drawnShape : CommandHistory.getShapeList()) {
      drawnShape.draw();
    }
  }
}
