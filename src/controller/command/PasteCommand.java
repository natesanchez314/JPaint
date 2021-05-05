package controller.command;

import controller.shape.IShape;

import java.awt.*;
import java.util.Stack;

public class PasteCommand implements ICommand, IUndoable {

  private Stack<IShape> pastedShapes = new Stack<>();

  public PasteCommand() {
    for (IShape shape : CommandHistory.getClipBoard()) {
      pastedShapes.push(shape.copy());
    }
  }

  @Override
  public void run() {
    if (!pastedShapes.isEmpty()) {
      for (IShape shape : pastedShapes) {
        shape.move(10, 10);
        CommandHistory.addShape(shape);
      }
      Graphics2D g = pastedShapes.peek().getGraphics();
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, (int) g.getDeviceConfiguration().getBounds().getWidth(), (int) g.getDeviceConfiguration().getBounds().getHeight());
      for (IShape drawnShape : CommandHistory.getShapeList()) {
        drawnShape.draw();
      }
    }
    CommandHistory.add(this);
  }

  @Override
  public void redo() {
    if (!pastedShapes.isEmpty()) {
      for (IShape shape : pastedShapes) {
        shape.move(10, 10);
        CommandHistory.addShape(shape);
      }
      Graphics2D g = pastedShapes.peek().getGraphics();
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, (int) g.getDeviceConfiguration().getBounds().getWidth(), (int) g.getDeviceConfiguration().getBounds().getHeight());
      for (IShape drawnShape : CommandHistory.getShapeList()) {
        drawnShape.draw();
      }
    }
  }

  @Override
  public void undo() {
    if (!pastedShapes.isEmpty()) {
      for (IShape shape : pastedShapes) {
        CommandHistory.removeShape(shape);
      }
      Graphics2D g = pastedShapes.peek().getGraphics();
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, (int) g.getDeviceConfiguration().getBounds().getWidth(), (int) g.getDeviceConfiguration().getBounds().getHeight());
      for (IShape drawnShape : CommandHistory.getShapeList()) {
        drawnShape.draw();
      }
    }
  }
}
