package model;

import java.awt.*;

public enum ShapeColor {
    BLACK,
    BLUE,
    CYAN,
    DARK_GRAY,
    GRAY,
    GREEN,
    LIGHT_GRAY,
    MAGENTA,
    ORANGE,
    PINK,
    RED,
    WHITE,
    YELLOW;

    public Color getColor() {
        return switch (this) {
            case BLACK -> Color.BLACK;
            //case BLUE -> Color.BLUE;
            case CYAN -> Color.CYAN;
            case DARK_GRAY -> Color.DARK_GRAY;
            case GRAY -> Color.GRAY;
            case GREEN -> Color.GREEN;
            case LIGHT_GRAY -> Color.LIGHT_GRAY;
            case MAGENTA -> Color.MAGENTA;
            case ORANGE -> Color.ORANGE;
            case PINK -> Color.PINK;
            case RED -> Color.RED;
            case WHITE -> Color.WHITE;
            case YELLOW -> Color.YELLOW;
            default -> Color.BLUE;
        };
    }
}
