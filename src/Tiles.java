import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tiles {
    private ImageView tile_image;
    private int tile_id;
    private String type;
    private String property;

    Tiles(int tile_id, String type, String property) {
        this.tile_id = tile_id;
        this.type = type;
        this.property = property;

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

    @Override
    public String toString() {
        return "Tile -> " + tile_id + ":" + type + ":" + property;
    }
}