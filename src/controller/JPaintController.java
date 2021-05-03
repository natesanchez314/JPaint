package controller;

import controller.command.RedoCommand;
import controller.command.UndoCommand;
import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final MouseHandler mouseHandler;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, MouseHandler mouseHandler) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.mouseHandler = mouseHandler;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, () -> {
            applicationState.setActiveStartAndEndPointMode();
            mouseHandler.updateState();
        });
        uiModule.addEvent(EventName.UNDO, () -> { new UndoCommand().run(); });
        uiModule.addEvent(EventName.REDO, () -> { new RedoCommand().run(); });
    }
}
