package controller.command;

import controller.Point;

public class MoveCommand implements ICommand, IUndoable{

  final int hShift;
  final int vShift;

  public MoveCommand(Point startPoint, Point endPoint) {
    hShift = endPoint.getX() - startPoint.getX();
    vShift = endPoint.getY() - startPoint.getY();
  }

  @Override
  public void run() {
    System.out.println("Moving...");
  }

  @Override
  public void redo() {

  }

  @Override
  public void undo() {

  }
}
