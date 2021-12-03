import java.io.File;
import java.util.Scanner;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Main extends Application {
    PathTransition pathOne1 = new PathTransition();
    PathTransition pathOne2 = new PathTransition();
    PathTransition pathOne3 = new PathTransition();
    PathTransition pathTwo1 = new PathTransition();
    PathTransition pathTwo2 = new PathTransition();
    PathTransition pathTwo3 = new PathTransition();
    PathTransition pathTwo4 = new PathTransition();
    PathTransition pathTwo5 = new PathTransition();

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
    Button go_back_button = new Button("Back");
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
        credit_button.setOnAction(e -> credit_screen());
        quit_button.setOnAction(e -> closeProgram());

        start_pane.setStyle("-fx-background-color: #5e0000");
        start_pane.getChildren().addAll(start_button, credit_button, quit_button);
        start_button.relocate(145.5, 135);
        credit_button.relocate(145.5, 193);
        quit_button.relocate(145.5, 251);

        start_scene = new Scene(start_pane, 387, 434, Color.BLACK);
        scene = new Scene(borderPane, 387, 434, Color.BLACK);

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        window.getIcons().add(new Image("icons/window_icon.png"));
        window.setTitle("Game");
        window.setScene(start_scene);
        window.setResizable(false);
        window.show();
    }

    public void credit_screen() {
        GridPane gridPaneForCredits = new GridPane();
        gridPaneForCredits.setHgap(5);
        gridPaneForCredits.setVgap(5);

        Font fontBold = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);
        Font fontNormal = Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.REGULAR, 20);
        Font fontNormalSmall = Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.REGULAR, 15);

        Text text1 = new Text("Programmers:");
        Text text1_1 = new Text("Yasin Tarakçı");
        Text text1_2 = new Text("Yusuf Taha Atalay");
        Text text2 = new Text("Artist:");
        Text text2_2 = new Text("Yusuf Taha Atalay");
        Text text3 = new Text("Icons:");
        Text text3_1 = new Text("https://www.iconfinder.com/");
        Text text4 = new Text("Special Thanks:");
        Text text4_1 = new Text("Aseprite for Pixel Art");
        Text text4_2 = new Text("Intellij IDEA and JetBrains");
        Text text4_3 = new Text("TabNine the best plugin");

        text1.setFont(fontBold);
        text2.setFont(fontBold);
        text3.setFont(fontBold);
        text4.setFont(fontBold);
        text1_1.setFont(fontNormal);
        text1_2.setFont(fontNormal);
        text2_2.setFont(fontNormal);
        text3_1.setFont(fontNormalSmall);
        text4_1.setFont(fontNormalSmall);
        text4_2.setFont(fontNormalSmall);
        text4_3.setFont(fontNormalSmall);

        gridPaneForCredits.add(text1, 0, 0);
        gridPaneForCredits.add(text1_1, 1, 0);
        gridPaneForCredits.add(text1_2, 1, 1);
        gridPaneForCredits.add(new Text(""), 0, 2);
        gridPaneForCredits.add(text2, 0, 3);
        gridPaneForCredits.add(text2_2, 1, 3);
        gridPaneForCredits.add(new Text(""), 0, 4);
        gridPaneForCredits.add(text3, 0, 5);
        gridPaneForCredits.add(text3_1, 1, 5);
        gridPaneForCredits.add(new Text(""), 0, 7);
        gridPaneForCredits.add(text4, 0, 8);
        gridPaneForCredits.add(text4_1, 1, 8);
        gridPaneForCredits.add(text4_2, 1, 9);
        gridPaneForCredits.add(text4_3, 1, 10);

        Pane credits_pane = new Pane(gridPaneForCredits, go_back_button);
        credits_pane.setStyle("-fx-background-color: #0ea582");
        gridPaneForCredits.relocate(10, 5);
        go_back_button.relocate(145.5, 325.5);
        go_back_button.setPrefSize(96, 48);
        go_back_button.setOnAction(e -> window.setScene(start_scene));
        Scene credits_scene = new Scene(credits_pane, 387, 434);
        window.setScene(credits_scene);
    }

    public void closeProgram() {
        int result = ConfirmBox.display("Exit", "Do you really want to close the game?");
        if (result == 1)
            window.close();
        else if (result == 0)
            window.setScene(start_scene);
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
        circle = new Circle(48, 36, 15, Color.YELLOW);
        int time_for_tile = 430;

        if (level.equals(level1) || level.equals(level2) || level.equals(level3)) {
            Line line_path1_1 = new Line(48, 36, 48, 292);
            Arc arc1 = new Arc(97, 292, 48, 48, 180, 90);
            Line line_path1_2 = new Line(97, 340, 346, 338 + 2);

            pathOne1.setDuration(Duration.millis(time_for_tile * 3));
            pathOne1.setPath(line_path1_1);
            pathOne1.setNode(circle);
            pathOne1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathOne1.setCycleCount(1);
            pathOne1.setAutoReverse(false);

            pathOne2.setDuration(Duration.millis(time_for_tile));
            pathOne2.setDelay(Duration.millis(time_for_tile * 3 - 100));
            pathOne2.setPath(arc1);
            pathOne2.setNode(circle);
            pathOne2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathOne2.setCycleCount(1);
            pathOne2.setAutoReverse(false);

            pathOne3.setDuration(Duration.millis(time_for_tile * 3));
            pathOne3.setDelay(Duration.millis(time_for_tile * 4 - 150));
            pathOne3.setPath(line_path1_2);
            pathOne3.setNode(circle);
            pathOne3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathOne3.setCycleCount(1);
            pathOne3.setAutoReverse(false);
        } else if (level.equals(level4) || level.equals(level5)) {
            Line line_path2_1 = new Line(48, 36, 48, 193);
            Arc arc2_1 = new Arc(97, 193, 48, 48, 180, 90);
            Line line_path2_2 = new Line(97, 241, 290, 241);
            Arc arc2_2 = new Arc(292, 193, 48, 48, 270, 90);
            Line line_path2_3 = new Line(340, 193, 340, 137);

            pathTwo1.setDuration(Duration.millis(time_for_tile * 2));
            pathTwo1.setPath(line_path2_1);
            pathTwo1.setNode(circle);
            pathTwo1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTwo1.setCycleCount(1);
            pathTwo1.setAutoReverse(false);

            pathTwo2.setDuration(Duration.millis(time_for_tile));
            pathTwo2.setDelay(Duration.millis(time_for_tile * 2 - 100));
            pathTwo2.setPath(arc2_1);
            pathTwo2.setNode(circle);
            pathTwo2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTwo2.setCycleCount(1);
            pathTwo2.setAutoReverse(false);

            pathTwo3.setDuration(Duration.millis(time_for_tile * 2));
            pathTwo3.setDelay(Duration.millis(time_for_tile * 3 - 150));
            pathTwo3.setPath(line_path2_2);
            pathTwo3.setNode(circle);
            pathTwo3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTwo3.setCycleCount(1);
            pathTwo3.setAutoReverse(false);

            pathTwo4.setDuration(Duration.millis(time_for_tile));
            pathTwo4.setDelay(Duration.millis(time_for_tile * 5 - 200));
            pathTwo4.setPath(arc2_2);
            pathTwo4.setNode(circle);
            pathTwo4.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTwo4.setCycleCount(1);
            pathTwo4.setAutoReverse(false);

            pathTwo5.setDuration(Duration.millis(time_for_tile));
            pathTwo5.setDelay(Duration.millis(time_for_tile * 6 - 250));
            pathTwo5.setPath(line_path2_3);
            pathTwo5.setNode(circle);
            pathTwo5.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTwo5.setCycleCount(1);
            pathTwo5.setAutoReverse(false);
        }
    }

    public void animation_play(String level) {
        if (level.equals(level1) || level.equals(level2) || level.equals(level3)) {
            pathOne1.play();
            pathOne2.play();
            pathOne3.play();
        } else if (level.equals(level4) || level.equals(level5)) {
            pathTwo1.play();
            pathTwo2.play();
            pathTwo3.play();
            pathTwo4.play();
            pathTwo5.play();
        }
        System.out.println("Level is completed.");
    }

    public void move_cell(int cell_index, GridPane gridPane, Tiles[] tiles) {
        tiles[cell_index].getTile_image().setOnMouseDragged(e -> {
            if (tiles[cell_index].getType().equals("Pipe")
                    || (tiles[cell_index].getType().equals("Empty") && tiles[cell_index].getProperty().equals("none"))) {
                if (e.getX() - tiles[cell_index].getTile_image().getX() > 96 && can_move_right(cell_index, tiles)) {
                    if (!isWon(tiles)) {
                        GridPane.setColumnIndex(tiles[cell_index + 1].getTile_image(), GridPane.getColumnIndex(tiles[cell_index + 1].getTile_image()) - 1);
                        GridPane.setColumnIndex(tiles[cell_index].getTile_image(), GridPane.getColumnIndex(tiles[cell_index].getTile_image()) + 1);

                        Tiles current = tiles[cell_index];
                        Tiles next = tiles[cell_index + 1];
                        System.out.println(current.toString() + " moved to right.");

                        tiles[cell_index + 1] = current;
                        step_controller(cell_index, tiles, next);
                    }
                } else if (e.getX() - tiles[cell_index].getTile_image().getX() < 0 && can_move_left(cell_index, tiles)) {
                    if (!isWon(tiles)) {
                        GridPane.setColumnIndex(tiles[cell_index - 1].getTile_image(), GridPane.getColumnIndex(tiles[cell_index - 1].getTile_image()) + 1);
                        GridPane.setColumnIndex(tiles[cell_index].getTile_image(), GridPane.getColumnIndex(tiles[cell_index].getTile_image()) - 1);

                        Tiles current = tiles[cell_index];
                        Tiles next = tiles[cell_index - 1];
                        System.out.println(current.toString() + " moved to left.");

                        tiles[cell_index - 1] = current;
                        step_controller(cell_index, tiles, next);
                    }
                } else if (e.getY() - tiles[cell_index].getTile_image().getY() > 96 && can_move_down(cell_index, tiles)) {
                    if (!isWon(tiles)) {
                        GridPane.setRowIndex(tiles[cell_index + 4].getTile_image(), GridPane.getRowIndex(tiles[cell_index + 4].getTile_image()) - 1);
                        GridPane.setRowIndex(tiles[cell_index].getTile_image(), GridPane.getRowIndex(tiles[cell_index].getTile_image()) + 1);

                        Tiles current = tiles[cell_index];
                        Tiles next = tiles[cell_index + 4];
                        System.out.println(current.toString() + " moved to down.");

                        tiles[cell_index + 4] = current;
                        step_controller(cell_index, tiles, next);
                    }
                } else if (e.getY() - tiles[cell_index].getTile_image().getY() < 0 && can_move_up(cell_index, tiles)) {
                    if (!isWon(tiles)) {
                        GridPane.setRowIndex(tiles[cell_index - 4].getTile_image(), GridPane.getRowIndex(tiles[cell_index - 4].getTile_image()) + 1);
                        GridPane.setRowIndex(tiles[cell_index].getTile_image(), GridPane.getRowIndex(tiles[cell_index].getTile_image()) - 1);

                        Tiles current = tiles[cell_index];
                        Tiles next = tiles[cell_index - 4];
                        System.out.println(current.toString() + " moved to up.");

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

            System.out.println("\nTile index: " + tile_index);
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
                            return true;
                        } else if (tiles[i + 4].getProperty().equals("Vertical")
                                || tiles[i + 4].getProperty().equals("00")
                                || tiles[i + 4].getProperty().equals("01")) {
                            before = current;
                            current = tiles[i + 4];
                            i += 4;
                        } else {
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
                            return true;
                        } else if (tiles[i + 1].getProperty().equals("Horizontal")
                                || tiles[i + 1].getProperty().equals("10")
                                || tiles[i + 1].getProperty().equals("00")) {
                            before = current;
                            current = tiles[i + 1];
                            i += 1;
                        } else {
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
                            return true;
                        } else if (tiles[i - 4].getProperty().equals("Vertical")
                                || tiles[i - 4].getProperty().equals("10")
                                || tiles[i - 4].getProperty().equals("11")) {
                            before = current;
                            current = tiles[i - 4];
                            i -= 4;
                        } else {
                            return false;
                        }
                    } else if (current.getProperty().equals("Horizontal") && before.getProperty().equals("00")
                            || current.getProperty().equals("Horizontal") && before.getProperty().equals("10")
                            || current.getProperty().equals("00") && before.getProperty().equals("Vertical")
                            || current.getProperty().equals("10") && before.getProperty().equals("Vertical")) {
                        if (tiles[i - 1].getType().equals("End")) {
                            return true;
                        } else if (tiles[i - 1].getProperty().equals("Horizontal")
                                || tiles[i - 1].getProperty().equals("01")
                                || tiles[i - 1].getProperty().equals("11")) {
                            before = current;
                            current = tiles[i - 1];
                            i -= 1;
                        } else {
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

    public static void main(String[] args) {
        launch(args);
    }
}