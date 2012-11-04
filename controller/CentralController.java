/*
 * 本类会作为所有UI的控制器，接收所有视图的数据，然后基于一定的逻辑计算，填充并返回相应的新的视图。
 */
package ls.jtsk.ui.controller;

import java.util.Collection;
import javax.swing.JFrame;
import ls.jtsk.helper.ApgarHelper;
import ls.jtsk.helper.BabyHelper;
import ls.jtsk.helper.CasesHelper;
import ls.jtsk.model.Cases;
import ls.jtsk.ui.APGARTab;
import ls.jtsk.ui.CreateNewBaby;

/**
 *
 * @author liushuai
 */
public class CentralController {
    private static CasesHelper ch = new CasesHelper();
    
    public static void saveCaseAndExit(String medicalNo, String doctorName, String gravidaName, String age) {
        ch.addCase(Integer.parseInt(medicalNo), doctorName, gravidaName, Integer.parseInt(age));
    }
    
    /**
     * 
     * 本方法是视图createNewCase的控制器。它会接收视图上传过来的用户输入，然后选择一个新的视图返回。
     * @param medicalNo
     * @param doctorName
     * @param gravidaName
     * @param age 
     */
    public static void saveCaseAndShowBabyInputWindow(String medicalNo, String doctorName, String gravidaName, String age) {
        long newCaseId = ch.addCase(Integer.parseInt(medicalNo), doctorName, gravidaName, Integer.parseInt(age)); // 模型更新完毕，接下来拼装新的视图。
        // TODO 怎么样才能使得我想运行上下两个赋值可以通过配置来完成，而不是修改代码？
        //long newCaseId = 0;
        CreateNewBaby cnb = new CreateNewBaby(newCaseId);
        cnb.setTitle("病历号：" + medicalNo + "产妇："+gravidaName);
        cnb.setVisible(true);
    }
    
    public static void saveBabyAndShowApgarScoreWindow(long momId, int babyGender, String babyBirthTime, String babyWindowTitle) {
        long babyId = BabyHelper.addBaby(momId, babyGender, babyBirthTime);
        //long babyId = 0;
        APGARTab apgarWindow = new APGARTab(momId, babyId);
        apgarWindow.setTitle(babyWindowTitle+" 性别： " + babyGender + " 出生时间: " + babyBirthTime);
        apgarWindow.setVisible(true);
    }
    
    public static void saveApgarAndDisposeWindow(long momId, long babyId, Collection collections, JFrame apgarFrame){
        System.out.println(collections.size());
        ApgarHelper.addApgar(momId, babyId, collections);
        apgarFrame.dispose();
    }
}