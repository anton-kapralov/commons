/*
 *
 *
 * Kapralov A.
 * 21.11.11
 */

package kae.util.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * @author A. Kapralov 21.11.11 20:05
 */
public class UiUtils {

  public static void expandAll(JTree tree) {
    // Loop over all tree rows and make all visible.
    for (int i = 0; i < tree.getRowCount(); ++i) {
      tree.expandRow(i);
    }
  }

  public static void setComponentCursor(Component component, int cursorType) {
    final Cursor cursor = Cursor.getPredefinedCursor(cursorType);
    if (component.getCursor() != cursor) {
      component.setCursor(cursor);
    }
  }

  public static void showPopupMenu(MouseEvent e, JPopupMenu menu, Component component) {
    Point popupLocation = e.getPoint();
    SwingUtilities.convertPointToScreen(popupLocation, component);
    menu.setLocation((int) popupLocation.getX(), (int) popupLocation.getY());
    menu.setInvoker(component);
    menu.setVisible(true);
  }

  public static int calculateMaxRowsCount(JComponent owner, JTable table) {
    final int paneHeight = owner.getHeight();
    final int headerHeight = table.getTableHeader().getHeight();
    final int rowHeight = table.getRowHeight();
    return (paneHeight - headerHeight) / rowHeight;
  }

  public static Window getWindow(Container component) {
    Container parentContainer = component.getParent();
    while (parentContainer != null) {
      if (parentContainer instanceof Window) {
        return (Window) parentContainer;
      } else {
        parentContainer = parentContainer.getParent();
      }
    }

    return null;
  }

  public static Frame getFrame(Component component) {
    Container parentContainer = component.getParent();
    while (parentContainer != null) {
      if (parentContainer instanceof Frame) {
        return (Frame) parentContainer;
      } else {
        parentContainer = parentContainer.getParent();
      }
    }

    return null;
  }

  public static ScalableImageIcon getImageIcon(String resource) {
    URL workingIconUrl = UiUtils.class.getResource(resource);
    final ScalableImageIcon scalableImageIcon = new ScalableImageIcon(workingIconUrl);
    scalableImageIcon.scale();
    return scalableImageIcon;
  }

  public static void center(Window window, float widthFactor, float heightFactor) {
    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    final Rectangle rectangle = env.getMaximumWindowBounds();
    final int width = rectangle.width;
    final int height = rectangle.height;
    window.setLocation((int) (width * (1 - widthFactor) / 2), (int) (height * (1 - heightFactor) / 2));
    Dimension size = new Dimension((int) (width * widthFactor), (int) (height * heightFactor));
    window.setPreferredSize(size);
  }

  public static Dimension resizeScreenDimension(float widthFactor, float heightFactor) {
    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    final Rectangle rectangle = env.getMaximumWindowBounds();
    final int width = rectangle.width;
    final int height = rectangle.height;
    return new Dimension((int) (width * widthFactor), (int) (height * heightFactor));
  }

  public static void setColor(JPanel panel, Color color) {
    panel.setBackground(color);

    final Component[] children = panel.getComponents();
    for (Component child : children) {
      if (child instanceof JPanel) {
        JPanel childPanel = (JPanel) child;
        setColor(childPanel, color);
      }
    }
  }
}
