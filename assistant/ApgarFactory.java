/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ls.jtsk.ui.assistant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
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
        if (apgarHash.containsKey(apgarInternal))
            ap = (Apgar) apgarHash.get(apgarInternal);
        else{
            ap = new Apgar();
            ap.setApgarInterval(apgarInternal);
            apgarHash.put(apgarInternal, ap);
        }
        return ap;
    }
    public Collection<Apgar> getAllApgars() {
        Collection apgars = apgarHash.values();
        return apgars;
    }
}
