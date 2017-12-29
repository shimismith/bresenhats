package bresenhats;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class SpriteSheetReader {
  
  public static Image[] readSpriteSheet(int col, int row, String spriteSheetPath) throws IOException {    
    Image[] images = new Image[col * row];
    
    BufferedImage spriteSheet = ImageIO.read(new File(spriteSheetPath));
    int width = spriteSheet.getWidth() / col;
    int height = spriteSheet.getHeight() / row;
    
    for (int y = 0; (y + 1) * height <= spriteSheet.getHeight(); y++) {
      for (int x = 0; (x + 1) * width <= spriteSheet.getWidth(); x++) {
         images[col * y + x] = SwingFXUtils.toFXImage(spriteSheet.getSubimage(x * width, y * height, width, height), null);
      }
    }
    
    return images;
  }
}
