package com.swing;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Action;

/**
 * @author manoji on 2019-11-28.
 */
public class ToolbarFrame1 extends Frame {

  Button cutButton, copyButton, pasteButton;

  public ToolbarFrame1() {
    super("Toolbar Example (AWT)");
    setSize(450, 250);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    ActionListener printListener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
      }
    };

    Panel toolbar = new Panel();
    toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
    cutButton = new Button("Cut");
    cutButton.addActionListener(printListener);
    toolbar.add(cutButton);

    copyButton = new Button("Copy");
    copyButton.addActionListener(printListener);
    toolbar.add(copyButton);

    pasteButton = new Button("Paste");
    pasteButton.addActionListener(printListener);
    toolbar.add(pasteButton);

    add(toolbar, BorderLayout.NORTH);
  }

  public static void main(String args[]) {
    ToolbarFrame1 toolbarFrame1 = new ToolbarFrame1();
    toolbarFrame1.setVisible(true);
  }

}
