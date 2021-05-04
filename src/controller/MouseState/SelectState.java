package controller.MouseState;

import controller.command.SelectCommand;

import java.awt.Graphics2D;

public class SelectState implements IState {

  @Override
  public void makeCommand(Graphics2D g, controller.Point startPoint, controller.Point endPoint) {
    new SelectCommand(g, startPoint, endPoint).run();
  }
}
