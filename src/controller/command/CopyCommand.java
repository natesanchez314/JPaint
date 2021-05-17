package controller.command;

import controller.shape.SelectedShapeOutline;

public class CopyCommand implements ICommand {

  @Override
  public void run() {
    CommandHistory.clearClipBoard();
    for (SelectedShapeOutline shape : CommandHistory.getSelectedShapes()) {
      CommandHistory.copyShape(shape.getShape());
    }
  }
}
