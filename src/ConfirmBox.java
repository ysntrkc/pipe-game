import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
    static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinHeight(100);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        HBox hBoxForButtons = new HBox(10);
        hBoxForButtons.getChildren().addAll(yesButton, noButton);
        hBoxForButtons.setAlignment(Pos.CENTER);
        hBoxForButtons.setPadding(new Insets(5, 5, 5, 5));

        BorderPane pane = new BorderPane();
        pane.setCenter(label);
        pane.setBottom(hBoxForButtons);

        Scene scene = new Scene(pane);
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
