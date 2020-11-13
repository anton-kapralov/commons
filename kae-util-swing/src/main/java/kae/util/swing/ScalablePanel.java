package kae.util.swing;

import javax.swing.*;
import java.awt.*;

/**
 * ScalablePanel
 *
 * @author Kapralov A. 01.10.2014 15:00
 */
public class ScalablePanel extends JPanel implements Scalable {

  private boolean scaled = false;

  public ScalablePanel(LayoutManager layout, boolean isDoubleBuffered) {
    super(layout, isDoubleBuffered);
  }

  public ScalablePanel(LayoutManager layout) {
    super(layout);
  }

  public ScalablePanel(boolean isDoubleBuffered) {
    super(isDoubleBuffered);
  }

  public ScalablePanel() {}

  @Override
  public void scale() {
    if (scaled) {
      return;
    }

    final ScaleController scaleController = ScaleController.getInstance();

    int componentCount = getComponentCount();
    for (int i = 0; i < componentCount; i++) {
      Component child = getComponent(i);
      scaleController.scale(child);
    }

    scaled = true;
  }
}
