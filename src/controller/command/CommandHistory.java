package controller.command;

import controller.shape.IShape;

import java.util.Stack;

public final class CommandHistory {
	private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
	private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();
	private static final Stack<IShape> shapeList = new Stack<IShape>();
	private static final Stack<IShape> selectedShapes = new Stack<>();
	private static final Stack<IShape> clipBoard = new Stack<>();

	public static void add(IUndoable cmd) {
		undoStack.push(cmd);
		redoStack.clear();
	}
	public static boolean undo() {
		boolean result = !undoStack.empty();
		if (result) {
			IUndoable c = undoStack.pop();
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

	public static void addShape(IShape shape) {
		shapeList.add(shape);
	}
	public static void removeShape(IShape shape) { shapeList.remove(shape); }

	public static Stack<IShape> getShapeList() {
		return shapeList;
	}
	public static Stack<IShape> getSelectedShapes() {
		return selectedShapes;
	}
	public static void selectShape(IShape shape) {
		selectedShapes.push(shape);
	}
	public static void deselectShapes() {
		selectedShapes.clear();
	}

	public static Stack<IShape> getClipBoard() { return clipBoard; }
	public static void copyShape(IShape shape) { clipBoard.push(shape); }
	public static void clearClipBoard() { clipBoard.clear(); }
}
