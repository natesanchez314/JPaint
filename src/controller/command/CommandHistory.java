package controller.command;

import controller.shape.IShape;
import controller.shape.SelectedShapeOutline;

import java.awt.*;
import java.util.Stack;

public final class CommandHistory {
	private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
	private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();
	private static final Stack<IShape> shapeList = new Stack<IShape>();
	private static final Stack<SelectedShapeOutline> selectedShapes = new Stack<>();
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
	public static void removeShape(IShape shape) {
		shapeList.remove(shape);
	}

	public static Stack<IShape> getShapeList() {
		return shapeList;
	}
	public static Stack<SelectedShapeOutline> getSelectedShapes() {
		return selectedShapes;
	}
	public static void selectShape(SelectedShapeOutline shape) {
		selectedShapes.push(shape);
	}
	public static void deselectShapes() {
		selectedShapes.clear();
	}

	public static Stack<IShape> getClipBoard() { return clipBoard; }
	public static void copyShape(IShape shape) { clipBoard.push(shape); }
	public static void clearClipBoard() { clipBoard.clear(); }

	public static void redrawAll() {
		if (!shapeList.isEmpty()) {
			Graphics2D g = shapeList.peek().getGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, (int) g.getDeviceConfiguration().getBounds().getWidth(), (int) g.getDeviceConfiguration().getBounds().getHeight());
			for (IShape shape : shapeList) {
				shape.draw();
			}
			g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
			if (!selectedShapes.isEmpty()) {
				for (SelectedShapeOutline shape : selectedShapes) {
					shape.draw();
				}
			}
			g.setStroke(new BasicStroke());
		}
	}
}
