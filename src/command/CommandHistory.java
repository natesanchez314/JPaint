package command;

import model.shape.IShape;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public final class CommandHistory {
	private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
	private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();
	private static final List<IShape> shapeList = new ArrayList<>();

	public static void add(IUndoable cmd) {
		undoStack.push(cmd);
		redoStack.clear();
		printStacks();
	}

	public static void addShape(IShape shape) {
		shapeList.add(shape);
	}
	
	public static boolean undo() {
		boolean result = !undoStack.empty();
		if (result) {
			IUndoable c = undoStack.pop();
			redoStack.push(c);
			c.undo();
		}
		//printStacks();
		return result;
	}

	public static boolean redo() {
		boolean result = !redoStack.empty();
		if (result) {
			IUndoable c = redoStack.pop();
			undoStack.push(c);
			c.redo();
		}
		//printStacks();
		return result;
	}

	public static void undoRedraw() {
		Iterator<IUndoable> undoIter = CommandHistory.getUndoStack().iterator();
		while (undoIter.hasNext()) {
			IUndoable undoableCmd = undoIter.next();
			undoableCmd.undo();
		}
	}

	private static void printStacks() {
		System.out.println("Undo Stack: " + undoStack);
		System.out.println("Redo Stack: " + redoStack);
	}

	public static Stack<IUndoable> getUndoStack() {
		return undoStack;
	}

	public static Stack<IUndoable> getRedoStack() {
		return redoStack;
	}
}
