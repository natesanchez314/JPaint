package controller.command;

public class UngroupCommand implements ICommand, IUndoable {

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
