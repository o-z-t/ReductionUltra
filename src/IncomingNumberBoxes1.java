import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Random;


public class IncomingNumberBoxes1 extends Animation
{
  private static final int NUMBER_OF_BOXES = 10;
  private Random random = new Random();
  private Pane rootPane;
  private boolean collisionDetected;
  private boolean collisionDetected2;
  private Rectangle divisionLine;
  private ArrayList<NumberBox> numerators = new ArrayList<>();
  private ArrayList<NumberBox> denominators = new ArrayList<>();


  IncomingNumberBoxes1(Pane rootPane)
  {
    collisionDetected = false;
    collisionDetected2 = false;
    this.rootPane = rootPane;
    double scale = 30;
    divisionLine = new Rectangle(0, DRAW_HEIGHT/2+5, DRAW_WIDTH, 10);
    divisionLine.setFill(Color.RED);

    rootPane.getChildren().add(divisionLine);
    for (int i = 0; i < NUMBER_OF_BOXES; i++)
    {
      numerators.add(new NumberBox(scale));
      numerators.get(i).setLayoutY(0);
      denominators.add(new NumberBox(scale));
      denominators.get(i).setLayoutY(DRAW_HEIGHT - denominators.get(i).getBoxDimension());

      rootPane.getChildren().addAll(numerators.get(i), denominators.get(i));
    }
  }

  @Override
  public void handle(long now)
  {
    for (int i = 0; i < NUMBER_OF_BOXES; i++)
    {
      if (numerators.get(i).getLayoutX() > Animation.DRAW_WIDTH - numerators.get(i).getBoxDimension() || numerators.get(i).getLayoutX() < 0)
      {
        numerators.get(i).relocate(random.nextDouble()*Animation.DRAW_WIDTH - numerators.get(i).getBoxDimension(), 0);
      }

      if (denominators.get(i).getLayoutX() > Animation.DRAW_WIDTH - denominators.get(i).getBoxDimension() || denominators.get(i).getLayoutX() < 0)
      {
        denominators.get(i).relocate(random.nextDouble()*Animation.DRAW_WIDTH - denominators.get(i).getBoxDimension(), Animation.DRAW_HEIGHT);
      }

      if (!divisionLine.getBoundsInParent().intersects(numerators.get(i).getBoundsInParent()))
      {
        numerators.get(i).setLayoutY(numerators.get(i).getLayoutY() + numerators.get(i).getDY());
        numerators.get(i).setLayoutX(Math.sin(numerators.get(i).getLayoutY() / (2*Math.PI)) + numerators.get(i).getLayoutX());
      }

      if (!divisionLine.intersects(denominators.get(i).getBoundsInParent()))
      {
        denominators.get(i).setLayoutY(denominators.get(i).getLayoutY() - denominators.get(i).getDY());
        denominators.get(i).setLayoutX(Math.sin(denominators.get(i).getLayoutY() / (2*Math.PI)) + denominators.get(i).getLayoutX());
      }

      for (int j = 0; j < NUMBER_OF_BOXES; j++)
      {
        if (j != i)
        {
          collisionDetected = numerators.get(i).getBoundsInParent().intersects(numerators.get(j).getBoundsInParent());
          collisionDetected2 = denominators.get(i).getBoundsInParent().intersects(denominators.get(j).getBoundsInParent());
        }

        if (collisionDetected)
        {
          numerators.get(i).relocate(random.nextDouble()*Animation.DRAW_WIDTH, 0);
          break;
        }

        if (collisionDetected2)
        {
          denominators.get(i).relocate(random.nextDouble()*Animation.DRAW_WIDTH, DRAW_HEIGHT - denominators.get(i).getBoxDimension());
          break;
        }
      }
    }
  }


  public void restart()
  {
    this.rootPane.getChildren().clear();
  }

  private int gcd(int a, int b)
  {
    while (b > 0)
    {
      int rem = a%b;
      a = b;
      b = rem;
    }
    return a;
  }
}