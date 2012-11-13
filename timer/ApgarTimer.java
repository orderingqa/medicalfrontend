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

/**
 *
 * @author liushuai
 */
public class ApgarTimer implements Runnable {

    private int minute = 0, second = 0;
    JLabel extJLabel = null;
    JLabel oneMinFlashLabel = null, fiveMinFlashLabel = null, tenMinFlashLabel = null;
    Color defaultBackColor = null;
    Color hightlightColor = Color.red;
    final int apgarLimitedScoreTime = 1;

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
        while (true) {
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
                    alarmPlay("1.wav");
                }
                oneMinFlashLabel.setOpaque(true);
                if (alarmSecInternal % 2 == 1) {
                    oneMinFlashLabel.setBackground(hightlightColor);
                } else {
                    oneMinFlashLabel.setBackground(defaultBackColor);
                }
                break;
            case 1 + apgarLimitedScoreTime:
                oneMinFlashLabel.setBackground(defaultBackColor);
                break;

            case 5:
                if (alarmSecInternal == 0) {
                    alarmPlay("5.wav");
                }
                fiveMinFlashLabel.setOpaque(true);
                if (alarmSecInternal % 2 == 1) {
                    fiveMinFlashLabel.setBackground(hightlightColor);
                } else {
                    fiveMinFlashLabel.setBackground(defaultBackColor);
                }
                break;
            case 5 + apgarLimitedScoreTime:
                fiveMinFlashLabel.setBackground(defaultBackColor);
                break;

            case 10:
                if (alarmSecInternal == 0) {
                    alarmPlay("10.wav");
                }
                tenMinFlashLabel.setOpaque(true);
                if (alarmSecInternal % 2 == 1) {
                    tenMinFlashLabel.setBackground(hightlightColor);
                } else {
                    tenMinFlashLabel.setBackground(defaultBackColor);
                }
                break;
            case 10 + apgarLimitedScoreTime:
                tenMinFlashLabel.setBackground(defaultBackColor);
            default:
        }
    }

    public static void registerDisplayComponent(JComponent displayTimerLabel, JComponent oneMinFlashLabel, JComponent fiveMinFlashLabel, JComponent tenMinFlashLabel) {
        ApgarTimer ap = new ApgarTimer();
        ap.extJLabel = (JLabel) displayTimerLabel;
        ap.oneMinFlashLabel = (JLabel) oneMinFlashLabel;
        ap.fiveMinFlashLabel = (JLabel) fiveMinFlashLabel;
        ap.tenMinFlashLabel = (JLabel) tenMinFlashLabel;
        ap.defaultBackColor = oneMinFlashLabel.getBackground();
        new Thread(ap).start();
    }

    private void alarmPlay(String fileName) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName));// �����Ƶ������ 
            AudioFormat baseFormat = ais.getFormat();// ָ�����������ض����ݰ��� 
//            System.out.println("baseFormat=" + baseFormat);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, baseFormat);
//            System.out.println("info=" + info);
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            // �ӻ�Ƶ�����Դ������ 
//            System.out.println("line=" + line);
            line.open(baseFormat);// �򿪾���ָ����ʽ���У�������ʹ�л�����������ϵͳ��Դ����ÿɲ����� 
            line.start();// ����������ִ������ I/O 
            int BUFFER_SIZE = 4000 * 4;
            int intBytes = 0;
            byte[] audioData = new byte[BUFFER_SIZE];
            while (intBytes != -1) {
                intBytes = ais.read(audioData, 0, BUFFER_SIZE);// ����Ƶ����ȡָ������������������ֽڣ����������������ֽ������С� 
                if (intBytes >= 0) {
                    int outBytes = line.write(audioData, 0, intBytes);// ͨ����Դ�����н���Ƶ����д���Ƶ���� 
                }
            }

        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        new Thread(new ApgarTimer()).start();
    }
}
