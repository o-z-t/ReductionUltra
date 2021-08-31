import javafx.animation.AnimationTimer;

abstract class Animation extends AnimationTimer
{
  static final double DRAW_WIDTH = MainFrame.WINDOW_WIDTH - MainFrame.DEFAULT_BUTTON_HEIGHT;
  static final double DRAW_HEIGHT = MainFrame.WINDOW_HEIGHT - MainFrame.DEFAULT_BUTTON_HEIGHT;

  abstract public void handle(long now);
}
