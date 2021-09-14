package ReductionUltra;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ReductionUltra extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Factor A = new Factor(200);
        Factor B = new Factor(200);

        Text factorA = new Text("Factor A: " + A.getCurrentValue());
        Text factorB = new Text(" Factor B: " + B.getCurrentValue());
        TextField redx = new TextField();

        Button reductionUltra =  new Button();
        reductionUltra.setText("Reduction Ultra!");
        reductionUltra.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                Reducer.reduce(A, B, Integer.getInteger(redx.getText()));
                factorA.setText("Factor A: " + A.getCurrentValue());
                factorB.setText(" Factor B: " + B.getCurrentValue());
            }
        });

        VBox root = new VBox();
        root.getChildren().addAll(factorA, factorB, redx, reductionUltra);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
