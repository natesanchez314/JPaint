package controller.command;

import controller.shape.IShape;
import controller.shape.SelectedShapeOutline;
import controller.shape.ShapeComposite;

public class GroupCommand implements ICommand, IUndoable {

  private final ShapeComposite shapeComposite;

  public GroupCommand() {
    shapeComposite = new ShapeComposite();
  }

  @Override
  public void run() {
    if (!CommandHistory.getSelectedShapes().isEmpty()) {
      for (SelectedShapeOutline selectedShape : CommandHistory.getSelectedShapes()) {
        shapeComposite.addShape(selectedShape.getShape());
        CommandHistory.removeShape(selectedShape.getShape());
      }
      CommandHistory.deselectShapes();
      CommandHistory.addShape(shapeComposite);
      CommandHistory.selectShape(new SelectedShapeOutline(shapeComposite));
      CommandHistory.add(this);
      CommandHistory.redrawAll();
    }
  }

  @Override
  public void redo() {
    CommandHistory.deselectShapes();
    for (IShape groupedShape : shapeComposite.getChildren()) {
      CommandHistory.removeShape(groupedShape);
    }
    CommandHistory.addShape(shapeComposite);
    CommandHistory.selectShape(new SelectedShapeOutline(shapeComposite));
    CommandHistory.redrawAll();
  }

  @Override
  public void undo() {
    CommandHistory.deselectShapes();
    for (IShape groupedShape : shapeComposite.getChildren()) {
      CommandHistory.addShape(groupedShape);
      CommandHistory.selectShape(new SelectedShapeOutline(groupedShape));
    }
    CommandHistory.removeShape(shapeComposite);
    CommandHistory.redrawAll();
  }
}
