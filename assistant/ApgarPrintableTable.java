/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ls.jtsk.ui.assistant;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

/**
 *
 * @author liushuai
 */
public class ApgarPrintableTable {
    public static String getPrintableApgarString(TableModel tm) {
        StringBuffer sb = new StringBuffer();
        if (tm != null) {
            // title需要单独处理
            sb.append("\r\n");
            for (int j=0; j<tm.getColumnCount(); j++) {
                sb.append(getFormatedString(tm.getColumnName(j), "%20s"));
            }
            for (int i=0; i<tm.getRowCount(); i++) {
                sb.append("\r\n");
                for (int j=0; j<tm.getColumnCount(); j++) {       
                    try {
                        sb.append(String.format("%20d", tm.getValueAt(i, j)));
                    } catch (Exception e) {
                        sb.append(getFormatedString((String)tm.getValueAt(i, j) , "%20s"));
                    }
                } 
            }
        }
        return sb.toString();
    }
    
    
    // 以30的间距来控制每一个打分项，效果不错。
    public static String getFormatedString(String s1, String format) {
        try {
            String s2 = String.format(format, new String(s1.getBytes(),"ISO-8859-1"));
            return new String(s2.getBytes("ISO-8859-1"));
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        String s = "人民共和国";
        System.out.println(s);
        System.out.println(getFormatedString(s, "%30s"));
        System.out.println(getFormatedString("中国共产党中央人民政府", "%30s"));
    }
    
}
