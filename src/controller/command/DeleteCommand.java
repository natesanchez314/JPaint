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
      }
      CommandHistory.redrawAll();
    }
  }

  private void deleteShapes() {
    if (!deletedShapes.isEmpty()) {
      for (SelectedShapeOutline selectedShape : deletedShapes) {
        CommandHistory.removeShape(selectedShape.getShape());
      }
      CommandHistory.redrawAll();
    }
  }
}
