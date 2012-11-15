/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ls.jtsk.ui.timer;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author LS
 */
public class AlarmWithVoice implements Runnable{

    String fileName = null;
    public AlarmWithVoice(String wavFileName) {
        fileName = wavFileName;
    }
    
    @Override
    public void run() {
        alarmPlay(fileName);
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
            e.printStackTrace();
        }
    }
    
}
