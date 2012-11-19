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
    private long existingBabyId = 0;
    HashMap <Integer, Apgar> apgarHash = new HashMap <Integer, Apgar> ();
    private ApgarFactory(long newBabyId) {
        existingBabyId = newBabyId;
    }
    
    // TODO [待总] 每一个baby自身打分时，需要同一个factory
    // 不同的baby打分时，需要不同的factory，故做这个单例时，需要条件。
    public static ApgarFactory getApgarFactory(long newBabyId) {
        if (af != null && af.existingBabyId == newBabyId) return af;
        af = new ApgarFactory(newBabyId);
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
