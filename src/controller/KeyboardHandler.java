package controller;

import model.persistence.ApplicationState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener {

  private final ApplicationState appState;

  public KeyboardHandler(ApplicationState _appState) {
    appState = _appState;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    System.out.println(e);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    System.out.println(e);
  }

  @Override
  public void keyReleased(KeyEvent e) {
    System.out.println(e);
  }
}
