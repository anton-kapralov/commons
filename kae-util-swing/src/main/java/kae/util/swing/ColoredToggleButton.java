package kae.util.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author eiv
 * 20.06.14.
 */
public class ColoredToggleButton extends JToggleButton {

  private static final Color SELECTED_GROUP_BUTTON_COLOR = new Color(0xFCB312);
  private static final Color SELECTED_BUTTON_COLOR = new Color(0x25A5FC);
  private static final Color NON_SELECTED_BUTTON_COLOR = new Color(0xA9ACB1);

  public ColoredToggleButton() {
  }

  public ColoredToggleButton(Icon icon) {
    super(icon);
  }

  public ColoredToggleButton(Icon icon, boolean selected) {
    super(icon, selected);
  }

  public ColoredToggleButton(String text) {
    super(text);
  }

  public ColoredToggleButton(String text, boolean selected) {
    super(text, selected);
  }

  public ColoredToggleButton(Action a) {
    super(a);
  }

  public ColoredToggleButton(String text, Icon icon) {
    super(text, icon);
  }

  public ColoredToggleButton(String text, Icon icon, boolean selected) {
    super(text, icon, selected);
  }

  @Override
  public void setModel(ButtonModel newModel) {
    super.setModel(newModel);
    getModel().addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          if (((DefaultButtonModel) getModel()).getGroup() != null) {
            setBackground(SELECTED_GROUP_BUTTON_COLOR);
          } else {
            setBackground(SELECTED_BUTTON_COLOR);
          }
        } else {
          setBackground(NON_SELECTED_BUTTON_COLOR);
        }
      }
    });
  }
}
