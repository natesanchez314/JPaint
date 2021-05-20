package controller.command;

import controller.Point;
import controller.shape.IShape;
import controller.shape.SelectedShapeOutline;
import controller.shape.SelectionRectangle;

import java.awt.*;

public class SelectCommand implements ICommand {

  private final IShape selectionRectangle;

  public SelectCommand(Graphics2D g, Point startPoint, Point endPoint) {
    selectionRectangle = new SelectionRectangle(g, startPoint, endPoint);
  }

  @Override
  public void run() {
    CommandHistory.deselectShapes();
    for (IShape shape : CommandHistory.getShapeList()) {
      if (shape.intersects(selectionRectangle) || selectionRectangle.intersects(shape)) {
        CommandHistory.selectShape(new SelectedShapeOutline(shape));
      }
    }
    CommandHistory.redrawAll();
    CommandHistory.printStacks();
  }
}