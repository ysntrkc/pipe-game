import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
    private static int answer;
    public static Button menuButton = new Button("Menu");

    public static int display(String title, String message) {
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
            answer = 1;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer = -1;
            window.close();
        });
        menuButton.setOnAction(e -> {
            answer = 0;
            window.close();
        });

        HBox hBoxForButtons = new HBox(10);
        hBoxForButtons.getChildren().addAll(yesButton, menuButton, noButton);
        hBoxForButtons.setAlignment(Pos.CENTER);
        hBoxForButtons.setPadding(new Insets(5, 5, 5, 5));

        BorderPane pane = new BorderPane();
        pane.setCenter(label);
        pane.setBottom(hBoxForButtons);

        Scene scene = new Scene(pane);
        window.getIcons().add(new Image("icons/exit_icon.png"));
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}