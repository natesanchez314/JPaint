package controller.command;

import controller.shape.IShape;

import java.util.Stack;

public final class CommandHistory {
	private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
	private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();
	private static final Stack<IShape> shapeList = new Stack<IShape>();

	public static void add(IUndoable cmd) {
		undoStack.push(cmd);
		redoStack.clear();
	}

	public static void addShape(IShape shape) {
		shapeList.add(shape);
	}
	
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
}
