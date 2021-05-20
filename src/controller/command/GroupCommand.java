package controller.command;

import controller.shape.IShape;
import controller.shape.SelectedShapeOutline;
import controller.shape.ShapeComposite;

import java.util.ArrayList;

public class GroupCommand implements ICommand, IUndoable {

  private final ShapeComposite shapeComposite;
  private final ArrayList<IShape> shapes;

  public GroupCommand() {
    shapeComposite = new ShapeComposite();
    shapes = new ArrayList<>();
  }

  @Override
  public void run() {
    if (!CommandHistory.getSelectedShapes().isEmpty()) {
      for (SelectedShapeOutline selectedShape : CommandHistory.getSelectedShapes()) {
        shapes.add(selectedShape);
        shapeComposite.addShape(selectedShape.getShape());
        CommandHistory.removeShape(selectedShape.getShape());
      }
      CommandHistory.deselectShapes();
      CommandHistory.addShape(shapeComposite);
      CommandHistory.selectShape(new SelectedShapeOutline(shapeComposite));
      CommandHistory.add(this);
      CommandHistory.redrawAll();
      //CommandHistory.printStacks();
    }
  }

  @Override
  public void redo() {
    for (SelectedShapeOutline selectedShape : CommandHistory.getSelectedShapes()) {
      shapes.add(selectedShape);
      shapeComposite.addShape(selectedShape.getShape());
      CommandHistory.removeShape(selectedShape.getShape());
    }
    CommandHistory.deselectShapes();
    CommandHistory.addShape(shapeComposite);
    CommandHistory.selectShape(new SelectedShapeOutline(shapeComposite));
    CommandHistory.add(this);
    CommandHistory.redrawAll();
    //CommandHistory.printStacks();
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
