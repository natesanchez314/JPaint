package controller.command;

import model.shape.IShape;
import model.shape.IShapeObserver;

import java.util.Stack;

public final class CommandHistory implements IShapeSubject {
	private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
	private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();
	private static final Stack<IShape> shapeList = new Stack<IShape>();
	private static final Stack<IShape> selectedShapes = new Stack<>();

	public static void add(IUndoable cmd) {
		undoStack.push(cmd);
		redoStack.clear();
	}

	public static void addShape(IShape shape) {
		shapeList.add(shape);
	}

	public static void selectShape(IShape shape) { selectedShapes.push(shape);
	System.out.println(selectedShapes);}

	public static void clearSelectedShapes() { selectedShapes.clear(); }
	
	public static boolean undo() {
		boolean result = !undoStack.empty();
		if (result) {
			IUndoable c = undoStack.pop();
			//shapeList.pop();
			redoStack.push(c);
			c.undo();
		}
		return result;
	}

	public static boolean redo() {
		boolean result = !redoStack.empty();
		if (result) {
			IUndoable c = redoStack.pop();
			undoStack.push(c);
			c.redo();
		}
		return result;
	}

	private static void printStacks() {
		System.out.println("Undo Stack: " + undoStack);
		System.out.println("Redo Stack: " + redoStack);
	}

	public static Stack<IShape> getShapeList() {
		return shapeList;
	}

	public static Stack<IShape> getSelectedShapes() { return selectedShapes; }

	@Override
	public void registerObserver(IShapeObserver observer) {

	}

	@Override
	public void deregisterOberver(IShapeObserver observer) {

	}

	private void notifyObservers() {
		//for (IShapeSubject)
	}
}
