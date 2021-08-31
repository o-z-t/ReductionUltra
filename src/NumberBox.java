import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Random;

class NumberBox extends StackPane
{
  private static final Color INACTIVE_COLOR = Color.DEEPSKYBLUE;
  private static final Color ACTIVE_COLOR = Color.DEEPPINK;
  private int factor;
  private double boxDimension;
  private double dX;
  private double dY;
  private double xNaught;
  private double yNaught;
  private boolean isSelected = false;
  private Text textFactor;
  private Rectangle box;

  NumberBox(double scale)
  {
    Random random = new Random();
    boxDimension = Animation.DRAW_WIDTH/scale;
    xNaught = random.nextDouble()*(Animation.DRAW_WIDTH - boxDimension);
    dX = random.nextDouble()*0.5 + 0.1;
    dY = random.nextDouble()*0.5 + 0.1;
    factor = random.nextInt(50) + 2;
    textFactor = new Text("" + factor);
    textFactor.setFont(Font.font(boxDimension / 2));
    textFactor.setFill(Color.BLACK);
    box = new Rectangle(boxDimension, boxDimension);
    box.setFill(INACTIVE_COLOR);

    super.setPrefSize(boxDimension, boxDimension);
    super.setLayoutX(xNaught);
    super.getChildren().addAll(box, textFactor);
    super.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      @Override
      public void handle(MouseEvent event)
      {
        isSelected = !isSelected;

        if(isSelected)
        {
          box.setFill(ACTIVE_COLOR);
          textFactor.setFill(Color.WHITE);
        }

        else
        {
          box.setFill(INACTIVE_COLOR);
          textFactor.setFill(Color.BLACK);
        }
      }
    });
  }

  public double getBoxDimension()
  {
    return boxDimension;
  }

  public double getDX()
  {
    return dX;
  }

  public void setDY(double dY)
  {
    this.dY = dY;
  }
  public double getDY()
  {
    return dY;
  }

  public boolean isSelected()
  {
    return isSelected;
  }

  public int getFactor()
  {
    return factor;
  }

  public void setFactor(int factor)
  {
    this.factor = factor;
  }
}