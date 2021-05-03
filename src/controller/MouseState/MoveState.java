package controller.MouseState;

import controller.command.MoveCommand;

import java.awt.*;

public class MoveState implements IState {

  @Override
  public void makeCommand(Graphics2D g, controller.Point startPoint, controller.Point endPoint) {
    new MoveCommand(g, startPoint, endPoint).run();
  }
}
