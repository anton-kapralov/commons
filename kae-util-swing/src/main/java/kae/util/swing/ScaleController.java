package kae.util.swing;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.JTableHeader;

/**
 * @author eiv
 *         19.05.14.
 */
public class ScaleController {

  private static ScaleController instance;

  public static ScaleController getInstance() {
    return instance;
  }

  public static void initInstance(boolean autoScale, int originalWidth, int originalHeight, float scaleFactor) {
    instance = new ScaleController(autoScale, originalWidth, originalHeight, scaleFactor);
  }

  private final float scaleFactor;
  private final boolean autoScale;

  private ScaleController(boolean autoScale, int originalWidth, int originalHeight, float scaleFactor) {
    this.autoScale = autoScale;
    if(autoScale) {
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      //eiv разрешение на которое надо оптимизировать
      //Dimension screenSize = new Dimension(1024, 768);
      final float scaleFactorWidth = (float) (screenSize.getWidth() / originalWidth);
      final float scaleFactorHeight = (float) (screenSize.getHeight() / originalHeight);
      scaleFactor = Math.min(scaleFactorWidth, scaleFactorHeight);
      this.scaleFactor = scaleFactor;
    } else {
      this.scaleFactor = scaleFactor;
    }
  }

  public float getScaleFactor() {
    return scaleFactor;
  }

  public boolean isAutoScale() {
    return autoScale;
  }

  public void scale(Component component) {
    if (component instanceof Scalable) {
      Scalable scalable = (Scalable) component;
      scalable.scale();
      return;
    }

    final Font scaledFont = scale(component.getFont());
    component.setFont(scaledFont);

    if (component instanceof JLabel) {
      final JLabel label = (JLabel) component;
      final Icon icon = label.getIcon();
      if (icon instanceof ScalableImageIcon) {
        ScalableImageIcon scalableImageIcon = (ScalableImageIcon) icon;
        scale(scalableImageIcon);
      } else if (icon instanceof ImageIcon) {
        ImageIcon imageIcon = (ImageIcon) icon;
        imageIcon = scale(imageIcon);
        label.setIcon(imageIcon);
      }
    } else if (component instanceof AbstractButton) {
      final AbstractButton button = (AbstractButton) component;
      final Icon icon = button.getIcon();
      if (icon instanceof ScalableImageIcon) {
        ScalableImageIcon scalableImageIcon = (ScalableImageIcon) icon;
        scalableImageIcon.scale();
      } else if (icon instanceof ImageIcon) {
        ImageIcon imageIcon = (ImageIcon) icon;
        button.setIcon(scale(imageIcon));
      }
    }

    if (component instanceof Container) {
      Container container = (Container) component;
      for (Component child : container.getComponents()) {
        scale(child);
      }
    }
  }

  public Font scale(Font font) {
    int size = font.getSize();
    return scale(font, size);
  }

  private Font scale(Font font, int size) {
    return font.deriveFont(size * scaleFactor);
  }

  private ImageIcon scale(ImageIcon imageIcon) {
    if (imageIcon != null) {
      final int width = (int) (imageIcon.getIconWidth() * scaleFactor);
      final int height = (int) (imageIcon.getIconHeight() * scaleFactor);
      return new ImageIcon(imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }
    return null;
  }

  public ScalableImageIcon scale(ScalableImageIcon scalableImageIcon) {
    if(scalableImageIcon.isScaled()) {
      return scalableImageIcon;
    }
    if(scalableImageIcon != null) {
      final int width = (int) (scalableImageIcon.getIconWidth() * scaleFactor);
      final int height = (int) (scalableImageIcon.getIconHeight() * scaleFactor);
      scalableImageIcon.setImage(scalableImageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
      return scalableImageIcon;
    }
    return null;
  }

  public ImageIcon scale(ImageIcon imageIcon, int originalWidth, int originalHeight) {
    if (imageIcon != null) {
      int width = (int) (originalWidth * scaleFactor);
      int height = (int) (originalHeight * scaleFactor);
      return new ImageIcon(imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }
    return null;
  }

}
