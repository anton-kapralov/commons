package kae.util.swing;

import kae.util.concurrency.AbstractBlinker;

import javax.swing.*;
import java.awt.*;

/**
 * ComponentBlinker
 *
 * @author Kapralov A. 10.12.2014 17:23
 */
public class ComponentBlinker extends AbstractBlinker {

  protected final JComponent component;
  private final Color defaultColor;
  private final Color color;

  public ComponentBlinker(JComponent component, Color color, long[] pattern, int cyclesCount) {
    super(pattern, cyclesCount);
    this.component = component;
    defaultColor = this.component.getBackground();
    this.color = color;
  }

  @Override
  protected void blink(boolean opened) {
    SwingUtilities.invokeLater(() -> component.setBackground(opened ? color : defaultColor));
  }
}
