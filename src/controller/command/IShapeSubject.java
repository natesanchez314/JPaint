package controller.command;

import model.shape.IShapeObserver;

public interface IShapeSubject {
  void registerObserver(IShapeObserver observer);
  void deregisterOberver(IShapeObserver observer);
}
