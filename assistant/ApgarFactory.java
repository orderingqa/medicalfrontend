/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ls.jtsk.ui.assistant;
import java.util.HashMap;
import ls.jtsk.model.Apgar;

/**
 *
 * @author liushuai
 */
public class ApgarFactory {
    static ApgarFactory af = null;
    HashMap <Integer, Apgar> apgarHash = new HashMap <Integer, Apgar> ();
    private ApgarFactory() {
    }
    public static ApgarFactory getApgarFactory() {
        if (af != null) return af;
        af = new ApgarFactory();
        return af;
    }
    public Apgar getApgarByInterval(int apgarInternal) {
        Apgar ap = null;
        if (apgarHash.containsKey(1))
            ap = (Apgar) apgarHash.get(1);
        else{
            ap = new Apgar();
            ap.setApgarInterval(1);
            apgarHash.put(1, ap);
        }
        return ap;
    }
}
