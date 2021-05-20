package controller.command;

import controller.shape.IShape;
import controller.shape.ShapeComposite;

import java.util.ArrayList;

public class UngroupCommand implements ICommand, IUndoable {

  ShapeComposite shapeComposite;
  ArrayList<IShape> shapes;

  public UngroupCommand() {
  }

  @Override
  public void run() {
    System.out.println("Ungroup");
    CommandHistory.add(this);
  }

  @Override
  public void redo() {

  }

  @Override
  public void undo() {

  }
}
