package controller.command;

import controller.shape.IShape;

import java.util.Stack;

public class SelectedShapes {

  private SelectedShapes() {}

  private static final Stack<IShape> selectedShapes = new Stack<>();

  public static Stack<IShape> getSelectedShapes() {
    return selectedShapes;
  }

  public static void selectShape(IShape shape) {
    selectedShapes.push(shape);
  }

  public static void deselectShapes() {
    selectedShapes.clear();
  }
}
