package kae.util.swing;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/** @author eiv 09.10.2014. */
public class ScalableImageIcon extends ImageIcon implements Scalable {
  private boolean scaled = false;

  public ScalableImageIcon(String filename, String description) {
    super(filename, description);
  }

  public ScalableImageIcon(String filename) {
    super(filename);
  }

  public ScalableImageIcon(URL location, String description) {
    super(location, description);
  }

  public ScalableImageIcon(URL location) {
    super(location);
  }

  public ScalableImageIcon(Image image, String description) {
    super(image, description);
  }

  public ScalableImageIcon(Image image) {
    super(image);
  }

  public ScalableImageIcon(byte[] imageData, String description) {
    super(imageData, description);
  }

  public ScalableImageIcon(byte[] imageData) {
    super(imageData);
  }

  public ScalableImageIcon() {}

  public boolean isScaled() {
    return scaled;
  }

  @Override
  public void scale() {
    if (scaled) {
      return;
    }

    final ScaleController scaleController = ScaleController.getInstance();

    scaleController.scale(this);
    scaled = true;
  }
}
