package command;

import java.util.Stack;

public final class CommandHistory {
	private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
	private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();

	public static void add(IUndoable cmd) {
		undoStack.push(cmd);
		redoStack.clear();
		printStacks();
	}
	
	public static boolean undo() {
		boolean result = !undoStack.empty();
		if (result) {
			IUndoable c = undoStack.pop();
			redoStack.push(c);
			c.undo();
		}
		printStacks();

		return result;
	}

	public static boolean redo() {
		boolean result = !redoStack.empty();
		if (result) {
			IUndoable c = redoStack.pop();
			undoStack.push(c);
			c.redo();
		}
		printStacks();

		return result;
	}

	private static void printStacks() {
		System.out.println("Undo Stack: " + undoStack);
		System.out.println("Redo Stack: " + redoStack);
	}
}
