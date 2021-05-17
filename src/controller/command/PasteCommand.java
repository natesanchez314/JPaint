package controller.command;

import controller.shape.IShape;

import java.awt.*;
import java.util.Stack;

public class PasteCommand implements ICommand, IUndoable {

  private final Stack<IShape> pastedShapes = new Stack<>();

  public PasteCommand() {
    for (IShape shape : CommandHistory.getClipBoard()) {
      IShape newShape = shape.copy();
      newShape.move(10, 10);
      pastedShapes.push(newShape);
    }
    CommandHistory.clearClipBoard();
    for (IShape shape : pastedShapes) {
      CommandHistory.copyShape(shape);
    }
  }

  @Override
  public void run() {
    if (!pastedShapes.isEmpty()) {
      for (IShape shape : pastedShapes) {
        CommandHistory.addShape(shape);
      }
      CommandHistory.redrawAll();
    }
    CommandHistory.add(this);
  }

  @Override
  public void redo() {
    if (!pastedShapes.isEmpty()) {
      for (IShape shape : pastedShapes) {
        CommandHistory.addShape(shape);
      }
      CommandHistory.redrawAll();
    }
  }

  @Override
  public void undo() {
    if (!pastedShapes.isEmpty()) {
      for (IShape shape : pastedShapes) {
        CommandHistory.removeShape(shape);
      }
      CommandHistory.redrawAll();
    }
  }
}
