package Images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class LoadImage {
    private BufferedImage image = null;
    private final String path;

    public LoadImage(String path) {
        this.path = path;
        loadImage();
    }

    private void loadImage() {
        try {
            image = ImageIO.read(Objects.requireNonNull(LoadImage.class.getResourceAsStream(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}