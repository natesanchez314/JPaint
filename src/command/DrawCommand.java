package command;

import controller.Point;

import java.awt.*;

public class DrawCommand implements ICommand, IUndoable {

  private final Graphics2D g;
  private final Point startPoint;
  private final Point endPoint;

  public DrawCommand(Graphics2D _g, Point _startPoint, Point _endPoint) {
    g = _g;
    startPoint = _startPoint;
    endPoint = _endPoint;
  }

  @Override
  public void run() {
    drawRectangle(startPoint, endPoint);
  }

  @Override
  public void redo() {
    run();
  }

  @Override
  public void undo() {
    int width = endPoint.getX() - startPoint.getX();
    int height = endPoint.getY() - startPoint.getY();
    g.clearRect(startPoint.getX(), startPoint.getY(), width, height);
    //CommandHistory.undo();
  }

  private void drawRectangle(Point startPoint, Point endPoint) {
    g.setColor(Color.GREEN);
    int width = endPoint.getX() - startPoint.getX();
    int height = endPoint.getY() - startPoint.getY();
    g.fillRect(startPoint.getX(), startPoint.getY(), width, height);
  }
}
