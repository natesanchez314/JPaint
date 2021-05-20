package controller.command;

import controller.shape.IShape;
import controller.shape.SelectedShapeOutline;
import controller.shape.ShapeComposite;

import java.util.ArrayList;

public class UngroupCommand implements ICommand, IUndoable {

  ArrayList<ShapeComposite> shapes;

  public UngroupCommand() {
    shapes = new ArrayList<>();
  }

  @Override
  public void run() {
    for (SelectedShapeOutline selectedShape : CommandHistory.getSelectedShapes()) {
      IShape tmpShape = selectedShape.getShape();
      if (selectedShape.getShape()instanceof ShapeComposite) {
        shapes.add((ShapeComposite) tmpShape);
        //((ShapeComposite) tmpShape).ungroup();
        for (IShape shape : ((ShapeComposite) tmpShape).getChildren()) {
          CommandHistory.addShape(shape);
        }
        CommandHistory.removeShape(tmpShape);
      }
    }
    CommandHistory.deselectShapes();
    CommandHistory.add(this);
    CommandHistory.redrawAll();
    //CommandHistory.printStacks();
  }

  @Override
  public void redo() {
    for (ShapeComposite shapeComposite : shapes) {
      for (IShape shape : shapeComposite.getChildren()) {
        CommandHistory.addShape(shape);
      }
      CommandHistory.removeShape(shapeComposite);
    }
    CommandHistory.deselectShapes();
    CommandHistory.redrawAll();
  }

  @Override
  public void undo() {
    for (ShapeComposite shapeComposite : shapes) {
      for (IShape shape : shapeComposite.getChildren()) {
        CommandHistory.removeShape(shape);
      }
      CommandHistory.addShape(shapeComposite);
    }
  }
}
