package controller.command;

import controller.shape.IShape;
import controller.shape.SelectedShapeOutline;
import controller.shape.ShapeComposite;

import java.util.ArrayList;

public class GroupCommand implements ICommand, IUndoable {

  ShapeComposite shapeComposite;
  ArrayList<IShape> shapes;

  public GroupCommand() {
    shapeComposite = new ShapeComposite();
    shapes = new ArrayList<>();
  }

  @Override
  public void run() {
    for (SelectedShapeOutline selectedShape : CommandHistory.getSelectedShapes()) {
      CommandHistory.removeShape(selectedShape);
      shapeComposite.addShape(selectedShape.getShape());
      shapes.add(selectedShape);
    }
    CommandHistory.deselectShapes();
    CommandHistory.selectShape(new SelectedShapeOutline(shapeComposite));
    CommandHistory.add(this);
    CommandHistory.redrawAll();
  }

  @Override
  public void redo() {
    for (IShape selectedShape : shapes) {
      CommandHistory.removeShape(selectedShape);
      CommandHistory.deselectShapes();
      shapeComposite.addShape(selectedShape);
    }
    CommandHistory.addShape(shapeComposite);
  }

  @Override
  public void undo() {
    for (IShape groupedShape : shapeComposite.getChildren()) {
      shapeComposite.removeShape(groupedShape);
      CommandHistory.addShape(groupedShape);
      CommandHistory.selectShape(new SelectedShapeOutline(groupedShape));
    }
    CommandHistory.removeShape(shapeComposite);
  }
}
