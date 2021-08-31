import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


public class IncomingBoxes extends Animation
{
  private static final Color INACTIVE_COLOR = Color.DEEPSKYBLUE;
  private static final Color ACTIVE_COLOR = Color.DEEPPINK;
  private static final int NUMBER_OF_BOXES = 12;
  private static final double BOX_DIMENSION = Animation.DRAW_WIDTH/NUMBER_OF_BOXES;
  private Pane rootPane;
  private Rectangle divisionLine;

  private Rectangle[] numeratorRects = new Rectangle[NUMBER_OF_BOXES];
  private Rectangle[] denominatorRects = new Rectangle[NUMBER_OF_BOXES];
  private int[] numeratorStep = new int[numeratorRects.length];
  private int[] denominatorStep = new int[denominatorRects.length];


  public IncomingBoxes(Pane rootPane)
  {
    this.rootPane = rootPane;
    divisionLine = new Rectangle(0, DRAW_HEIGHT/2+5, DRAW_WIDTH, 10);
    divisionLine.setFill(Color.RED);
    rootPane.getChildren().add(divisionLine);

    for (int i = 0; i < NUMBER_OF_BOXES; i++)
    {
      numeratorStep[i] = 1;
      numeratorRects[i] = new Rectangle(BOX_DIMENSION, BOX_DIMENSION);
      numeratorRects[i].setFill(INACTIVE_COLOR);

      denominatorStep[i] = 1;
      denominatorRects[i] = new Rectangle(BOX_DIMENSION, BOX_DIMENSION);
      denominatorRects[i].setFill(INACTIVE_COLOR);

      rootPane.getChildren().addAll(numeratorRects[i], denominatorRects[i]);
    }
  }

  @Override
  public void handle(long now)
  {
    for (int i = 0; i < NUMBER_OF_BOXES; i++)
    {
      if(!divisionLine.intersects(numeratorRects[i].getBoundsInParent())) numeratorStep[i]++;
      numeratorRects[i].setLayoutX(i*(BOX_DIMENSION));
      numeratorRects[i].setLayoutY(0.1*numeratorStep[i]*(i + 1));
      numeratorRects[i].setOnMouseClicked(new EventHandler<MouseEvent>()
      {
        @Override
        public void handle(MouseEvent event)
        {
          Rectangle selectedRect = (Rectangle)event.getSource();
          if (selectedRect.getFill() == INACTIVE_COLOR) selectedRect.setFill(ACTIVE_COLOR);
          else if (selectedRect.getFill() == ACTIVE_COLOR) selectedRect.setFill(INACTIVE_COLOR);
        }
      });

      if(!divisionLine.intersects(denominatorRects[i].getBoundsInParent())) denominatorStep[i]++;
      denominatorRects[i].setLayoutX(i*(BOX_DIMENSION));
      denominatorRects[i].setLayoutY(DRAW_HEIGHT - BOX_DIMENSION -(0.1*denominatorStep[i]*(i + 1)));
      denominatorRects[i].setOnMouseClicked(new EventHandler<MouseEvent>()
      {
        @Override
        public void handle(MouseEvent event)
        {
          Rectangle selectedRect = (Rectangle)event.getSource();
          if (selectedRect.getFill() == INACTIVE_COLOR) selectedRect.setFill(ACTIVE_COLOR);
          else if (selectedRect.getFill() == ACTIVE_COLOR) selectedRect.setFill(INACTIVE_COLOR);
        }
      });

    }
  }

  public class NumberBox extends Rectangle
  {
    private NumberBox()
    {

    }
  }
}
