package main;

import controller.IJPaintController;
import controller.JPaintController;
import controller.MouseHandler;
import controller.command.CommandHistory;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;


public class Main {
    public static void main(String[] args){
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        MouseHandler mh = new MouseHandler(paintCanvas.getGraphics2D(), appState);
        paintCanvas.addMouseListener(mh);
        IJPaintController controller = new JPaintController(uiModule, appState, mh);
        controller.setup();
    }
}
