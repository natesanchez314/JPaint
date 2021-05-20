package controller.command;

public class GroupCommand implements ICommand, IUndoable {

  @Override
  public void run() {
    System.out.println("Group");
    CommandHistory.add(this);
  }

  @Override
  public void redo() {

  }

  @Override
  public void undo() {

  }
}
