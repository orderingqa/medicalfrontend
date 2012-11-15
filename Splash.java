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
* 简单实现启动及退出时的等待界面
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
* 设置进度百分比，最大值100
* @param percent
*/
public void setValue(int percent){
   if(percent<0 || percent>100){
    throw new RuntimeException("percent is invalid.");
   }
//   progressBar.setValue(percent);
}
/**
* 调用入口
*/
public void startSplash(){ 
   prepareSplash();
   windowSplash.setVisible(true); 
   windowSplash.toFront();
}
/**
* 结束
*/
public void stopSplash() {    
   windowSplash.dispose();
} 

// 测试
public static void main(String d[]){
   Splash s=new Splash("./baby.jpg");
   s.startSplash();
   CaseHistory ch = new CaseHistory();
//   for(int i=1;i<=10;i++){
//       s.setValue(10*i);
//       try {Thread.sleep(1*1000);} catch (InterruptedException e) {e.printStackTrace();}
//   }
   ch.setLocationRelativeTo(null);
   ch.setVisible(true);
   s.stopSplash();
}
}
