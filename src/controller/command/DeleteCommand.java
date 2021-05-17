package controller.command;

import controller.shape.IShape;
import controller.shape.SelectedShapeOutline;

import java.awt.*;
import java.util.Stack;

public class DeleteCommand implements ICommand, IUndoable {

  private final Stack<SelectedShapeOutline> deletedShapes;

  public DeleteCommand() {
    deletedShapes = (Stack<SelectedShapeOutline>) CommandHistory.getSelectedShapes().clone();
  }

  @Override
  public void run() {
    deleteShapes();
    CommandHistory.add(this);
  }

  @Override
  public void redo() {
    deleteShapes();
  }

  @Override
  public void undo() {
    if (!deletedShapes.isEmpty()) {
      for (SelectedShapeOutline shape : deletedShapes) {
        CommandHistory.addShape(shape.getShape());
        CommandHistory.selectShape(shape);
      }
    }
    CommandHistory.redrawAll();
  }

  private void deleteShapes() {
    CommandHistory.printStacks();
    if (!deletedShapes.isEmpty()) {
      for (SelectedShapeOutline selectedShape : deletedShapes) {
        CommandHistory.removeShape(selectedShape.getShape());
        CommandHistory.getSelectedShapes().remove(selectedShape);
      }
      Graphics2D g = deletedShapes.peek().getGraphics();
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, (int) g.getDeviceConfiguration().getBounds().getWidth(), (int) g.getDeviceConfiguration().getBounds().getHeight());
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
}
