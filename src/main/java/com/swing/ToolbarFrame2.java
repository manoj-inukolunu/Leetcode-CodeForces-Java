package com.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;


/**
 * @author manoji on 2019-11-28.
 */
public class ToolbarFrame2 extends Frame {

  JButton cutButton, copyButton, pasteButton;
  JButton javaButton, macButton, motifButton, winButton;

  public ToolbarFrame2() {
    super("Toolbar Example (Swing)");
    setSize(450, 250);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    ActionListener printListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
      }
    };

    JPanel toolbar = new JPanel();
    toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));

    cutButton = new JButton("Cut");
    cutButton.addActionListener(printListener);
    toolbar.add(cutButton);

    copyButton = new JButton("Copy");
    copyButton.addActionListener(printListener);
    toolbar.add(copyButton);

    pasteButton = new JButton("Paste");
    pasteButton.addActionListener(printListener);
    toolbar.add(pasteButton);

    add(toolbar, BorderLayout.NORTH);

    JPanel infPanel = new JPanel();
    LnfListener lnfListener = new LnfListener(this);
    macButton = new JButton("Mac");
    macButton.addActionListener(lnfListener);
    infPanel.add(macButton);

    javaButton = new JButton("Metal");
    javaButton.addActionListener(lnfListener);
    infPanel.add(javaButton);

    motifButton = new JButton("Motif");
    motifButton.addActionListener(lnfListener);
    infPanel.add(motifButton);

    winButton = new JButton("Windows");
    winButton.addActionListener(lnfListener);
    infPanel.add(winButton);

    add(infPanel, BorderLayout.SOUTH);

  }

  public static void main(String args[]) {
    ToolbarFrame2 toolbarFrame2 = new ToolbarFrame2();
    toolbarFrame2.setVisible(true);
    for (LookAndFeelInfo installedLookAndFeel : UIManager.getInstalledLookAndFeels()) {
      System.out.println(installedLookAndFeel.getName() + " -- " + installedLookAndFeel.getClassName());
    }
  }
}
