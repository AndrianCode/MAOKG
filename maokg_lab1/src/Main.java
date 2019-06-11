import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 475);

        Rectangle stick = new Rectangle(282, 230, 20, 100);
        root.getChildren().add(stick);

        Polyline sail = new Polyline(224, 230, 207, 168, 256, 109, 367, 109, 343, 170,
                372, 235, 224, 230);
        root.getChildren().add(sail);
        sail.setFill(Color.GRAY);
        sail.setStroke(Color.GRAY);

        Polyline board = new Polyline(105, 270, 180, 330, 410, 330, 475, 280, 400, 400,
                190, 400, 105, 270);
        root.getChildren().add(board);
        board.setFill(Color.rgb(134, 88, 27));
        board.setStroke(Color.rgb(134, 88, 27));

        scene.setFill(Color.rgb(237, 238, 109));
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
