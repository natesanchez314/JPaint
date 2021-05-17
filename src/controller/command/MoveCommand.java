package controller.command;

import controller.Point;
import controller.shape.IShape;

import java.awt.*;
import java.util.Stack;

public class MoveCommand implements ICommand, IUndoable{

  private final int xShift;
  private final int yShift;
  private final Graphics2D g;
  private final Stack<IShape> selectShapes;

  public MoveCommand(Graphics2D _g, Point startPoint, Point endPoint) {
    g = _g;
    xShift = endPoint.getX() - startPoint.getX();
    yShift = endPoint.getY() - startPoint.getY();
    selectShapes = (Stack<IShape>)CommandHistory.getSelectedShapes().clone();
  }

  @Override
  public void run() {
    move();
    CommandHistory.add(this);
  }

  @Override
  public void redo() {
    move();
  }

  @Override
  public void undo() {
    for (IShape shape : selectShapes) {
      shape.move(-xShift, -yShift);
    }
    CommandHistory.redrawAll();
  }

  private void move() {
    for (IShape shape : selectShapes) {
      shape.move(xShift, yShift);
    }
    CommandHistory.redrawAll();
  }
}
