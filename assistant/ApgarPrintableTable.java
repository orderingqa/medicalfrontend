/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ls.jtsk.ui.assistant;

import javax.swing.table.TableModel;

/**
 *
 * @author liushuai
 */
public class ApgarPrintableTable {

    public static String getPrintableApgarString(TableModel tm) {
        StringBuffer sb = new StringBuffer();
        if (tm != null) {
            for (int i=0; i<tm.getRowCount(); i++) {
                sb.append("\r\n\r\n");
                for (int j=0; j<tm.getColumnCount(); j++) {
                    if (i == 0) {
                        sb.append(String.format("%30s",tm.getColumnName(j)));
                    } else {
                        try {
                            sb.append(String.format("%30d", tm.getValueAt(i, j)));
                        } catch (Exception e) {
                            sb.append(String.format("%30s", tm.getValueAt(i, j)));
                        }
                   }
                } 
            }
        }
        return sb.toString();
    }
    
}
