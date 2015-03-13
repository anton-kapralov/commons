package kae.util.swing;

import java.awt.*;

import javax.swing.*;

import kae.util.MorseCodeAbstractPlayer;

/**
 * ComponentBlinker
 *
 * @author Kapralov A.
 *         10.12.2014 17:23
 */
public class ComponentBlinker extends MorseCodeAbstractPlayer {

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
  protected void dotOrDash(boolean dot) {
    component.setBackground(dot ? color : defaultColor);
  }

}
