package com.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author manoji on 2019-11-28.
 */
public class LnfListener implements ActionListener {

  Frame frame;

  public LnfListener(Frame f) {
    frame = f;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String infName = null;
    if (e.getActionCommand().equalsIgnoreCase("Mac")) {
      infName = "com.apple.laf.AquaLookAndFeel";
    } else if (e.getActionCommand().equalsIgnoreCase("Metal")) {
      infName = "javax.swing.plaf.metal.MetalLookAndFeel";
    } else if (e.getActionCommand().equalsIgnoreCase("Motif")) {
      infName = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    } else if (e.getActionCommand().equalsIgnoreCase("Windows")) {
      infName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    } else {
      System.err.println("Unrecognized L&F request action:" + e.getActionCommand());
      return;
    }

    try {
      UIManager.setLookAndFeel(infName);
      SwingUtilities.updateComponentTreeUI(frame);

    } catch (IllegalAccessException ex) {
      ex.printStackTrace();
    } catch (InstantiationException ex) {
      ex.printStackTrace();
    } catch (UnsupportedLookAndFeelException ex) {
      ex.printStackTrace();
    } catch (ClassNotFoundException ex) {
      ex.printStackTrace();
    }
  }
}
