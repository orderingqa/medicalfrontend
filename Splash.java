/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ls.jtsk.ui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
* ��ʵ���������˳�ʱ�ĵȴ�����
* 
*/
public class Splash {
private Window windowSplash=null; 
//private JProgressBar progressBar; 
private String picPath="";
public Splash(String pic){
   picPath=pic;
}
private void prepareSplash(){
//   progressBar=new JProgressBar(0, 100); 
//   progressBar.setForeground(new Color(140,140,140));
  
   Toolkit toolkit = Toolkit.getDefaultToolkit();    
   windowSplash = new Window(null);
   Image image = toolkit.getImage(picPath);
   JLabel l=new JLabel(new ImageIcon(image));

   windowSplash.add("Center",l);
//   windowSplash.add("North",progressBar);
   Dimension scmSize = toolkit.getScreenSize();
   int imgWidth = image.getWidth(null);
   int imgHeight = image.getHeight(null);
   windowSplash.setLocation(scmSize.width/2 - (imgWidth/2), scmSize.height/2 - (imgHeight/2) );
   windowSplash.setSize(imgWidth, imgHeight);
}
/**
* ���ý��Ȱٷֱȣ����ֵ100
* @param percent
*/
public void setValue(int percent){
   if(percent<0 || percent>100){
    throw new RuntimeException("percent is invalid.");
   }
//   progressBar.setValue(percent);
}
/**
* �������
*/
public void startSplash(){ 
   prepareSplash();
   windowSplash.setVisible(true); 
   windowSplash.toFront();
}
/**
* ����
*/
public void stopSplash() {    
   windowSplash.dispose();
} 

// ����
public static void main(String d[]){
   Splash s=new Splash("./baby.jpg");
   
   
   s.startSplash();
   
   try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ActivateWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActivateWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActivateWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActivateWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
   
   ActivateWindow ch = new ActivateWindow();
   
   
   
   
//   for(int i=1;i<=10;i++){
//       s.setValue(10*i);
//       try {Thread.sleep(1*1000);} catch (InterruptedException e) {e.printStackTrace();}
//   }
   try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
   ch.setLocationRelativeTo(null);
   ch.setVisible(true);
   s.stopSplash();
}
}
