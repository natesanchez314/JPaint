package controller.command;

import controller.shape.IShape;

import java.awt.*;
import java.util.Stack;

public class DeleteCommand implements ICommand, IUndoable {

  private final Stack<IShape> deletedShapes;

  public DeleteCommand() {
    deletedShapes = (Stack<IShape>) CommandHistory.getSelectedShapes().clone();
  }

  @Override
  public void run() {
    if (!deletedShapes.isEmpty()) {
      for (IShape shape : deletedShapes) {
        CommandHistory.removeShape(shape);
      }
      Graphics2D g = deletedShapes.peek().getGraphics();
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, (int) g.getDeviceConfiguration().getBounds().getWidth(), (int) g.getDeviceConfiguration().getBounds().getHeight());
      for (IShape drawnShape : CommandHistory.getShapeList()) {
        drawnShape.draw();
      }
      CommandHistory.add(this);
    }
  }

  @Override
  public void redo() {
    if (!deletedShapes.isEmpty()) {
      for (IShape shape : deletedShapes) {
        CommandHistory.removeShape(shape);
      }
      Graphics2D g = deletedShapes.peek().getGraphics();
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, (int) g.getDeviceConfiguration().getBounds().getWidth(), (int) g.getDeviceConfiguration().getBounds().getHeight());
      for (IShape drawnShape : CommandHistory.getShapeList()) {
        drawnShape.draw();
      }
    }
  }

  @Override
  public void undo() {
    if (!deletedShapes.isEmpty()) {
      for (IShape shape : deletedShapes) {
        CommandHistory.addShape(shape);
      }
      Graphics2D g = deletedShapes.peek().getGraphics();
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, (int) g.getDeviceConfiguration().getBounds().getWidth(), (int) g.getDeviceConfiguration().getBounds().getHeight());
      for (IShape drawnShape : CommandHistory.getShapeList()) {
        drawnShape.draw();
      }
    }
  }
}
