/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ls.jtsk.ui.timer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author liushuai
 */
public class ApgarTimer implements Runnable{
    private int minute=0, second = 0;
    JLabel extJLabel = null;
    private void autoUpdateTimer() {
        if (second < 59) {
            second++;
        } else {
            second = 0;
            if (minute < 59) {minute++;}
            else {minute = 0;}
        }
    }
    private String getTimerModel() {
        String minute = "0" + this.minute;
        String second = "0" + this.second;
        int minLength = minute.length();
        int secLength = second.length();
        return minute.substring(minLength-2, minLength)+":"+second.substring(secLength-2, secLength);
    }

        
    private void fillExternalComponent(JLabel jl, String newValue) {
        if (jl != null)    jl.setText(newValue);
    }
    
    @Override
    public void run() {
        while (true) {
            autoUpdateTimer();
//            System.out.println(getTimerModel());
            fillExternalComponent(extJLabel, getTimerModel());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ApgarTimer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    public static void registerDisplayComponent(JComponent jl) {
        ApgarTimer ap = new ApgarTimer();
        ap.extJLabel = (JLabel)jl;
        new Thread(ap).start();
    }
    
    public static void main(String[] args ) {
        new Thread(new ApgarTimer()).start();
    }
}
