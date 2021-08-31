import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainFrame extends Application implements EventHandler<ActionEvent>
{
  static final double WINDOW_WIDTH = 1800;
  static final double WINDOW_HEIGHT = 900;
  static final double DEFAULT_BUTTON_WIDTH = WINDOW_WIDTH/4;
  static final double DEFAULT_BUTTON_HEIGHT = WINDOW_HEIGHT/8;

  private Button buttonMainMenuToPlay; //activated
  private Button buttonMainMenuToInstructions; //activated
  private Button buttonMainMenuToQuit; //activated

  private Pane rootPane;
  private IncomingNumberBoxes1 animation;
  private Canvas canvasPlay;
  private Button buttonPlayToMainMenu; //activated
  private Button buttonPlayToRestart; // activated
  private Button buttonPlayToQuit;//activated

  private Button buttonInstructionsToPlay; //activated
  private Button buttonInstructionsToMainMenu; //activated
  private Button buttonInstructionsToQuit; //activated

  private Scene sceneMainMenu;
  private Scene sceneInstructions;
  private Scene scenePlay;

  private Stage mainStage;


  @Override
  public void start(Stage mainStage)
  {
    this.mainStage = mainStage;
    mainStage.setTitle("One");
    makeMainMenuScene();
    makeInstructionsScene();
    makePlayScene();
    mainStage.setScene(sceneMainMenu);
    mainStage.show();
  }

  private void makeMainMenuScene()
  {
    VBox vBoxMainMenu = new VBox(10);
    HBox hBoxMainMenuButtons = new HBox(10);

    Image imageLogo = new Image("Something.png");
    ImageView imageView = new ImageView();
    imageView.setImage(imageLogo);

    buttonMainMenuToPlay = new Button("Play");
    buttonMainMenuToPlay.setOnAction(this);
    buttonMainMenuToPlay.setMaxSize(DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT);

    buttonMainMenuToInstructions = new Button("Instructions");
    buttonMainMenuToInstructions.setOnAction(this);
    buttonMainMenuToInstructions.setMaxSize(DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT);

    buttonMainMenuToQuit = new Button("Quit");
    buttonMainMenuToQuit.setOnAction(this);
    buttonMainMenuToQuit.setMaxSize(DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT);

    hBoxMainMenuButtons.getChildren().addAll(buttonMainMenuToPlay, buttonMainMenuToInstructions, buttonMainMenuToQuit);
    hBoxMainMenuButtons.setAlignment(Pos.CENTER);

    vBoxMainMenu.getChildren().addAll(imageView, hBoxMainMenuButtons);
    vBoxMainMenu.setAlignment(Pos.CENTER);

    sceneMainMenu = new Scene(vBoxMainMenu, WINDOW_WIDTH, WINDOW_HEIGHT);
  }

  private void makePlayScene()
  {
    VBox vBoxPlayScene = new VBox(10);
    HBox hBoxPlaySceneButtons = new HBox(10);


    rootPane = new Pane();
    rootPane.setMinSize(Animation.DRAW_WIDTH, Animation.DRAW_HEIGHT);
    rootPane.setMaxSize(Animation.DRAW_WIDTH, Animation.DRAW_HEIGHT);
    rootPane.setStyle("-fx-background-color: black;");

    buttonPlayToRestart = new Button("Restart");
    buttonPlayToRestart.setOnAction(this);
    buttonPlayToRestart.setMaxSize(DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT);

    buttonPlayToMainMenu = new Button("Main Menu");
    buttonPlayToMainMenu.setOnAction(this);
    buttonPlayToMainMenu.setMaxSize(DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT);

    buttonPlayToQuit = new Button("Quit");
    buttonPlayToQuit.setOnAction(this);
    buttonPlayToQuit.setMaxSize(DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT);

    hBoxPlaySceneButtons.getChildren().addAll(buttonPlayToRestart, buttonPlayToMainMenu, buttonPlayToQuit);
    hBoxPlaySceneButtons.setAlignment(Pos.CENTER);

    vBoxPlayScene.getChildren().addAll(rootPane,hBoxPlaySceneButtons);
    vBoxPlayScene.setAlignment(Pos.CENTER);

    scenePlay = new Scene(vBoxPlayScene, WINDOW_WIDTH, WINDOW_HEIGHT);
  }

  private void makeInstructionsScene()
  {
    VBox vBoxInstructions = new VBox(10);

    HBox hBoxInstructionsButtons = new HBox(10);

    Text textInstructions = new Text("Instructions");

    buttonInstructionsToPlay = new Button("Play");
    buttonInstructionsToPlay.setOnAction(this);
    buttonInstructionsToPlay.setMaxSize(DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT);

    buttonInstructionsToMainMenu = new Button("Main Menu");
    buttonInstructionsToMainMenu.setOnAction(this);
    buttonInstructionsToMainMenu.setMaxSize(DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT);

    buttonInstructionsToQuit = new Button("Quit");
    buttonInstructionsToQuit.setOnAction(this);
    buttonInstructionsToQuit.setMaxSize(DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT);

    hBoxInstructionsButtons.getChildren().addAll(buttonInstructionsToPlay, buttonInstructionsToQuit, buttonInstructionsToMainMenu);
    hBoxInstructionsButtons.setAlignment(Pos.CENTER);

    vBoxInstructions.getChildren().addAll(textInstructions, hBoxInstructionsButtons);
    vBoxInstructions.setAlignment(Pos.CENTER);

    sceneInstructions = new Scene(vBoxInstructions, WINDOW_WIDTH, WINDOW_HEIGHT);
  }

  @Override
  public void handle(ActionEvent event)
  {
    Object source = event.getSource();

    if (source == buttonMainMenuToPlay || source == buttonInstructionsToPlay || source == buttonPlayToRestart)
    {
      mainStage.setScene(scenePlay);
      animation = new IncomingNumberBoxes1(rootPane);
      animation.start();
    }

    if (source == buttonPlayToRestart)
    {
      mainStage.setScene(scenePlay);
      animation.restart();
      animation = new IncomingNumberBoxes1(rootPane);
      animation.start();
      System.out.println("game restarted");
    }

    if (source == buttonMainMenuToInstructions)
    {
      mainStage.setScene(sceneInstructions);
    }

    if (source == buttonInstructionsToMainMenu || source == buttonPlayToMainMenu)
    {
      mainStage.setScene(sceneMainMenu);
      rootPane.getChildren().clear();
    }

    if (source == buttonMainMenuToQuit || source == buttonPlayToQuit || source == buttonInstructionsToQuit)
    {
      System.exit(0);
    }
  }


  public static void main(String[] args)
  {
    launch(args);
  }
}

