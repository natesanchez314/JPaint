package controller.command;

import controller.shape.IShape;

public class CopyCommand implements ICommand {

  @Override
  public void run() {
    CommandHistory.clearClipBoard();
    for (IShape shape : CommandHistory.getSelectedShapes()) {
      CommandHistory.copyShape(shape);
    }
  }
}
