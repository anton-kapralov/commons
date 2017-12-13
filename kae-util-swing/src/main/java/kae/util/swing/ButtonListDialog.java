package kae.util.swing;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ButtonListDialog<T> extends JDialog {

  public static final String PROPERTY_VALUE = "value";
  private JPanel contentPane;

  private final ActionListener actionListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      onItemClick(e);
    }
  };

  private String emptyInfoString = "";

  private Font buttonFont;
  private final int buttonMargin = 10;
  private ScaleController scaleController;

  public ButtonListDialog(JComponent owner) {
    contentPane = new JPanel();

    setContentPane(contentPane);

    setUndecorated(true);
    setDefaultLookAndFeelDecorated(true);
    getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);

    Point location = owner.getLocationOnScreen();
    location.translate(0, owner.getHeight());
    setLocation(location);

    buttonFont = contentPane.getFont();

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowDeactivated(WindowEvent e) {
        dispose();
      }
    });
  }

  public void setButtonFontSize(float size) {
    buttonFont = buttonFont.deriveFont(size);
  }


  public void setEmptyInfoString(String emptyInfoString) {
    this.emptyInfoString = emptyInfoString;
  }

  public void setAdapter(Adapter<T> adapter) {

    Border border = BorderFactory.createCompoundBorder(
        BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
        BorderFactory.createEmptyBorder(10, 10, 10, 10));
    contentPane.setBorder(border);

    int count = adapter.getCount();
    if (count > 0) {
      contentPane.setLayout(new GridLayout(count, 1, 5, 5));

      for (int i = 0; i < count; i++) {
        JButton button = new JButton(adapter.getName(i));
        button.setFont(buttonFont);
        button.setBorder(new EmptyBorder(buttonMargin, buttonMargin, buttonMargin, buttonMargin));
        button.putClientProperty(PROPERTY_VALUE, adapter.getItem(i));
        button.addActionListener(actionListener);
        contentPane.add(button);
      }
    } else {
      contentPane.add(new JLabel(emptyInfoString));
    }
  }

  private void onItemClick(ActionEvent e) {
    JButton button = (JButton) e.getSource();
    //noinspection unchecked
    T selectedValue = (T) button.getClientProperty(PROPERTY_VALUE);
    setVisible(false);
    if (selectionListener != null) {
      selectionListener.selected(selectedValue);
    }
  }

  private SelectionListener<T> selectionListener;

  public void setSelectionListener(SelectionListener<T> selectionListener) {
    this.selectionListener = selectionListener;
  }

  public static interface Adapter<T> {

    int getCount();

    T getItem(int i);

    String getName(int i);

  }

}
