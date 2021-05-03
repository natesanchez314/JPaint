package controller.command;

import controller.shape.IShapeObserver;

public interface IShapeSubject {
  void registerObserver(IShapeObserver observer);
  void deregisterOberver(IShapeObserver observer);
}
