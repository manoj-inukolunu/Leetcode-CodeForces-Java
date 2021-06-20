package com.swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 * @author manoji on 2019-12-12.
 */
public class TableTest extends JFrame {

  public TableTest() {
    String[] columns = new String[]{"Request Type", "Request URL", "Date"};

    String[][] data = new String[][]{
        {"GET", "https://google.com", LocalDateTime.now().toString()}
    };

    JTable table = new JTable(data, columns);

    this.add(new JScrollPane(table));

    this.setTitle("Table Example");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setVisible(true);
  }

  public static void _main(String args[]) {
    SwingUtilities.invokeLater(() -> {
      new TableTest();
    });
  }

  public static void main(String[] a)
      throws Exception {
    Class.forName("org.h2.Driver");
    Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
    // add application code here
    conn.close();
  }

}
