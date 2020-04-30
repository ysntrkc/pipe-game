import java.io.File;
import java.util.Scanner;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Main extends Application {
    PathTransition pathOne, pathTwo;

    Tiles[] tiles_level1 = new Tiles[16];
    Tiles[] tiles_level2 = new Tiles[16];
    Tiles[] tiles_level3 = new Tiles[16];
    Tiles[] tiles_level4 = new Tiles[16];
    Tiles[] tiles_level5 = new Tiles[16];

    String level1, level2, level3, level4, level5;
    String current_level;

    Circle circle;

    Stage window;
    Scene scene, start_scene;
    Pane pane = new Pane();

    Label step_counter_label = new Label();
    Label total_counter_label = new Label();
    BorderPane borderPane = new BorderPane();
    Pane start_pane = new Pane();
    BorderPane paneForStepCounters = new BorderPane();
    HBox hBoxForButtons = new HBox(25);
    VBox vBoxForButtons = new VBox(10);
    Button level1_button, level2_button, level3_button, level4_button, level5_button;
    Button start_button, credit_button, quit_button;
    Text step_counter_text = new Text("");
    Text total_counter_text = new Text("");
    Text win_text = new Text("");

    GridPane gridPane1;
    GridPane gridPane2;
    GridPane gridPane3;
    GridPane gridPane4;
    GridPane gridPane5;

    int steps_counter = 0;
    int total_counter = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        level1 = "src/levels/level1.txt";
        level2 = "src/levels/level2.txt";
        level3 = "src/levels/level3.txt";
        level4 = "src/levels/level4.txt";
        level5 = "src/levels/level5.txt";

        path_maker(level1);
        current_level = level1;

        gridPane1 = map_maker(level1, tiles_level1);
        gridPane2 = map_maker(level2, tiles_level2);
        gridPane3 = map_maker(level3, tiles_level3);
        gridPane4 = map_maker(level4, tiles_level4);
        gridPane5 = map_maker(level5, tiles_level5);

        level1_button = new Button("Level 1");
        level2_button = new Button("Level 2");
        level3_button = new Button("Level 3");
        level4_button = new Button("Level 4");
        level5_button = new Button("Level 5");
        level2_button.setDisable(true);
        level3_button.setDisable(true);
        level4_button.setDisable(true);
        level5_button.setDisable(true);
        level1_button.setOnAction(e -> button_handler(level1, tiles_level1));
        level2_button.setOnAction(e -> button_handler(level2, tiles_level2));
        level3_button.setOnAction(e -> button_handler(level3, tiles_level3));
        level4_button.setOnAction(e -> button_handler(level4, tiles_level4));
        level5_button.setOnAction(e -> button_handler(level5, tiles_level5));
        hBoxForButtons.setPadding(new Insets(2, 10, 2, 10));
        hBoxForButtons.getChildren().addAll(level1_button, level2_button, level3_button, level4_button, level5_button);

        start_button = new Button("Start");
        credit_button = new Button("Credits");
        quit_button = new Button("Quit");

        start_button.setPrefSize(96, 48);
        credit_button.setPrefSize(96, 48);
        quit_button.setPrefSize(96, 48);

        vBoxForButtons.getChildren().addAll(start_button, credit_button, quit_button);

        start_button.setOnAction(e -> button_handler(level1, tiles_level1));
        quit_button.setOnAction(e -> closeProgram());

        start_pane.setStyle("-fx-background-color: #5e0000");
        start_pane.getChildren().addAll(start_button, credit_button, quit_button);
        start_button.relocate(145.5,135);
        credit_button.relocate(145.5,193);
        quit_button.relocate(145.5,251);

        start_scene = new Scene(start_pane, 387, 434, Color.BLACK);
        scene = new Scene(borderPane, 387, 434, Color.BLACK);

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        window.setTitle("Game");
        window.setScene(start_scene);
        window.setResizable(false);
        window.show();
    }

    public void closeProgram() {
        boolean answer = ConfirmBox.display("Exit", "Do you really want to close the game?");
        if (answer)
            window.close();
    }

    public void button_handler(String level, Tiles[] tiles_level) {
        steps_counter = 0;
        win_text.setText("");
        current_level = level;
        path_maker(level);

        GridPane gridPaneForHandler = new GridPane();

        step_counter_text.setText("Move Count: " + steps_counter);
        total_counter_text.setText("Total Move Count: " + total_counter);
        step_counter_text.setFill(Color.WHITE);
        total_counter_text.setFill(Color.WHITE);
        win_text.setFill(Color.WHITE);

        paneForStepCounters.setLeft(step_counter_text);
        paneForStepCounters.setRight(total_counter_text);
        paneForStepCounters.setCenter(win_text);

        try {
            gridPaneForHandler = map_maker(level, tiles_level);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().addAll(gridPaneForHandler, circle);

        borderPane.getChildren().clear();
        borderPane.setStyle("-fx-background-color: #5e0000");
        borderPane.setCenter(pane);
        borderPane.setTop(hBoxForButtons);
        borderPane.setBottom(paneForStepCounters);

        window.setScene(scene);
    }

    public void path_maker(String level) {
        circle = new Circle(48, 48, 15, Color.YELLOW);

        if (level.equals(level1) || level.equals(level2) || level.equals(level3)) {
            Polyline path1 = new Polyline(48, 48, 48, 339, 339, 339);
            pathOne = new PathTransition();
            pathOne.setDuration(Duration.millis(4000));
            pathOne.setPath(path1);
            pathOne.setNode(circle);
            pathOne.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathOne.setCycleCount(1);
            pathOne.setAutoReverse(false);
        } else if (level.equals(level4) || level.equals(level5)) {
            Polyline path2 = new Polyline(48, 48, 48, 242, 339, 242, 339, 145);
            pathTwo = new PathTransition();
            pathTwo.setDuration(Duration.millis(4000));
            pathTwo.setPath(path2);
            pathTwo.setNode(circle);
            pathTwo.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTwo.setCycleCount(1);
            pathTwo.setAutoReverse(false);
        }
    }

    public void animation_play(String level) {
        if (level.equals(level1) || level.equals(level2) || level.equals(level3)) {
            pathOne.play();
        } else if (level.equals(level4) || level.equals(level5)) {
            pathTwo.play();
        }
    }

    public void move_cell(int cell_index, GridPane gridPane, Tiles[] tiles) {
        tiles[cell_index].getTile_image().setOnMouseDragged(e -> {
            if (tiles[cell_index].getType().equals("Pipe")
                    || (tiles[cell_index].getType().equals("Empty") && tiles[cell_index].getProperty().equals("none"))) {
                if (e.getX() - tiles[cell_index].getTile_image().getX() > 96 && tiles[cell_index].can_move_right(cell_index, tiles)) {
                    if (isWon(tiles)) {
                        System.out.println("Level is already done!");
                    } else {
                        GridPane.setColumnIndex(tiles[cell_index + 1].getTile_image(), GridPane.getColumnIndex(tiles[cell_index + 1].getTile_image()) - 1);
                        GridPane.setColumnIndex(tiles[cell_index].getTile_image(), GridPane.getColumnIndex(tiles[cell_index].getTile_image()) + 1);

                        Tiles current = tiles[cell_index];
                        Tiles next = tiles[cell_index + 1];

                        tiles[cell_index + 1] = current;
                        step_controller(cell_index, tiles, next);
                    }
                } else if (e.getX() - tiles[cell_index].getTile_image().getX() < 0 && tiles[cell_index].can_move_left(cell_index, tiles)) {
                    if (isWon(tiles)) {
                        System.out.println("Level is already done!");
                    } else {
                        GridPane.setColumnIndex(tiles[cell_index - 1].getTile_image(), GridPane.getColumnIndex(tiles[cell_index - 1].getTile_image()) + 1);
                        GridPane.setColumnIndex(tiles[cell_index].getTile_image(), GridPane.getColumnIndex(tiles[cell_index].getTile_image()) - 1);

                        Tiles current = tiles[cell_index];
                        Tiles next = tiles[cell_index - 1];

                        tiles[cell_index - 1] = current;
                        step_controller(cell_index, tiles, next);
                    }

                } else if (e.getY() - tiles[cell_index].getTile_image().getY() > 96 && tiles[cell_index].can_move_down(cell_index, tiles)) {
                    if (isWon(tiles)) {
                        System.out.println("Level is already done!");
                    } else {
                        GridPane.setRowIndex(tiles[cell_index + 4].getTile_image(), GridPane.getRowIndex(tiles[cell_index + 4].getTile_image()) - 1);
                        GridPane.setRowIndex(tiles[cell_index].getTile_image(), GridPane.getRowIndex(tiles[cell_index].getTile_image()) + 1);

                        Tiles current = tiles[cell_index];
                        Tiles next = tiles[cell_index + 4];

                        tiles[cell_index + 4] = current;
                        step_controller(cell_index, tiles, next);
                    }
                } else if (e.getY() - tiles[cell_index].getTile_image().getY() < 0 && tiles[cell_index].can_move_up(cell_index, tiles)) {
                    if (isWon(tiles)) {
                        System.out.println("Level is already done!");
                    } else {
                        GridPane.setRowIndex(tiles[cell_index - 4].getTile_image(), GridPane.getRowIndex(tiles[cell_index - 4].getTile_image()) + 1);
                        GridPane.setRowIndex(tiles[cell_index].getTile_image(), GridPane.getRowIndex(tiles[cell_index].getTile_image()) - 1);

                        Tiles current = tiles[cell_index];
                        Tiles next = tiles[cell_index - 4];

                        tiles[cell_index - 4] = current;
                        step_controller(cell_index, tiles, next);
                    }
                }
            }
        });
    }

    public void step_controller(int cell_index, Tiles[] tiles, Tiles next) {
        tiles[cell_index] = next;
        if (isWon(tiles)) {
            animation_play(current_level);
            win_text.setText("You Win");
        }
        if (isWon(tiles_level1)) {
            level2_button.setDisable(false);
            if (isWon(tiles_level2)) {
                level3_button.setDisable(false);
                if (isWon(tiles_level3)) {
                    level4_button.setDisable(false);
                    if (isWon(tiles_level4)) {
                        level5_button.setDisable(false);
                    }
                }
            }
        }
        steps_counter++;
        total_counter++;
        step_counter_text.setText("Move Count: " + steps_counter);
        total_counter_text.setText("Total Move Count: " + total_counter);
    }

    public GridPane map_maker(String level, Tiles[] tiles) throws Exception {
        GridPane gridMap = new GridPane();
        gridMap.setStyle("-fx-background-color: black");
        gridMap.setHgap(1);
        gridMap.setVgap(1);
        int tile_count = 0;

        File file = new File(level);

        Scanner scr = new Scanner(file);

        while (scr.hasNext()) {
            int i, j;
            String current_line = scr.next();
            String[] properties = current_line.split(",");

            switch (properties[0]) {
                case "1":
                    i = 0;
                    j = 0;
                    break;
                case "2":
                    i = 0;
                    j = 1;
                    break;
                case "3":
                    i = 0;
                    j = 2;
                    break;
                case "4":
                    i = 0;
                    j = 3;
                    break;
                case "5":
                    i = 1;
                    j = 0;
                    break;
                case "6":
                    i = 1;
                    j = 1;
                    break;
                case "7":
                    i = 1;
                    j = 2;
                    break;
                case "8":
                    i = 1;
                    j = 3;
                    break;
                case "9":
                    i = 2;
                    j = 0;
                    break;
                case "10":
                    i = 2;
                    j = 1;
                    break;
                case "11":
                    i = 2;
                    j = 2;
                    break;
                case "12":
                    i = 2;
                    j = 3;
                    break;
                case "13":
                    i = 3;
                    j = 0;
                    break;
                case "14":
                    i = 3;
                    j = 1;
                    break;
                case "15":
                    i = 3;
                    j = 2;
                    break;
                case "16":
                    i = 3;
                    j = 3;
                    break;
                default:
                    continue;
            }

            Tiles tile = new Tiles(Integer.parseInt(properties[0]), properties[1], properties[2]);
            gridMap.add(tile.getTile_image(), j, i);
            tiles[tile_count] = tile;
            tile_count++;
        }

        gridMap.setOnMousePressed(e -> {
            int i = (int) e.getX() / 96;
            int j = (int) e.getY() / 96;
            int tile_index = i + 4 * j;

            System.out.println(tile_index);
            System.out.println(tiles[tile_index].getTile_id() + ":" + tiles[tile_index].getType() + ":" + tiles[tile_index].getProperty());

            move_cell(tile_index, gridMap, tiles);
        });
        return gridMap;
    }

    public boolean isWon(Tiles[] tiles) {
        Tiles before;
        Tiles current;

        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].getType().equals("Starter")) {
                before = tiles[0];
                current = tiles[i];
                while (true) {
                    if (current.getProperty().equals("Vertical") && before.getProperty().equals("Vertical")
                            || current.getProperty().equals("Vertical") && before.getProperty().equals("10")
                            || current.getProperty().equals("Vertical") && before.getProperty().equals("11")
                            || current.getProperty().equals("10") && before.getProperty().equals("Horizontal")
                            || current.getProperty().equals("10") && before.getProperty().equals("11")
                            || current.getProperty().equals("10") && before.getProperty().equals("01")
                            || current.getProperty().equals("11") && before.getProperty().equals("Horizontal")
                            || current.getProperty().equals("11") && before.getProperty().equals("11")
                            || current.getProperty().equals("11") && before.getProperty().equals("00")) {
                        if (tiles[i + 4].getType().equals("End")) {
                            System.out.println("You win");
                            return true;
                        } else if (tiles[i + 4].getProperty().equals("Vertical")
                                || tiles[i + 4].getProperty().equals("00")
                                || tiles[i + 4].getProperty().equals("01")) {
                            before = current;
                            current = tiles[i + 4];
                            i += 4;
                        } else {
                            System.out.println("Dead End");
                            return false;
                        }
                    } else if (current.getProperty().equals("Horizontal") && before.getProperty().equals("Horizontal")
                            || current.getProperty().equals("Horizontal") && before.getProperty().equals("01")
                            || current.getProperty().equals("Horizontal") && before.getProperty().equals("11")
                            || current.getProperty().equals("01") && before.getProperty().equals("Vertical")
                            || current.getProperty().equals("01") && before.getProperty().equals("10")
                            || current.getProperty().equals("11") && before.getProperty().equals("Vertical")
                            || current.getProperty().equals("11") && before.getProperty().equals("01")) {
                        if (tiles[i + 1].getType().equals("End")) {
                            System.out.println("You win");
                            return true;
                        } else if (tiles[i + 1].getProperty().equals("Horizontal")
                                || tiles[i + 1].getProperty().equals("10")
                                || tiles[i + 1].getProperty().equals("00")) {
                            before = current;
                            current = tiles[i + 1];
                            i += 1;
                        } else {
                            System.out.println("Dead End");
                            return false;
                        }
                    } else if (current.getProperty().equals("Vertical") && before.getProperty().equals("01")
                            || current.getProperty().equals("Vertical") && before.getProperty().equals("00")
                            || current.getProperty().equals("00") && before.getProperty().equals("Horizontal")
                            || current.getProperty().equals("00") && before.getProperty().equals("01")
                            || current.getProperty().equals("00") && before.getProperty().equals("11")
                            || current.getProperty().equals("01") && before.getProperty().equals("Horizontal")
                            || current.getProperty().equals("01") && before.getProperty().equals("01")
                            || current.getProperty().equals("01") && before.getProperty().equals("11")) {
                        if (tiles[i - 4].getType().equals("End")) {
                            System.out.println("You win");
                            return true;
                        } else if (tiles[i - 4].getProperty().equals("Vertical")
                                || tiles[i - 4].getProperty().equals("10")
                                || tiles[i - 4].getProperty().equals("11")) {
                            before = current;
                            current = tiles[i - 4];
                            i -= 4;
                        } else {
                            System.out.println("Dead End");
                            return false;
                        }
                    } else if (current.getProperty().equals("Horizontal") && before.getProperty().equals("00")
                            || current.getProperty().equals("Horizontal") && before.getProperty().equals("10")
                            || current.getProperty().equals("00") && before.getProperty().equals("Vertical")
                            || current.getProperty().equals("10") && before.getProperty().equals("Vertical")) {
                        if (tiles[i - 1].getType().equals("End")) {
                            System.out.println("You win");
                            return true;
                        } else if (tiles[i - 1].getProperty().equals("Horizontal")
                                || tiles[i - 1].getProperty().equals("01")
                                || tiles[i - 1].getProperty().equals("11")) {
                            before = current;
                            current = tiles[i - 1];
                            i -= 1;
                        } else {
                            System.out.println("Dead End");
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }

    static class Tiles {
        private boolean isStatic;
        private ImageView tile_image;
        private int tile_id;
        private String type;
        private String property;

        Tiles(int tile_id, String type, String property) {
            this.tile_id = tile_id;
            this.type = type;
            this.property = property;

            this.isStatic = type.equals("PipeStatic") || type.equals("End") || type.equals("Starter");

            switch (type) {
                case "Starter":
                    switch (property) {
                        case "Horizontal":
                            tile_image = new ImageView(new Image("tiles/start_horizontal.PNG"));
                            break;
                        case "Vertical":
                            tile_image = new ImageView(new Image("tiles/start_vertical.PNG"));
                            break;
                    }
                    break;
                case "Empty":
                    switch (property) {
                        case "none":
                            tile_image = new ImageView(new Image("tiles/empty.PNG"));
                            break;
                        case "Free":
                            tile_image = new ImageView(new Image("tiles/empty_free.PNG"));
                            break;
                    }
                    break;
                case "Pipe":
                    switch (property) {
                        case "Vertical":
                            tile_image = new ImageView(new Image("tiles/pipe_vertical.PNG"));
                            break;
                        case "Horizontal":
                            tile_image = new ImageView(new Image("tiles/pipe_horizontal.PNG"));
                            break;
                        case "00":
                            tile_image = new ImageView(new Image("tiles/curved_pipe_00.PNG"));
                            break;
                        case "01":
                            tile_image = new ImageView(new Image("tiles/curved_pipe_01.PNG"));
                            break;
                        case "10":
                            tile_image = new ImageView(new Image("tiles/curved_pipe_10.PNG"));
                            break;
                        case "11":
                            tile_image = new ImageView(new Image("tiles/curved_pipe_11.PNG"));
                            break;
                    }
                    break;
                case "PipeStatic":
                    switch (property) {
                        case "Vertical":
                            tile_image = new ImageView(new Image("tiles/pipe_static_vertical.PNG"));
                            break;
                        case "Horizontal":
                            tile_image = new ImageView(new Image("tiles/pipe_static_horizontal.PNG"));
                            break;
                        case "00":
                            tile_image = new ImageView(new Image("tiles/curved_pipe_static_00.PNG"));
                            break;
                        case "01":
                            tile_image = new ImageView(new Image("tiles/curved_pipe_static_01.PNG"));
                            break;
                        case "10":
                            tile_image = new ImageView(new Image("tiles/curved_pipe_static_10.PNG"));
                            break;
                        case "11":
                            tile_image = new ImageView(new Image("tiles/curved_pipe_static_11.PNG"));
                            break;
                    }
                    break;
                case "End":
                    switch (property) {
                        case "Horizontal":
                            tile_image = new ImageView(new Image("tiles/end_horizontal.PNG"));
                            break;
                        case "Vertical":
                            tile_image = new ImageView(new Image("tiles/end_vertical.PNG"));
                            break;
                    }
                    break;
            }
        }

        public boolean can_move_right(int cell_index, Tiles[] tiles) {
            if (cell_index == 3 || cell_index == 7 || cell_index == 11 || cell_index == 15) {
                return false;
            } else if (tiles[cell_index].getType().equals("Pipe") || (tiles[cell_index].getType().equals("Empty") && tiles[cell_index].getProperty().equals("none"))) {
                return tiles[cell_index + 1].getProperty().equals("Free");
            }
            return false;
        }

        public boolean can_move_left(int cell_index, Tiles[] tiles) {
            if (cell_index == 0 || cell_index == 4 || cell_index == 8 || cell_index == 12) {
                return false;
            } else if (tiles[cell_index].getType().equals("Pipe") || (tiles[cell_index].getType().equals("Empty") && tiles[cell_index].getProperty().equals("none"))) {
                return tiles[cell_index - 1].getProperty().equals("Free");
            }
            return false;
        }

        public boolean can_move_up(int cell_index, Tiles[] tiles) {
            if (cell_index == 0 || cell_index == 1 || cell_index == 2 || cell_index == 3) {
                return false;
            } else if (tiles[cell_index].getType().equals("Pipe") || (tiles[cell_index].getType().equals("Empty") && tiles[cell_index].getProperty().equals("none"))) {
                return tiles[cell_index - 4].getProperty().equals("Free");
            }
            return false;
        }

        public boolean can_move_down(int cell_index, Tiles[] tiles) {
            if (cell_index == 12 || cell_index == 13 || cell_index == 14 || cell_index == 15) {
                return false;
            } else if (tiles[cell_index].getType().equals("Pipe") || (tiles[cell_index].getType().equals("Empty") && tiles[cell_index].getProperty().equals("none"))) {
                return tiles[cell_index + 4].getProperty().equals("Free");
            }
            return false;
        }

        public boolean isStatic() {
            return isStatic;
        }

        public void setStatic(boolean aStatic) {
            isStatic = aStatic;
        }

        public ImageView getTile_image() {
            return tile_image;
        }

        public void setTile_image(ImageView tile_image) {
            this.tile_image = tile_image;
        }

        public int getTile_id() {
            return tile_id;
        }

        public void setTile_id(int tile_id) {
            this.tile_id = tile_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }
    }
}