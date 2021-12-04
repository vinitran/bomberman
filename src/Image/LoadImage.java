package Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LoadImage {
    private BufferedImage image = null;
    private final String path;

    public LoadImage(String path) {
        this.path = path;
        loadImage();
    }

    private void loadImage() {
        try {
            image = ImageIO.read(LoadImage.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}