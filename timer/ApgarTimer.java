/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ls.jtsk.ui.timer;

import java.awt.Color;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JComponent;
import javax.swing.JLabel;
import ls.jtsk.ui.APGARTab;

/**
 *
 * @author liushuai
 */
public class ApgarTimer implements Runnable {

    private APGARTab at = null;
    private int minute = 0, second = 0;
    JLabel extJLabel = null;
    JLabel oneMinFlashLabel = null, fiveMinFlashLabel = null, tenMinFlashLabel = null;
    Color defaultBackColor = null;
    Color hightlightColor = Color.red;
    final int apgarLimitedScoreTime = 1;
    boolean oneMinAlarmShouldStop = false;
    boolean fiveMinAlarmShouldStop = false;
    boolean tenMinAlarmShouldStop = false;
    boolean timerContinue = true;
    
    public void stopTimer() {
        timerContinue = false;
    }
    
    // TODO 怎么能够一次跳一个标示符而不是一个单词
    public void setOneMinAlarmShouldStop() {
        oneMinAlarmShouldStop = true;
    }
    public void setFiveMinAlarmShouldStop() {
        fiveMinAlarmShouldStop = true;
    }
    public void setTenMinAlarmShouldStop() {
        tenMinAlarmShouldStop = true;
    }
    
    private void autoUpdateTimer() {
        if (second < 59) {
            second++;
        } else {
            second = 0;
            if (minute < 59) {
                minute++;
            } else {
                minute = 0;
            }
        }
    }

    private String getTimerModel() {
        String minute = "0" + this.minute;
        String second = "0" + this.second;
        int minLength = minute.length();
        int secLength = second.length();
        return minute.substring(minLength - 2, minLength) + ":" + second.substring(secLength - 2, secLength);
    }

    private int getMin() {
        return minute;
    }

    private int getSec() {
        return second;
    }

    private void fillExternalComponent(JLabel jl, String newValue) {
        if (jl != null) {
            jl.setText(newValue);
        }
    }

    @Override
    public void run() {
        // 当ui界面关闭时，需要释放掉timer资源，停掉timer线程，不然会傻傻的一直叫着。
        while (timerContinue) {
            autoUpdateTimer();
            fillExternalComponent(extJLabel, getTimerModel());
            triggerAlarm(getMin(), getSec());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ApgarTimer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void triggerAlarm(int alarmMinInterval, int alarmSecInternal) {
        switch (alarmMinInterval) {
            case 1:
                if (alarmSecInternal == 0) {
                    new Thread(new AlarmWithVoice("1.wav")).start();
                }
                oneMinFlashLabel.setOpaque(true);
                
                if (alarmSecInternal % 2 == 1 && !oneMinAlarmShouldStop) {
                    oneMinFlashLabel.setBackground(hightlightColor);
                } else {
                    oneMinFlashLabel.setBackground(defaultBackColor);
                }
                break;
            case 1 + apgarLimitedScoreTime:
                oneMinFlashLabel.setBackground(defaultBackColor);
                if (this.at != null) {at.disableOneMinScoreButton();}
                break;

            case 5:
                if (alarmSecInternal == 0) {
                    new Thread(new AlarmWithVoice("5.wav")).start();
                }
                fiveMinFlashLabel.setOpaque(true);
                
                if (alarmSecInternal % 2 == 1 && !fiveMinAlarmShouldStop) {
                    fiveMinFlashLabel.setBackground(hightlightColor);
                } else {
                    fiveMinFlashLabel.setBackground(defaultBackColor);
                }
                break;
            case 5 + apgarLimitedScoreTime:
                fiveMinFlashLabel.setBackground(defaultBackColor);
                if (this.at != null) {at.disableFiveMinScoreButton();}
                break;

            case 10:
                if (alarmSecInternal == 0) {
                    new Thread(new AlarmWithVoice("10.wav")).start();
                }
                tenMinFlashLabel.setOpaque(true);
                
                if (alarmSecInternal % 2 == 1 && !tenMinAlarmShouldStop) {
                    tenMinFlashLabel.setBackground(hightlightColor);
                } else {
                    tenMinFlashLabel.setBackground(defaultBackColor);
                }
                break;
            case 10 + apgarLimitedScoreTime:
                tenMinFlashLabel.setBackground(defaultBackColor);
                if (this.at != null) {at.disableTenMinScoreButton();}
            default:
        }
    }

    public static ApgarTimer registerDisplayComponent(APGARTab apt, JComponent displayTimerLabel, JComponent oneMinFlashLabel, JComponent fiveMinFlashLabel, JComponent tenMinFlashLabel) {
        ApgarTimer ap = new ApgarTimer();
        ap.extJLabel = (JLabel) displayTimerLabel;
        ap.oneMinFlashLabel = (JLabel) oneMinFlashLabel;
        ap.fiveMinFlashLabel = (JLabel) fiveMinFlashLabel;
        ap.tenMinFlashLabel = (JLabel) tenMinFlashLabel;
        ap.defaultBackColor = oneMinFlashLabel.getBackground();
        ap.at = apt;
        new Thread(ap).start();
        return ap;
    }



    public static void main(String[] args) {
        new Thread(new ApgarTimer()).start();
    }
}
