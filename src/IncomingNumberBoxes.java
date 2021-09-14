import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;


public class IncomingNumberBoxes extends Animation
{
  private static final Color INACTIVE_COLOR = Color.DEEPSKYBLUE;
  private static final Color ACTIVE_COLOR = Color.DEEPPINK;
  private static final int NUMBER_OF_BOXES = 12;
  private static final double BOX_DIMENSION = Animation.DRAW_WIDTH/NUMBER_OF_BOXES;
  private Pane rootPane;
  private Rectangle divisionLine;

  private Text[] textNumerators = new Text[NUMBER_OF_BOXES];
  private Text[] textDenominators = new Text[NUMBER_OF_BOXES];

  private StackPane[] numberBoxNumerators = new StackPane[NUMBER_OF_BOXES];
  private StackPane[] numberBoxDenominators = new StackPane[NUMBER_OF_BOXES];

  private Rectangle[] numeratorRects = new Rectangle[NUMBER_OF_BOXES];
  private Rectangle[] denominatorRects = new Rectangle[NUMBER_OF_BOXES];

  private int[] numeratorStep = new int[numeratorRects.length];
  private int[] denominatorStep = new int[denominatorRects.length];

  private ArrayList<Integer> numeratorFactors = new ArrayList<>();
  private ArrayList<Integer> denominatorFactors = new ArrayList<>();


  private Random random = new Random();


  public IncomingNumberBoxes(Pane rootPane)
  {
    this.rootPane = rootPane;
    divisionLine = new Rectangle(0, DRAW_HEIGHT/2+5, DRAW_WIDTH, 10);
    divisionLine.setFill(Color.RED);
    rootPane.getChildren().add(divisionLine);

    for (int i = 0; i < NUMBER_OF_BOXES; i++)
    {
      textNumerators[i] = new Text("" + (random.nextInt(50) + 2));
      textNumerators[i].setFill(Color.BLACK);
      textNumerators[i].setFont(Font.font(BOX_DIMENSION/2));

      numeratorStep[i] = 1;

      numeratorRects[i] = new Rectangle(BOX_DIMENSION, BOX_DIMENSION);
      numeratorRects[i].setFill(INACTIVE_COLOR);

      numberBoxNumerators[i] = new StackPane();
      numberBoxNumerators[i].setPrefSize(BOX_DIMENSION,BOX_DIMENSION);
      numberBoxNumerators[i].getChildren().addAll(numeratorRects[i], textNumerators[i]);



      textDenominators[i] = new Text("" + (random.nextInt(50) + 2));
      textDenominators[i].setFill(Color.BLACK);
      textDenominators[i].setFont(Font.font(BOX_DIMENSION/2));

      denominatorStep[i] = 1;

      denominatorRects[i] = new Rectangle(BOX_DIMENSION, BOX_DIMENSION);
      denominatorRects[i].setFill(INACTIVE_COLOR);

      numberBoxDenominators[i] = new StackPane();
      numberBoxDenominators[i].setPrefSize(BOX_DIMENSION,BOX_DIMENSION);
      numberBoxDenominators[i].getChildren().addAll(denominatorRects[i], textDenominators[i]);

      rootPane.getChildren().addAll(numberBoxNumerators[i], numberBoxDenominators[i]);
    }
  }

  @Override
  public void handle(long now)
  {
    for (int i = 0; i < NUMBER_OF_BOXES; i++)
    {
      if(!divisionLine.intersects(numberBoxNumerators[i].getBoundsInParent())) numeratorStep[i]++;
      numberBoxNumerators[i].setLayoutX(i*(BOX_DIMENSION));
      numberBoxNumerators[i].setLayoutY(0.1*numeratorStep[i]*(i + 1));
      numberBoxNumerators[i].setOnMouseClicked(new EventHandler<MouseEvent>()
      {
        @Override
        public void handle(MouseEvent event)
        {
          StackPane selectedNumeratorPane = (StackPane)event.getSource(); // the StackPane that has been clicked
          Rectangle selectedNumeratorRect = (Rectangle)selectedNumeratorPane.getChildren().get(0); // the corresponding box
          int selectedNumeratorFactor = Integer.parseInt(((Text)selectedNumeratorPane.getChildren().get(1)).getText()); // the int inside the box

          if (selectedNumeratorRect.getFill() == INACTIVE_COLOR)
          {
            selectedNumeratorRect.setFill(ACTIVE_COLOR);
            numeratorFactors.add(selectedNumeratorFactor);
          }
          else if (selectedNumeratorRect.getFill() == ACTIVE_COLOR)
          {
            selectedNumeratorRect.setFill(INACTIVE_COLOR);
            numeratorFactors.remove(numeratorFactors.indexOf(selectedNumeratorFactor));
          }

          if (!numeratorFactors.isEmpty() && !denominatorFactors.isEmpty())
          {
            updateReduction(numeratorFactors,denominatorFactors);
          }

          System.out.println("Numerator Factors: " + numeratorFactors.toString());
          System.out.println("Denominator Factors: " + denominatorFactors.toString());

        }
      });


      if(!divisionLine.intersects(numberBoxDenominators[i].getBoundsInParent())) denominatorStep[i]++;
      numberBoxDenominators[i].setLayoutX(i*(BOX_DIMENSION));
      numberBoxDenominators[i].setLayoutY(DRAW_HEIGHT - BOX_DIMENSION -(0.1*denominatorStep[i]*(i + 1)));
      numberBoxDenominators[i].setOnMouseClicked(new EventHandler<MouseEvent>()
      {
        @Override
        public void handle(MouseEvent event)
        {
          StackPane clickedDenominatorPane = (StackPane) event.getSource();
          Rectangle clickedDenominatorRect = (Rectangle)clickedDenominatorPane.getChildren().get(0);
          int clickedDenominatorFactor = Integer.parseInt(((Text)clickedDenominatorPane.getChildren().get(1)).getText()); // the int inside the box

          if (clickedDenominatorRect.getFill() == INACTIVE_COLOR)
          {
            clickedDenominatorRect.setFill(ACTIVE_COLOR);
            denominatorFactors.add(clickedDenominatorFactor);
          }
          else if (clickedDenominatorRect.getFill() == ACTIVE_COLOR)
          {
            clickedDenominatorRect.setFill(INACTIVE_COLOR);
            denominatorFactors.remove(clickedDenominatorFactor);
          }

          if (!numeratorFactors.isEmpty() && !denominatorFactors.isEmpty())
          {
            updateReduction(numeratorFactors,denominatorFactors);
          }

          System.out.println("Numerator Factors: " + numeratorFactors.toString());
          System.out.println("Denominator Factors: " + denominatorFactors.toString());
        }
      });

    }
  }

  private void updateReduction(ArrayList<Integer> numeratorFactors, ArrayList<Integer> denominatorFactors)
  {
    int a = numeratorFactors.get(0);
    int b = denominatorFactors.get(0);
    numeratorFactors.remove(0);
    numeratorFactors.add(a/gcd(a,b));
    denominatorFactors.remove(0);
    denominatorFactors.add(b/gcd(a,b));
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

  public class NumberBox extends Rectangle
  {
    private NumberBox()
    {

    }
  }
}
