/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ls.jtsk.ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

/**
 *
 * @author LS
 */
public class TryPrint {
    public static void main(String[] args) throws PrintException {
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        PrintService svc = PrintServiceLookup.lookupDefaultPrintService();
        PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
        PrintService service = ServiceUI.printDialog(
                null, 100, 100, services, svc, null, attrs);
        DocPrintJob job = service.createPrintJob();
        URL url = null;
        try {
            url = new URL("http://www.baidu.com/img/baidu_sylogo1.gif");
        } catch (MalformedURLException ex) {
            Logger.getLogger(TryPrint.class.getName()).log(Level.SEVERE, null, ex);
        }
        DocFlavor flavor = DocFlavor.URL.GIF;
        Doc doc = new SimpleDoc(url, flavor, null);
        attrs.add(new Copies(1));
        job.print(doc, attrs);
    }
}
