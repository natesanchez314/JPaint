package controller;

import controller.command.CommandHistory;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class FrameHandler implements ComponentListener {

  @Override
  public void componentResized(ComponentEvent e) {
    CommandHistory.redrawAll();
  }

  @Override
  public void componentMoved(ComponentEvent e) {
    CommandHistory.redrawAll();
  }

  @Override
  public void componentShown(ComponentEvent e) {
    CommandHistory.redrawAll();
  }

  @Override
  public void componentHidden(ComponentEvent e) {

  }
}
