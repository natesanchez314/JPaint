package controller.command;

import controller.shape.IShape;

public class CopyCommand implements ICommand {

  @Override
  public void run() {
    for (IShape shape : CommandHistory.getSelectedShapes()) {
      CommandHistory.copyShape(shape);
    }
  }
}
