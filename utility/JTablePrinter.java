/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ls.jtsk.ui.utility;

import java.awt.print.PrinterException;
import java.text.MessageFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author LS
 */
public class JTablePrinter {
    private static Boolean fitWidth = true;
    
    public static void printJTable(JTable gradesTable, String headerString, String footerString) {
        StringBuilder sb = new StringBuilder();
        sb.append("这个是页头第一行\n");
        sb.append("这个是页头第二行");
        MessageFormat header = new MessageFormat(sb.toString());
        MessageFormat footer = new MessageFormat("这个是页脚");
        
        /* determine the print mode */
        JTable.PrintMode mode = fitWidth ? JTable.PrintMode.FIT_WIDTH
                                         : JTable.PrintMode.NORMAL;
        try {
            /* print the table */
            boolean complete = gradesTable.print(mode, header, footer,
                                                 true, null,
                                                 true, null);

            /* if printing completes */
            if (complete) {
                /* show a success message */
                JOptionPane.showMessageDialog(null,
                                              "Printing Complete",
                                              "Printing Result",
                                              JOptionPane.INFORMATION_MESSAGE);
            } else {
                /* show a message indicating that printing was cancelled */
                JOptionPane.showMessageDialog(null,
                                              "Printing Cancelled",
                                              "Printing Result",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException pe) {
            /* Printing failed, report to the user */
            JOptionPane.showMessageDialog(null,
                                          "Printing Failed: " + pe.getMessage(),
                                          "Printing Result",
                                          JOptionPane.ERROR_MESSAGE);
        }
}
}
