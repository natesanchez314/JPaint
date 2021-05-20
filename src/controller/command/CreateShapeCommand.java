package controller.command;

import controller.Point;
import controller.shape.IShape;
import controller.shape.SelectedShapeOutline;

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
    CommandHistory.getSelectedShapes().removeIf(selectedShapeOutline -> selectedShapeOutline.getShape() == shape);
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, (int) g.getDeviceConfiguration().getBounds().getWidth(), (int) g.getDeviceConfiguration().getBounds().getHeight());
    // redraw all the shapes
    for (IShape drawnShape : CommandHistory.getShapeList()) {
      drawnShape.draw();
    }
    if (!CommandHistory.getSelectedShapes().isEmpty()) {
      g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
      if (!CommandHistory.getSelectedShapes().isEmpty()) {
        for (SelectedShapeOutline shape : CommandHistory.getSelectedShapes()) {
          shape.draw();
        }
      }
      g.setStroke(new BasicStroke());
    }
  }
}
