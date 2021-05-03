package controller.MouseState;


import controller.Point;

import java.awt.Graphics2D;

public interface IState {
  void makeCommand(Graphics2D g, Point startPoint, Point endPoint);
}
